package com.yhcrt.healthcloud.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletContext;

import org.apache.commons.lang3.StringUtils;

/**
 * 文件帮助类
 * 
 * @author cs
 * 
 */
public class FileUtil {

	public static String DEFAULT_MIME_TYPE = "application/octet-stream";

	/**
	 * 获得指定文件的byte数组
	 */
	public static byte[] getBytes(File file) {
		byte[] buffer = null;
		try {
			FileInputStream fis = new FileInputStream(file);
			ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
			byte[] b = new byte[1000];
			int n;
			while ((n = fis.read(b)) != -1) {
				bos.write(b, 0, n);
			}
			fis.close();
			bos.close();
			buffer = bos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return buffer;
	}

	/**
	 * 获取上传路径
	 * 
	 * @return
	 */
	@SuppressWarnings("unused")
	public static String getChildDir() {
		Calendar cal = Calendar.getInstance();
		String child;
		if (Calendar.MONTH < 9) {
			child = String.valueOf(cal.get(Calendar.YEAR)) + "0" + String.valueOf(cal.get(Calendar.MONTH) + 1);
		} else {
			child = String.valueOf(cal.get(Calendar.YEAR)) + String.valueOf(cal.get(Calendar.MONTH) + 1);
		}
		return child;
	}

	/**
	 * 删除文件
	 * 
	 * @param path
	 * @return
	 */
	public static boolean remove(String path) {
		boolean flag = false;
		File file = new File(path);
		if (file.exists()) {
			flag = file.delete();
		}
		return flag;
	}

	/**
	 * 获取缩略图名称
	 */
	public static String getSmallJPG(String filename) {
		String result = "";
		if (filename.indexOf(".") != -1) {
			result = filename.substring(0, filename.lastIndexOf(".")) + "_small.jpg";
		}
		return result;
	}

	/**
	 * 获取缩略图名称
	 */
	public static String getThumbnail(String filename) {
		String result = "";
		if (filename.indexOf(".") != -1) {
			result = filename.substring(0, filename.lastIndexOf(".")) + "_small"
					+ filename.substring(filename.lastIndexOf("."));
		}
		return result;
	}

	/**
	 * 获取原图
	 */
	public static String getSourceImage(String filename) {
		String result = "";
		if (StringUtils.isNotBlank(filename)) {
			if (filename.indexOf(".") != -1) {
				result = filename.substring(0, filename.lastIndexOf(".")) + "_source"
						+ filename.substring(filename.lastIndexOf("."));
			}
		}
		return result;
	}

	/**
	 * 获取缩略图名称
	 */
	public static String getSmallThumbnail(String filename, int width) {
		String result = "";
		if (StringUtils.isNotBlank(filename)) {
			if (filename.indexOf(".") != -1) {
				result = filename.substring(0, filename.lastIndexOf(".")) + "_small_" + width
						+ filename.substring(filename.lastIndexOf("."));
			}
		}
		return result;
	}

	/**
	 * 根据文件名和后缀获取文件
	 * 
	 * @param filename
	 * @return
	 */
	public static String getFile(String filename, String type) {
		String result = "";
		if (filename.indexOf(".") != -1) {
			result = filename.substring(0, filename.lastIndexOf(".")) + "." + type;
		}
		return result;
	}

	/**
	 * 获取文件前缀（文件名）
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getFilePrefix(String fileName) {
		String result = "";
		int splitIndex = fileName.lastIndexOf(".");
		if (splitIndex == -1) {
			result = fileName;
		} else {
			result = fileName.substring(0, splitIndex);
		}
		return result;
	}

	/**
	 * 获取文件后缀
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getFileSufix(String fileName) {
		int splitIndex = fileName.lastIndexOf(".");
		return fileName.substring(splitIndex + 1);
	}

	/**
	 * 拷贝文件
	 * 
	 * @param inputFile
	 * @param outputFile
	 * @throws FileNotFoundException
	 */
	public static void copyFile(String inputFile, String outputFile) throws FileNotFoundException {
		File sFile = new File(inputFile);
		File tFile = new File(outputFile);
		FileInputStream fis = new FileInputStream(sFile);
		FileOutputStream fos = new FileOutputStream(tFile);
		int temp = 0;
		try {
			while ((temp = fis.read()) != -1) {
				fos.write(temp);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 文件大小转化
	 * 
	 * @param length
	 * @return
	 */
	public static String formatFileSize(double length) {
		DecimalFormat df = new DecimalFormat("0.00");
		DecimalFormat df2 = new DecimalFormat("0.##");
		double gb = 1073741824L;
		double mb = 1048576L;
		double kb = 1024L;

		if (length >= gb) {
			return df.format(length / gb) + " GB";
		} else if (length >= mb) {
			return df2.format(length / mb) + " MB";
		} else if (length >= kb) {
			return df2.format(length / kb) + " KB";
		} else {
			return length + " B";
		}
	}

	/**
	 * 删除文件夹
	 * 
	 * @param file
	 */
	public static void deleteAll(File file) {
		if (file.isFile() || file.list().length == 0) {
			file.delete();
		} else {
			File[] files = file.listFiles();
			for (int i = 0; i < files.length; i++) {
				deleteAll(files[i]);
				files[i].delete();
			}
			if (file.exists()) {
				file.delete();
			}
		}
	}

	/**
	 * 将存放在sourceFilePath目录下的源文件,打包成fileName名称的ZIP文件,并存放到zipFilePath。
	 * 
	 * @param sourceFilePath
	 *            待压缩的文件路径
	 * @param zipFilePath
	 *            压缩后存放路径
	 * @param fileName
	 *            压缩后文件的名称
	 * @return flag
	 */
	public static boolean fileToZip(List<File> sourceFiles, String zipFilePath, String fileName) {
		boolean flag = false;
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		FileOutputStream fos = null;
		ZipOutputStream zos = null;

		try {

			File folder = new File(zipFilePath);
			if (!folder.exists()) {
				folder.mkdirs();
			}
			File zipFile = new File(zipFilePath + "/" + fileName + ".zip");
			if (zipFile.exists()) {
				System.out.println(">>>>>> " + zipFilePath + " 目录下存在名字为：" + fileName + ".zip" + " 打包文件. <<<<<<");
			} else {
				if (null == sourceFiles || sourceFiles.size() < 1) {
					System.out.println(">>>>>> 待压缩的文件目录：里面不存在文件,无需压缩. <<<<<<");
				} else {
					fos = new FileOutputStream(zipFile);
					zos = new ZipOutputStream(new BufferedOutputStream(fos));
					byte[] bufs = new byte[1024 * 10];
					for (File file : sourceFiles) {
						// 创建ZIP实体,并添加进压缩包
						ZipEntry zipEntry = new ZipEntry(file.getName());
						zos.putNextEntry(zipEntry);
						// 读取待压缩的文件并写进压缩包里
						fis = new FileInputStream(file);
						bis = new BufferedInputStream(fis, 1024 * 10);
						int read = 0;
						while ((read = bis.read(bufs, 0, 1024 * 10)) != -1) {
							zos.write(bufs, 0, read);
						}
					}
					flag = true;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			// 关闭流
			try {
				if (null != bis) {
					bis.close();
				}

				if (null != zos) {
					zos.close();
				}

			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

		return flag;
	}

	/**
	 * 将存放在sourceFilePath目录下的源文件,打包成fileName名称的ZIP文件,并存放到zipFilePath。
	 * 
	 * @param sourceFilePath
	 *            待压缩的文件路径
	 * @param zipFilePath
	 *            压缩后存放路径
	 * @param fileName
	 *            压缩后文件的名称
	 * @return flag
	 */
	public static boolean fileToZip(List<File> sourceFiles, String[] fileNameList, String zipFilePath,
			String fileName) {
		boolean flag = false;
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		FileOutputStream fos = null;
		ZipOutputStream zos = null;

		try {
			File zipFile = new File(zipFilePath + "/" + fileName + ".zip");
			if (zipFile.exists()) {
				System.out.println(">>>>>> " + zipFilePath + " 目录下存在名字为：" + fileName + ".zip" + " 打包文件. <<<<<<");
			} else {
				if (null == sourceFiles || sourceFiles.size() < 1) {
					System.out.println(">>>>>> 待压缩的文件目录：里面不存在文件,无需压缩. <<<<<<");
				} else {
					fos = new FileOutputStream(zipFile);
					zos = new ZipOutputStream(new BufferedOutputStream(fos));
					byte[] bufs = new byte[1024 * 10];

					int index = 0;
					for (File file : sourceFiles) {
						// 创建ZIP实体,并添加进压缩包
						ZipEntry zipEntry = new ZipEntry(fileNameList[index]);
						zos.putNextEntry(zipEntry);
						// 读取待压缩的文件并写进压缩包里
						fis = new FileInputStream(file);
						bis = new BufferedInputStream(fis, 1024 * 10);
						int read = 0;
						while ((read = bis.read(bufs, 0, 1024 * 10)) != -1) {
							zos.write(bufs, 0, read);
						}
						index++;
					}
					flag = true;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			// 关闭流
			try {
				if (null != bis) {
					bis.close();
				}

				if (null != zos){
					zos.close();
				}
					
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

		return flag;
	}

	/**
	 * 获取文件类型
	 */
	public static String getMineType(ServletContext context, String fileName) {
		String type = "";
		if (StringUtils.isNotBlank(fileName)) {
			type = context.getMimeType(fileName.toLowerCase());
			if (type == null){
				type = DEFAULT_MIME_TYPE;
			}
				
		} else {
			type = DEFAULT_MIME_TYPE;
		}
		return type;
	}

	/**
	 * 获取缩略图
	 * 
	 * @param filename
	 * @param width
	 * @param height
	 * @return
	 */
	public static String getSmallThumbnail(String filename, int width, int height) {
		String result = "";
		if (filename.indexOf(".") != -1) {
			result = filename.substring(0, filename.lastIndexOf(".")) + "_small_" + width + "_" + height
					+ filename.substring(filename.lastIndexOf("."));
		}
		return result;
	}

	/**
	 * 递归查询搜索文件
	 */
	public static List<File> getFileList(String dir, List<File> list) {
		if (list == null) {
			list = new ArrayList<File>();
		}

		File fileDir = new File(dir);
		if (fileDir.exists() && fileDir.isDirectory()) {
			File[] files = fileDir.listFiles();
			for (File file : files) {
				if (file.isDirectory()) {
					getFileList(file.getAbsolutePath(), list);
				} else if (file.isFile()) {
					list.add(file);
				}
			}
		}
		return list;
	}
}
