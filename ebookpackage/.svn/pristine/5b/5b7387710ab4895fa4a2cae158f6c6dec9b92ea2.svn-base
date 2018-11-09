package com.whty.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class MyZipUtil {

	/**
	 * 把zip文件解压到指定文件夹
	 * @param zipfilePath zip文件
	 * @param outPath 解压后的文件路径
	 * @throws ZipException
	 * @throws IOException
	 */
	public static void upZIPFile(String zipfilePath, String outPath)
			throws ZipException, IOException {
		System.out.println(zipfilePath+"文件解压中...");
		File oldfile = new File(zipfilePath);
		ZipFile zipFile = new ZipFile(oldfile);
		ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(
				oldfile));
		ZipEntry zipEntry = null;

		while ((zipEntry = zipInputStream.getNextEntry()) != null) {
			String fileName = zipEntry.getName();
			if (!zipEntry.isDirectory()) {
				File temp = new File(outPath + fileName);
				if (!temp.getParentFile().exists()) {
					temp.getParentFile().mkdirs();
				}
				if (!temp.exists()) {
					temp.createNewFile();
				}
//				System.out.println(temp.getAbsolutePath());
				OutputStream os = new FileOutputStream(temp);
				// 通过ZipFile的getInputStream方法拿到具体的ZipEntry的输入流
				InputStream is = zipFile.getInputStream(zipEntry);
				byte[] by = new byte[1024]; 
				int len = 0;
				while ((len = is.read(by)) != -1)
					os.write(by,0,len);
				os.close();
				is.close();
			}
		}
		zipInputStream.close();
		System.out.println(zipfilePath+"文件解压完毕！");
	}
	
	public static List getFileMD5String(String fileDirPath)
			throws FileNotFoundException, IOException {
		List list = new ArrayList();
		readfile(fileDirPath, list);
		return list;

	}

	/**
	 * 读取某个文件夹下的所有文件
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static boolean readfile(String filepath, List list)
			throws FileNotFoundException, IOException {
		try {

			File file = new File(filepath);
			if (!file.isDirectory()) {
//				System.out.println("文件");
//				System.out.println("path=" + file.getPath());
//				System.out.println("absolutepath=" + file.getAbsolutePath());
//				System.out.println("name=" + file.getName());

				list.add(file.getAbsolutePath());

			} else if (file.isDirectory()) {
//				System.out.println("文件夹");
				String[] filelist = file.list();
				for (int i = 0; i < filelist.length; i++) {
					File readfile = new File(filepath + File.separator+ filelist[i]);
					if (!readfile.isDirectory()) {
//						System.out.println("path=" + readfile.getPath());
//						System.out.println("absolutepath="
//								+ readfile.getAbsolutePath());
//						System.out.println("name=" + readfile.getName());
						list.add(readfile.getAbsolutePath());

					} else if (readfile.isDirectory()) {
						readfile(filepath + File.separator + filelist[i], list);
					}
				}

			}

		} catch (FileNotFoundException e) {
			System.out.println("readfile()   Exception:" + e.getMessage());
		}
		return true;
	}
	
	

}
