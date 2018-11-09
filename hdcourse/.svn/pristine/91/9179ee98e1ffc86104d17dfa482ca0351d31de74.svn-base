package com.whty.common.util;

import java.util.Calendar;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bos.BosClient;
import com.baidubce.services.bos.BosClientConfiguration;
import org.springframework.context.annotation.Bean;

public class BaiduBosUtils {

	/**
	 * 根据文件名生成bos的key
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getKey(String userId, String fileName) {
		StringBuffer bosStringBuffer = new StringBuffer();

		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int date = cal.get(Calendar.DATE);

		bosStringBuffer.append("zhkt").append(SysConfigUtils.getStrValue("projectName")).append("/").append(year)
				.append("/").append(month).append("/").append(date).append("/").append(userId).append("/")
				.append(fileName);

		return bosStringBuffer.toString();
	}

	/**
	 * 获取baiduClient
	 */
	public static BosClient getClient(){
		String ak = SysConfig.getStrValue("baidu.bos.ak");
		String sk = SysConfig.getStrValue("baidu.bos.sk");
		String bucketname = SysConfig.getStrValue("baidu.bos.bucketname");
		String pathProductName = SysConfig.getStrValue("baidu.bos.path.productname");
		String pathVersion = SysConfig.getStrValue("baidu.bos.path.version");
		String baiduBosUrl = SysConfig.getStrValue("baidu.bos.url");
		Calendar cal = Calendar.getInstance();
		StringBuffer bosStringBuffer = new StringBuffer();
		bosStringBuffer.append(pathProductName).append("/").append(pathVersion).append("/")
				.append(cal.get(Calendar.YEAR)).append(cal.get(Calendar.MONTH))
				.append(cal.get(Calendar.DAY_OF_MONTH)).append("/").append(cal.getTime().getTime());

		String bosPath = bosStringBuffer.toString();

		BosClientConfiguration config = new BosClientConfiguration();
		config.setCredentials(new DefaultBceCredentials(ak, sk));
		BosClient client = new BosClient(config);
		return client;
	}

	/**
	 * 删除百度文件
	 * 删除的url路径必须包含3层目录
	 * @param objectKey  文件url路径，不包含 域名
	 * 例如 http://whty.bj.bcebos.com/hdcourse/test/201879/15337938629811533793858441.txt  objectKe传/hdcourse/test/201879/15337938629811533793858441.txt
	 * @return 删除成功返回true
	 */
	public static boolean deleteFile(String objectKey) {
		String[] dirList = objectKey.split("/");

		boolean result = false;

		if (dirList.length < 3)
			return result;

		getClient().deleteObject(SysConfig.getStrValue("baidu.bos.bucketname"), objectKey);
		return result;
	}

//	public static void main(String[] args) {
//		deleteFile(client, bucketName, objectKey)
//		
//		String sa = "hdcourse/test/201879/15337938629811533793858441.txt";
//		String[] s = sa.split("/");
//
//		System.out.println(s.length);
//	}

}
