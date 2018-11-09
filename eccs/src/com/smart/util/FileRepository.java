package com.smart.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 本地文件存储
 */
public class FileRepository {
	private Logger log = LoggerFactory.getLogger(FileRepository.class);

	private String rootDir;

	private String rootNode;

	public FileRepository() {
		this.rootDir = Property.getProperty("/eccs.properties", "file.dir");
		this.rootNode = Property.getProperty("/eccs.properties",
				"file.root.node");
	}

	public String storeByExt(String destPath, String fileName, File file)
			throws IOException {
		String tempPath = UploadUtils.generateFilename(destPath, fileName);
		String rootPath = getRealPath(tempPath);
		File dest = new File(rootPath);
		dest = UploadUtils.getUniqueFile(dest);
		store(file, dest);
		return rootPath.substring(rootPath.indexOf(rootNode) - 1, rootPath
				.length());
	}

	public String storeByFilename(String filename, File file)
			throws IOException {
		String rootPath = getRealPath(filename);
		File dest = new File(rootPath);
		store(file, dest);
		return rootPath;
	}

	private void store(File file, File dest) throws IOException {
		try {
			UploadUtils.checkDirAndCreate(dest.getParentFile());
			FileUtils.copyFile(file, dest);
		} catch (IOException e) {
			log.error("Transfer file error when upload file", e);
			throw e;
		}
	}

	private String getRealPath(String tempPath) {
		tempPath = rootDir + File.separator + rootNode + File.separator
				+ tempPath;
		return tempPath.replace("\\", "/");
	}
}
