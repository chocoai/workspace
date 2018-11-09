
package com.yhcrt.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

/**
 * @Description: 数据库的备份与恢复
 * @author: 陈伟
 * @date: 2017年5月15日 下午1:06:34
 * @version: V1.0
 */
public class DatebaseUtils {
	static String ip = "localhost";// ip地址
	static String datebasePath = "C:/Program Files/MySQL/MySQL Server 5.7/bin";// 数据库安装路径
	static String username = "root";// MySQL数据库的用户名
	static String password = "root";// MySQL数据库的密码
	static String datebaseName = "syh";// 数据库名字
	static String filePath = "C:/Users/陈伟/Desktop/sql";// 备份的路径

	public static void main(String[] args) throws IOException {
		DatebaseUtils db = new DatebaseUtils();
		 boolean isSuession = db.backupDatebase(ip, datebasePath, username, password, datebaseName, filePath);
		 if(isSuession){
			System.out.println("操作成功"); 
		 }
	}

	/**
	 * 数据库备份
	 * 
	 * @param ip
	 *            ip地址
	 * @param datebasePath
	 *            数据库安装路径
	 * @param username
	 *            MySQL数据库的用户名
	 * @param password
	 *            MySQL数据库的密码
	 * @param datebaseName
	 *            数据库名字
	 * @param filePath
	 *            备份文件存放路径
	 * @throws IOException
	 * @return
	 */
	public static boolean backupDatebase(String ip, String datebasePath, String username, String password, String datebaseName,
			String filePath) throws IOException {
		String strCommand = datebasePath + "/" + "mysqldump -h" + ip + " -u" + username + " -p" + password + " "
				+ datebaseName;
		filePath += "/" + datebaseName +"_"+DateUtil.getDateToString14()+".sql";
		File dirTemp = new File(filePath);
		if (!dirTemp.exists()) {
			dirTemp.createNewFile();
		}
		execCmd(strCommand, filePath);
		return true;
	}

	/**
	 * 数据库恢复
	 * 
	 * @param ip
	 *            ip地址
	 * @param datebasePath
	 *            数据库安装路径
	 * @param username
	 *            MySQL数据库的用户名
	 * @param password
	 *            MySQL数据库的密码
	 * @param datebaseName
	 *            数据库名字
	 * @param filePath
	 *            恢复文件的绝对路径
	 * @throws IOException
	 * @return
	 */
	public static boolean restoreDatebase(String ip, String datebasePath, String username, String password,
			String datebaseName, String filePath) throws IOException {
		String strCommand = datebasePath + "/" + "mysql.exe -h" + ip + " -u" + username + " -p" + password
				+ " --default-character-set=utf8 " + datebaseName;
		restore(strCommand, filePath);
		return true;
	}

	/**
	 * 
	 * @Title: execCmd
	 * @Description: 执行备份dos命令
	 * @return: String
	 */
	private static String execCmd(String cmd, String filePath) throws IOException {
		System.out.println(cmd);

		Runtime rt = Runtime.getRuntime();
		StringBuffer sb = new StringBuffer("");
		Process ls_proc;
		InputStream in = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		OutputStreamWriter writer = null;
		FileOutputStream fout = null;
		try {
			// 调用 调用mysql的安装目录的命令
			ls_proc = rt.exec(cmd);
			// 设置导出编码为utf-8。这里必须是utf-8
			// 把进程执行中的控制台输出信息写入.sql文件，即生成了备份文件。注：如果不对控制台信息进行读出，则会导致进程堵塞无法运行
			in = ls_proc.getInputStream();// 控制台的输出信息作为输入流

			isr = new InputStreamReader(in, "utf-8");
			// 设置输出流编码为utf-8。这里必须是utf-8，否则从流中读入的是乱码
			String inStr;
			String outStr;
			// 组合控制台输出信息字符串
			br = new BufferedReader(isr);
			while ((inStr = br.readLine()) != null) {
				sb.append(inStr + "\r\n");
			}
			outStr = sb.toString();

			// 要用来做导入用的sql目标文件：
			fout = new FileOutputStream(filePath);
			writer = new OutputStreamWriter(fout, "utf-8");
			writer.write(outStr);
			writer.flush();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			in.close();
			isr.close();
			br.close();
			writer.close();
			fout.close();
		}

		return sb.toString();
	}

	/**
	 * 
	 * @Title: restore
	 * @Description: 执行恢复dos命令
	 * @return: void
	 */
	private static void restore(String cmd, String filePath) throws IOException {
		Runtime runtime = Runtime.getRuntime();
		Process process;
		OutputStream outputStream = null;
		BufferedReader br = null;
		OutputStreamWriter writer = null;
		try {
			process = runtime.exec(cmd);
			outputStream = process.getOutputStream();
			br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "utf-8"));
			String str = null;
			StringBuffer sb = new StringBuffer();
			while ((str = br.readLine()) != null) {
				sb.append(str + "\r\n");
			}
			str = sb.toString();
			writer = new OutputStreamWriter(outputStream, "utf-8");
			writer.write(str);
			writer.flush();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			outputStream.close();
			br.close();
			writer.close();
		}
	}

}