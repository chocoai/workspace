package com.smart.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 * 读取文件类
 * 
 * @author 充满智慧的威哥
 */
public class FileUtil {

	private static final Logger log = Logger.getLogger(FileUtil.class);

	private static List<String> readEncodeList = new ArrayList<String>(); // 可读取的文件编码

	private static List<String> readExtList = new ArrayList<String>(); // 可读取的文件类型

	static {
		// 可读取的文件编码初始化
		readEncodeList.add("Big5");
		readEncodeList.add("GB18030");
		readEncodeList.add("GB2312");
		readEncodeList.add("GBK");
		readEncodeList.add("ISO-8859-1");
		readEncodeList.add("ISO-8859-5");
		readEncodeList.add("ISO-8859-7");
		readEncodeList.add("ISO-8859-8");
		readEncodeList.add("US-ASCII");
		readEncodeList.add("UTF-16BE");
		readEncodeList.add("UTF-16LE");
		readEncodeList.add("UTF-8");
		readEncodeList.add("x-EUC-TW");
		readEncodeList.add("IBM855");
		readEncodeList.add("void"); // 不知道编码的

		// 可读取的文件类型初始化
		readExtList.add("txt");
		readExtList.add("doc");
		readExtList.add("docx");
		readExtList.add("htm");
		readExtList.add("html");
		readExtList.add("java");
		readExtList.add("pdf");
		readExtList.add("js");
		readExtList.add("xml");
		readExtList.add("jsp");
		readExtList.add("ftl");
		readExtList.add("mht");
		readExtList.add("properties");
		readExtList.add("xls");
	}

	/**
	 * 新建文件
	 * 
	 * @param filePathAndName
	 *            文本文件完整绝对路径及文件名
	 * @param fileContent
	 *            文本文件内容
	 * @return
	 */
	public static void write(String path, String content, String encoding) {

		try {
			File myFilePath = new File(path);
			if (!myFilePath.exists()) {
				myFilePath.createNewFile();
			}
			OutputStreamWriter out = new OutputStreamWriter(
					new FileOutputStream(myFilePath), encoding);
			PrintWriter myFile = new PrintWriter(out);
			String strContent = content;
			myFile.println(strContent);
			myFile.close();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 是否可解析的编码
	 * 
	 * @param encode
	 * @return
	 */
	public static boolean canReadEncode(String encode) {
		// 针对 windows-1250 到 windows-1256的编码
		if (encode.toLowerCase().startsWith("windows")) {
			return true;
		}
		return readEncodeList.contains(encode) ? true : false;
	}

	/**
	 * 是否可读取的文件
	 * 
	 * @param ext
	 * @return
	 */
	public static boolean canReadExt(String ext) {
		return readExtList.contains(ext) ? true : false;
	}

	/**
	 * 读取资源配置文件
	 * 
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public static String readProperties(String path, String encode) {
		return readTxt(path, encode);
	}

	/**
	 * 读取txt文件
	 * 
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public static String readTxt(String path, String encode) {
		try {
			StringBuilder str = new StringBuilder("");
			FileInputStream fs = new FileInputStream(path);
			InputStreamReader isr = new InputStreamReader(fs, encode);
			BufferedReader br = new BufferedReader(isr);

			String data = "";
			while ((data = br.readLine()) != null) {
				str.append(data).append("\n");
			}

			br.close();
			isr.close();
			fs.close();
			return str.toString();
		} catch (Exception e) {
			log.error("读取文本错误：" + path);
		}
		return "";
	}

	// ================================ getter and setter
	// =============================================
	public static List<String> getReadEncodeList() {
		return readEncodeList;
	}

	public static void setReadEncodeList(List<String> readEncodeList) {
		FileUtil.readEncodeList = readEncodeList;
	}

	public static List<String> getReadExtList() {
		return readExtList;
	}

	public static void setReadExtList(List<String> readExtList) {
		FileUtil.readExtList = readExtList;
	}

}
