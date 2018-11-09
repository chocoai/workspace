package com.yhcrt.weihu.common.upload;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

/**
 * 本地文件存储
 */
public class FileRepository implements ServletContextAware {
    private Logger log = LoggerFactory.getLogger(FileRepository.class);

    public String storeByExt(String path, String ext, MultipartFile file) throws IOException {
	String filename = UploadUtils.generateFilename(path, ext);
	File dest = new File(getRealPath(filename));
	dest = UploadUtils.getUniqueFile(dest);
	store(file, dest);
	return filename;
    }

    public String storeByFilename(String filename, MultipartFile file) throws IOException {
	if (filename != null && (filename.contains("/") || filename.contains("\\") || filename.indexOf("\0") != -1)) {
	    return "";
	}
	File dest = new File(getRealPath(filename));
	store(file, dest);
	return filename;
    }

    public String storeByExt(String path, String ext, File file) throws IOException {
	String filename = UploadUtils.generateFilename(path, ext);
	File dest = new File(getRealPath(filename));
	dest = UploadUtils.getUniqueFile(dest);
	store(file, dest);
	return filename;
    }

    public String storeByFilename(String filename, File file) throws IOException {
	File dest = new File(getRealPath(filename));
	store(file, dest);
	return filename;
    }

    private void store(MultipartFile file, File dest) throws IOException {
	try {
	    UploadUtils.checkDirAndCreate(dest.getParentFile());
	    file.transferTo(dest);
	} catch (IOException e) {
	    log.error("Transfer file error when upload file", e);
	    throw e;
	}
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

    public File retrieve(String name) {
	return new File(ctx.getRealPath(name));
    }

    private String getRealPath(String name) {
	String realpath = ctx.getRealPath(name);
	if (realpath == null) {
	    realpath = ctx.getRealPath("/") + name;
	}
	if (StringUtils.isNotBlank(ctx.getContextPath())) {
	    realpath = StringUtils.remove(realpath, ctx.getContextPath().substring(1));
	}else{
	    realpath = StringUtils.remove(realpath, "ROOT");
	}
	return realpath;
    }

    private ServletContext ctx;

    public void setServletContext(ServletContext servletContext) {
	this.ctx = servletContext;
    }
}