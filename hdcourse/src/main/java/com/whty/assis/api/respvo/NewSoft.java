package com.whty.assis.api.respvo;

import java.util.List;

//新版本客户端对象
public class NewSoft {

	private String id;
	private List<ClientFile> fileList;// 绿色包解压的文件列表
	private Integer count;
	// private String packageUrl;

	private String packageId;
	private String packageSize;
	private String packageMd5;

	public String getPackageMd5() {
		return packageMd5;
	}

	public void setPackageMd5(String packageMd5) {
		this.packageMd5 = packageMd5;
	}

	public String getPackageSize() {
		return packageSize;
	}

	public void setPackageSize(String packageSize) {
		this.packageSize = packageSize;
	}

	public String getPackageId() {
		return packageId;
	}

	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<ClientFile> getFileList() {
		return fileList;
	}

	public void setFileList(List<ClientFile> fileList) {
		this.fileList = fileList;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

}
