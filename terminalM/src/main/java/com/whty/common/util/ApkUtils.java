/**
 * @Title: ApkUtls.java 
 * @Package com.whty.apkutils 
 * @Description: ApkUtils
 * @author xjin
 * @date 2014-6-26 
 * @version    
 */
package com.whty.common.util;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import com.whty.assis.mall.model.ApkInfo;
import com.whty.assis.mall.model.ImpliedFeature;
import com.whty.assis.mall.model.Version;
import com.whty.common.util.SysConfig;

/**
 * @ClassName: ApkUtls
 * @author xjin
 * @date 2014-6-26 获取apk文件的版本等信息
 */
public class ApkUtils {

	public static final String VERSION_CODE = "versionCode";
	public static final String VERSION_NAME = "versionName";
	public static final String SDK_VERSION = "sdkVersion";
	public static final String TARGET_SDK_VERSION = "targetSdkVersion";
	public static final String USES_PERMISSION = "uses-permission";
	public static final String APPLICATION_LABEL = "application-label";
	public static final String APPLICATION_ICON = "application-icon";
	public static final String USES_FEATURE = "uses-feature";
	public static final String USES_IMPLIED_FEATURE = "uses-implied-feature";
	public static final String SUPPORTS_SCREENS = "supports-screens";
	public static final String SUPPORTS_ANY_DENSITY = "supports-any-density";
	public static final String DENSITIES = "densities";
	public static final String PACKAGE = "package";
	public static final String APPLICATION = "application:";
	public static final String LAUNCHABLE_ACTIVITY = "launchable-activity";
	private ProcessBuilder mBuilder;
	private static final String SPLIT_REGEX = "(: )|(=')|(' )|'";
	private static final String FEATURE_SPLIT_REGEX = "(:')|(',')|'";
	private String mAaptPath = SysConfig.getStrValue("aapt_path");

	public ApkUtils() {
		this.mBuilder = new ProcessBuilder(new String[0]);
		this.mBuilder.redirectErrorStream(true);
	}

	public ApkInfo getApkInfo(String apkPath) throws Exception {
		Runtime rt = Runtime.getRuntime();
		Properties props = System.getProperties();
		String osname = props.getProperty("os.name");
		String order = mAaptPath + "aapt" + " d badging " + apkPath + " | grep -E \"application:\"";
		if (osname.indexOf("Windows") > -1) {
			order = mAaptPath + "aapt.exe" + " d badging \"" + apkPath + "\"";
		}
		Process proc = rt.exec(order);
		InputStream is = null;
		is = proc.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is, "utf8"));
		String tmp = null;
		try {
			ApkInfo apkInfo = new ApkInfo();
			while ((tmp = br.readLine()) != null) {
				setApkInfoProperty(apkInfo, tmp);
			}
			return apkInfo;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			proc.destroy();
			closeIO(is);
			closeIO(br);
		}
		return null;
	}

	private void setApkInfoProperty(ApkInfo apkInfo, String source) {
		System.out.println(source);
		if (source.startsWith("package")) {
			splitPackageInfo(apkInfo, source);
		} else if (source.startsWith("launchable-activity")) {
			apkInfo.setLaunchableActivity(getPropertyInQuote(source));
			splitLaunchableActivity(apkInfo, source);
			System.out.println("launchable-activity" + source);

		} else if (source.startsWith("sdkVersion")) {
			apkInfo.setSdkVersion(getPropertyInQuote(source));
		} else if (source.startsWith("targetSdkVersion")) {
			apkInfo.setTargetSdkVersion(getPropertyInQuote(source));
		} else if (source.startsWith("uses-permission")) {
			apkInfo.addToUsesPermissions(getPropertyInQuote(source));
		} else if (source.startsWith("application-label")) {
			apkInfo.setApplicationLable(getPropertyInQuote(source));
		} else if (source.startsWith("application-icon")) {
			apkInfo.addToApplicationIcons(getKeyBeforeColon(source), getPropertyInQuote(source));
		} else if (source.startsWith("application:")) {
			String[] rs = source.split("( icon=')|'");
			apkInfo.setApplicationIcon(rs[(rs.length - 1)]);
		} else if (source.startsWith("uses-feature")) {
			apkInfo.addToFeatures(getPropertyInQuote(source));
		} else if (source.startsWith("uses-implied-feature")) {
			apkInfo.addToImpliedFeatures(getFeature(source));
		}else if(source.startsWith("launchable activity")){
			splitLaunchableActivity(apkInfo, source);
			System.out.println("launchable activity" + source);
		}
	}

	/**
	 * @param apkInfo
	 * @param source
	 */
	private void splitLaunchableActivity(ApkInfo apkInfo, String source) {
		String[] packageInfo = source.split("(: )|(=')|(' )|'");
		// apkInfo.setPackageName(packageInfo[2]);
		// apkInfo.setVersionCode(packageInfo[4]);
		// apkInfo.setVersionName(packageInfo[6]);
		System.out.println("splitLaunchableActivity:" + source);
		System.out.println(packageInfo[2] + ":" + packageInfo[4]+":"+packageInfo[3]);
		apkInfo.setName(packageInfo[3]);
	}

	private ImpliedFeature getFeature(String source) {
		String[] result = source.split("(:')|(',')|'");
		ImpliedFeature impliedFeature = new ImpliedFeature(result[1], result[2]);
		return impliedFeature;
	}

	private String getPropertyInQuote(String source) {
		int index = source.indexOf("'") + 1;
		return source.substring(index, source.indexOf('\'', index));
	}

	private String getKeyBeforeColon(String source) {
		return source.substring(0, source.indexOf(':'));
	}

	private void splitPackageInfo(ApkInfo apkInfo, String packageSource) {
		String[] packageInfo = packageSource.split("(: )|(=')|(' )|'");
		apkInfo.setPackageName(packageInfo[2]);
		apkInfo.setVersionCode(packageInfo[4]);
		apkInfo.setVersionName(packageInfo[6]);
	}

	public static final void closeIO(Closeable c) {
		if (c == null)
			return;
		try {
			c.close();
		} catch (IOException localIOException) {
		}
	}

	public String getAaptPath() {
		return this.mAaptPath;
	}

	public void setAaptPath(String mAaptPath) {
		this.mAaptPath = mAaptPath;
	}

	public static void main(String[] args) {
		try {
			String demo = "E:\\lib\\aapt\\hdkt10_29.apk";
			if (args.length > 0) {
				if ((args[0].equals("-version")) || (args[0].equals("-v"))) {
					System.out.println("ApkUtil   -by Geek_Soledad");
					System.out.println("Version:" + Version.getVersion());
					return;
				}
				demo = args[0];
			}
			ApkInfo apkInfo = new ApkUtils().getApkInfo(demo);
			System.out.println(apkInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void sys() {
		System.out.println("hello lvs lmns world");
	}
}
