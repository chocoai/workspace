package com.whty.assis.demo.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bos.BosClient;
import com.baidubce.services.bos.BosClientConfiguration;
import com.whty.assis.api.respvo.CheckNewSilentSoft;
import com.whty.assis.api.respvo.CheckNewSoft;
import com.whty.assis.api.respvo.ClientFile;
import com.whty.assis.api.respvo.SoftAreaUpgrade;
import com.whty.assis.base.service.BaseService;
import com.whty.assis.demo.dao.SoftDao;
import com.whty.assis.demo.model.Soft;
import com.whty.assis.demo.model.SoftFile;
import com.whty.assis.demo.service.SoftService;
import com.whty.common.util.DelFile;
import com.whty.common.util.GUIDGenerator;
import com.whty.common.util.MD5Filter;
import com.whty.common.util.SysConfig;
import com.whty.common.util.ZipCompress;
import com.whty.page.util.HandlerResult;

/**
 * 客户端版本信息Service实现
 * 
 * @author zhangguozhu
 */
@Service
public class SoftServiceImpl extends BaseService implements SoftService {

	private static Logger logger = LoggerFactory.getLogger(SoftServiceImpl.class);

	@Autowired
	private SoftDao softDao;

	@Override
	public Map<String, Object> loadFilePackage(Map<String, Object> map) {
		List<Map<String, Object>> resultMap = softDao.loadFilePackage(map);
		Map<String, Object> resultObject = null;
		if (resultMap != null && resultMap.size() > 0) {

			resultObject = resultMap.get(0);

		}
		return resultObject;
	}

	@Override
	public void uploadPackageUrl(Map<String, Object> map, List<ClientFile> clientFileList)
			throws FileNotFoundException, IOException {

		List<Map<String, String>> fileList = new ArrayList<Map<String, String>>();
		for (ClientFile clientFile : clientFileList) {
			Map<String, String> fileMap = new HashMap<String, String>();

			if ("add".equals(clientFile.getUpdateType())) {
				fileMap.put("absolute_url", clientFile.getAbsoluteUrl());
				fileMap.put("file_path", clientFile.getFilePath());
				fileList.add(fileMap);
			}
		}

		String absoluteUrl = SysConfig.getStrValue("file_path_pre") + SysConfig.getStrValue("soft_upload_dir")
				+ "upgrade" + File.separator + GUIDGenerator.getUUID32() + ".zip";
		// 写压缩文件
		ZipCompress.writeFileToZip(fileList, absoluteUrl, "");

		// softFile.setAbsoluteUrl(archive);
		// 保存压缩文件路ing
		Map<String, Object> softFilePackageMap = new HashMap<String, Object>();

		Map<String, Object> softMap = new HashMap<String, Object>();

		softMap.put("softType", map.get("softType"));
		softMap.put("versionCode", map.get("versionCode"));

		List<Soft> softList = softDao.querySoft(softMap);

		String oldSoftId = null;

		if (softList != null && softList.size() != 0) {
			oldSoftId = softList.get(0).getId();
		}

		File file = new File(absoluteUrl);

		softFilePackageMap.put("id", GUIDGenerator.getUUID32());
		softFilePackageMap.put("oldSoftId", oldSoftId);
		softFilePackageMap.put("newSoftId", map.get("id"));
		softFilePackageMap.put("absoluteUrl", absoluteUrl);
		softFilePackageMap.put("fileSize", file.length());
		String md5 = MD5Filter.getMd5ByFile(file);
		softFilePackageMap.put("md5", md5);

		// 上传到百度云
		StringBuffer bosStringBuffer = new StringBuffer();
		Calendar cal = Calendar.getInstance();
		String ak = SysConfig.getStrValue("baidu.bos.ak");
		String sk = SysConfig.getStrValue("baidu.bos.sk");
		String bucketname = SysConfig.getStrValue("baidu.bos.bucketname");
		String pathProductName = SysConfig.getStrValue("baidu.bos.path.productname");
		String pathVersion = SysConfig.getStrValue("baidu.bos.path.version");
		String baiduBosUrl = SysConfig.getStrValue("baidu.bos.url");
		bosStringBuffer.append(pathProductName).append("/").append(pathVersion).append("/")
				.append(cal.get(Calendar.YEAR)).append(cal.get(Calendar.MONTH)).append(cal.get(Calendar.DAY_OF_MONTH))
				.append("/").append(cal.getTime().getTime());

		BosClientConfiguration config = new BosClientConfiguration();
		config.setCredentials(new DefaultBceCredentials(ak, sk));
		BosClient client = new BosClient(config);

		client.putObject(bucketname, bosStringBuffer + file.getName(), file);// 上传到百度云
		String bosAddress = baiduBosUrl + bosStringBuffer + file.getName();//
		// 图标地址

		softFilePackageMap.put("bosAddress", bosAddress);

		System.out.println("保存软件包:" + md5);
		softDao.saveSoftFilePackage(softFilePackageMap);

	}

	@Override
	public Map<String, Object> savePackageUrl(Map<String, Object> map, List<ClientFile> clientFileList)
			throws FileNotFoundException, IOException {
		// String packageUrl = null;
		// String packageId = null;

		Map<String, Object> resultPackage = new HashMap<String, Object>();

		List<Map<String, Object>> resultList = softDao.getFilePackage(map);

		// String file_path_pre = SysConfig.getStrValue("file_path_pre");
		// String file_path_http = SysConfig.getStrValue("file_path_http");
		if (resultList != null && resultList.size() != 0) {
			Map<String, Object> resultMap = resultList.get(0);
			// logger.info(resultMap.toString());
			resultPackage.put("packageId", resultMap.get("ID").toString());
			resultPackage.put("fileSize", resultMap.get("FILE_SIZE").toString());
			resultPackage.put("md5", resultMap.get("MD5").toString());
			// packageUrl = file_path_http +
			// resultMap.get("ABSOLUTE_URL").toString().replace(file_path_pre,
			// "");
		} else {
			List<Map<String, String>> fileList = new ArrayList<Map<String, String>>();
			for (ClientFile clientFile : clientFileList) {
				Map<String, String> fileMap = new HashMap<String, String>();

				if ("add".equals(clientFile.getUpdateType())) {
					fileMap.put("absolute_url", clientFile.getAbsoluteUrl());
					fileMap.put("file_path", clientFile.getFilePath());
					fileList.add(fileMap);
				}
			}

			String absoluteUrl = SysConfig.getStrValue("file_path_pre") + SysConfig.getStrValue("soft_upload_dir")
					+ "upgrade" + File.separator + GUIDGenerator.getUUID32() + ".zip";
			// 写压缩文件
			ZipCompress.writeFileToZip(fileList, absoluteUrl, "");

			// softFile.setAbsoluteUrl(archive);
			// 保存压缩文件路ing
			Map<String, Object> softFilePackageMap = new HashMap<String, Object>();

			Map<String, Object> softMap = new HashMap<String, Object>();

			softMap.put("softType", map.get("softType"));
			softMap.put("versionCode", map.get("versionCode"));

			List<Soft> softList = softDao.querySoft(softMap);
			String oldSoftId = null;
			if (softList != null && softList.size() != 0) {
				oldSoftId = softList.get(0).getId();
			}

			if (oldSoftId == null)
				return null;

			softFilePackageMap.put("id", GUIDGenerator.getUUID32());
			softFilePackageMap.put("oldSoftId", oldSoftId);
			softFilePackageMap.put("newSoftId", map.get("id"));
			softFilePackageMap.put("absoluteUrl", absoluteUrl);
			softFilePackageMap.put("fileSize", new File(absoluteUrl).length());
			softFilePackageMap.put("md5", MD5Filter.getMd5ByFile(new File(absoluteUrl)));

			softDao.saveSoftFilePackage(softFilePackageMap);

			resultPackage.put("md5", softFilePackageMap.get("md5").toString());
			resultPackage.put("packageId", softFilePackageMap.get("id").toString());
			resultPackage.put("fileSize", softFilePackageMap.get("fileSize").toString());
		}
		return resultPackage;
	}

	// @Override
	// public List<Map<String, Object>> getFilePackage(Map<String, Object> map)
	// {
	// return softDao.getFilePackage(map);
	// }

	/**
	 * 查询最新的版本信息
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Soft queryNew(Map paramMap) {
		List list = softDao.queryNewSoft(paramMap);
		if (list != null && list.size() > 0) {
			return (Soft) list.get(0);
		}
		return null;
	}

	/**
	 * 查询当前版本信息
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public List<Soft> querySoft(Map paramMap) {
		return softDao.querySoft(paramMap);
	}

	/**
	 * 分页查询版本信息
	 */
	@Override
	public HandlerResult querySoftPage(Map<String, Object> paramMap) {
		logger.info("queryPage:" + paramMap);
		HandlerResult rs = new HandlerResult();
		rs.setResultList(softDao.querySoft(paramMap));
		return rs;
	}

	@Override
	public void saveSoft(Soft soft) {
		if (StringUtils.isEmpty(soft.getId())) {
			soft.setId(GUIDGenerator.getUUID32());
		}
		softDao.saveSoft(soft);
	}

	@Override
	public void updateSoftFile(SoftFile softFile) {
		softDao.updateSoftFile(softFile);
	}

	/**
	 * 解压入库
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public boolean unzipSoft(String softId) {
		Map map = new HashMap();
		map.put("id", softId);
		List<Soft> softList = softDao.querySoft(map);
		if (softList != null && softList.size() > 0) {
			Soft soft = softList.get(0);

			try {
				List idList = new ArrayList();
				idList.add(soft.getId());
				// 删除绿色包客户端文件列表
				softDao.deleteSoftFile(idList);
				// 删除对应生成的升级文件数据
				softDao.deleteSoftUpgradeFile(idList);

				String unzipfilePath = this.unZipSoft(soft.getFileUrl());
				List<SoftFile> list = this.readfile(unzipfilePath, soft.getId(), unzipfilePath);

				this.saveSoftFile(list);

			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}

		return true;
	}

	@Override
	public void updateSoft(Soft soft) {
		softDao.updateSoft(soft);
	}

	@Override
	public void saveSoftFile(List<SoftFile> list) {
		if (list != null && list.size() > 0) {
			softDao.saveSoftFileBatch(list);
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<SoftFile> querySoftFile(Map map) {
		return softDao.querySoftFile(map);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<SoftFile> querySoftUpgradeFile(Map map) {
		return softDao.querySoftUpgradeFile(map);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void deleteSoft(List list) {
		/*
		 * 从服务器上删除上传的客户端文件和解压出的文件
		 */
		Map paramMap = new HashMap();
		paramMap.put("idList", list);
		List<Soft> softList = softDao.querySoft(paramMap);
		for (Soft soft : softList) {
			String filePath = soft.getFileUrl();
			File softFile = new File(filePath);
			if (softFile.exists()) {
				softFile.deleteOnExit();
				try {
					FileUtils.forceDelete(softFile);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if ("1".equals(soft.getFileType())) {
				// 客户端文件解压后存放的路径
				int lastIndex = filePath.lastIndexOf(File.separator);
				String zipFileName = filePath.substring(lastIndex + 1);
				String unzipfilePath = filePath.substring(0, lastIndex + 1) + "unzip" + File.separator
						+ zipFileName.substring(0, zipFileName.lastIndexOf(".")) + File.separator;

				// 清除对应的解压文件
				DelFile.delFolder(unzipfilePath);
			}
		}

		// 删除客户端信息
		softDao.deleteSoft(list);

		// 删除绿色包客户端文件列表
		softDao.deleteSoftFile(list);

		// 删除对应生成的升级文件数据
		softDao.deleteSoftUpgradeFile(list);

		Map paramap = new HashMap();
		paramap.put("list", list);

		// 删除对应的升级包
		this.removeUpgradePackage(paramap);

		// 删除升级版本关联数据
		softDao.deleteUpgradeSoftRel(paramap);

		// 删除指定用户升级的数据
		paramap.clear();
		// paramap.put("softIdList", list);

		for (int i = 0; i < list.size(); i++) {
			paramap.put("softId", list.get(i));
			softDao.deleteSoftUserUpgrade(paramap);
		}

	}

	/**
	 * 创建升级文件列表 2.查询当前版本的文件列表 3.与老版本对比生成升级文件列表 4.标记最新版本的升级状态为可升级
	 * 
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void createSoftUpgradeFile(String softId) throws FileNotFoundException, IOException {
		// 1
		softDao.deleteSoftUpgradeFile(new ArrayList());
		// 2
		Map paramMap = new HashMap();
		paramMap.put("softId", softId);
		List<SoftFile> newSoftFileList = softDao.querySoftFile(paramMap);
		// 3
		this.saveSoftUpgradeFile(newSoftFileList, softId);
		// 4
		softDao.updateSoftIsleveup("0");
		Soft soft = new Soft();
		soft.setId(softId);
		soft.setIsleveup("1");
		this.updateSoft(soft);
	}

	/**
	 * 老版本对比生成升级文件列表
	 * 
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void saveSoftUpgradeFile(List<SoftFile> newSoftFileList, String newSoftId)
			throws FileNotFoundException, IOException {

		// 查询所有有效的教师助手的绿色版本列表
		Map paraMap = new HashMap();
		paraMap.put("status", "0");// 有效
		paraMap.put("softType", "0");// 教师助手
		paraMap.put("fileType", "1");// 绿色安装包
		List<Soft> softList = softDao.querySoft(paraMap);

		List<Soft> softList2 = new ArrayList<Soft>();

		List<Map<String, Object>> softListMap = new ArrayList<Map<String, Object>>();

		/*
		 * 对比生成老客户端到新客户端的文件升级列表: 1.当文件相对路径和md5值都相同时，不需要更新；
		 * 2.当文件相对路径相同并且md5值不相同时，更新类型为"add"; 3.老客户端文件列表中剩余文件的更新类型为"delete";
		 * 4.新客户端文件列表中剩余文件的更新类型为"add"。
		 */
		if (softList != null && softList.size() > 0) {
			List<Map> softUpgradeList = new ArrayList<Map>();

			List<SoftFile> oldSoftFileList;
			for (Soft soft : softList) {
				String oldSoftId = soft.getId();
				if (oldSoftId.equals(newSoftId))
					continue;

				Map map = new HashMap();
				map.put("softId", oldSoftId);
				oldSoftFileList = softDao.querySoftFile(map);

				// 客户端文件列表中已经做过匹配的文件
				List<SoftFile> newSoftFileRemoveList = new ArrayList<SoftFile>();
				boolean isused;

				for (SoftFile oldSoftFile : oldSoftFileList) {
					isused = false;

					for (SoftFile newSoftFile : newSoftFileList) {
						if (oldSoftFile.getFilePath().equals(newSoftFile.getFilePath())) {
							isused = true;

							newSoftFileRemoveList.add(newSoftFile);

							if (!oldSoftFile.getFileMd5().equals(newSoftFile.getFileMd5())) {
								Map softUpgradeMap = new HashMap();
								softUpgradeMap.put("id", GUIDGenerator.getUUID32());
								softUpgradeMap.put("oldSoftId", oldSoftFile.getSoftId());
								softUpgradeMap.put("newSoftId", newSoftFile.getSoftId());
								softUpgradeMap.put("softFileId", newSoftFile.getId());
								softUpgradeMap.put("updateType", "add");

								softUpgradeList.add(softUpgradeMap);
							}
						}
					}

					if (!isused) {
						Map softUpgradeMap = new HashMap();
						softUpgradeMap.put("id", GUIDGenerator.getUUID32());
						softUpgradeMap.put("oldSoftId", oldSoftFile.getSoftId());
						softUpgradeMap.put("newSoftId", newSoftId);
						softUpgradeMap.put("softFileId", oldSoftFile.getId());
						softUpgradeMap.put("updateType", "delete");

						softUpgradeList.add(softUpgradeMap);
					}
				}

				for (SoftFile newSoftFile : newSoftFileList) {
					if (!newSoftFileRemoveList.contains(newSoftFile)) {
						Map softUpgradeMap = new HashMap();
						softUpgradeMap.put("id", GUIDGenerator.getUUID32());
						softUpgradeMap.put("oldSoftId", oldSoftId);
						softUpgradeMap.put("newSoftId", newSoftFile.getSoftId());
						softUpgradeMap.put("softFileId", newSoftFile.getId());
						softUpgradeMap.put("updateType", "add");

						// softUpgradeMap.put("absoluteUrl",
						// newSoftFile.getAbsoluteUrl());
						// softUpgradeMap.put("filePath",
						// newSoftFile.getFilePath());

						softUpgradeList.add(softUpgradeMap);
					}
				}
			}
			if (softUpgradeList != null && softUpgradeList.size() > 0) {
				// //TODO 将新增的文件打包到一个压缩包
				// Map<String, Object> ss = new HashMap<String, Object>();
				// for (Map map : softUpgradeList) {
				// String key = map.get("oldSoftId").toString() + "|" +
				// map.get("newSoftId").toString();
				// List<Map<String, String>> fileList = (List<Map<String,
				// String>>) ss.get(key);
				//
				// if (fileList == null)
				// fileList = new ArrayList<Map<String, String>>();
				//
				// Map<String, String> filMap = new HashMap<String, String>();
				// filMap.put("absolute_url",
				// map.get("absoluteUrl").toString());
				// filMap.put("file_path", map.get("filePath").toString());
				// fileList.add(filMap);
				// ss.put(key, fileList);
				// }
				//
				// for (Map.Entry<String, Object> entry : ss.entrySet()) {
				// String key = entry.getKey();
				// List<Map<String, String>> fileList = (List<Map<String,
				// String>>) entry.getValue();
				// String[] softIds = key.split("|");
				// String oldSoftId = softIds[0];
				// String newSoftId1 = softIds[1];
				//// String absoluteUrl =
				// String archive = SysConfig.getStrValue("file_path_pre") +
				// SysConfig.getStrValue("soft_upload_dir")
				// + "upgrade" + File.separator + GUIDGenerator.getUUID32() +
				// ".zip";
				// // TODO zz
				// ZipCompress.writeFileToZip(fileList, archive, "");
				// String absoluteUrl = archive;
				// Map<String,Object> map = new HashMap<String,Object>();
				// map.put("id",GUIDGenerator.getUUID32());
				// map.put("oldSoftId", oldSoftId);
				// map.put("newSoftId", newSoftId1);
				// map.put("absoluteUrl", absoluteUrl);
				// softDao.saveSoftFilePackage(map);
				// }

				softDao.saveSoftFileUpgradeBatch(softUpgradeList);
			}
		}
	}

	/**
	 * 解压客户端完整包文件
	 */
	private String unZipSoft(String filePath) throws Exception {
		// 校验客户端数据合法性
		if (StringUtils.isEmpty(filePath)) {
			logger.info("要解压的客户端完整包文件不存在。");
			return null;
		}
		File zipFile = new File(filePath);
		if (!zipFile.exists()) {
			logger.info("要解压的客户端完整包文件不存在。");
			return null;
		}

		// 客户端文件解压后存放的路径
		int lastIndex = filePath.lastIndexOf(File.separator);
		String zipFileName = filePath.substring(lastIndex + 1);
		String unzipfilePath = filePath.substring(0, lastIndex + 1) + "unzip" + File.separator
				+ zipFileName.substring(0, zipFileName.lastIndexOf(".")) + File.separator;

		// 清除旧文件
		DelFile.delFolder(unzipfilePath);
		File clientUnzip_f = new File(unzipfilePath);
		clientUnzip_f.mkdir();

		try {
			// 开始解压zip文件
			// MyZipUtil.upZIPFile(filePath, unzipfilePath);
			ZipCompress.readByApacheZipFile(filePath, unzipfilePath);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("解压异常");
		}
		return unzipfilePath;
	}

	/**
	 * 读取客户端解压的所有文件
	 * 
	 * @param filePath
	 *            读取文件的路径
	 * @param softId
	 *            客户端的ID
	 * @param unzipfilePath
	 *            客户端的解压路径，用于获取文件的相对路径
	 * @return
	 */
	private List<SoftFile> readfile(String filePath, String softId, String unzipfilePath) {
		List<SoftFile> list = new ArrayList<SoftFile>();
		SoftFile softFile;
		File file = new File(filePath);
		if (!file.isDirectory()) { // 如果是单个文件
			softFile = new SoftFile();
			softFile.setId(GUIDGenerator.getUUID32());
			softFile.setSoftId(softId);
			softFile.setAbsoluteUrl(file.getPath());
			softFile.setFileName(file.getName());
			String relative = file.getPath().replace(unzipfilePath, "");
			if (relative.indexOf(File.separator) != -1) {
				relative = relative.substring(relative.indexOf(File.separator));
			}
			softFile.setFilePath(relative);
			softFile.setFileSize(file.length() + "");
			softFile.setFileMd5(MD5Filter.getMd5ByFile(file));
			list.add(softFile);
		} else if (file.isDirectory()) {// 如果是文件夹目录
			String[] filelist = file.list();
			for (int i = 0; i < filelist.length; i++) {
				File readfile = new File(filePath + File.separator + filelist[i]);
				if (!readfile.isDirectory()) {
					softFile = new SoftFile();
					softFile.setId(GUIDGenerator.getUUID32());
					softFile.setSoftId(softId);
					softFile.setAbsoluteUrl(readfile.getPath());
					softFile.setFileName(readfile.getName());
					String relative = readfile.getPath().replace(unzipfilePath, "");
					if (relative.indexOf(File.separator) != -1) {
						relative = relative.substring(relative.indexOf(File.separator));
					}
					softFile.setFilePath(relative);
					softFile.setFileSize(readfile.length() + "");
					softFile.setFileMd5(MD5Filter.getMd5ByFile(readfile));
					list.add(softFile);
				} else if (readfile.isDirectory()) {
					list.addAll(readfile(filePath + File.separator + filelist[i], softId, unzipfilePath));
				}
			}

		}
		return list;
	}

	/**
	 * 查询指定用户升级列表
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public HandlerResult querySetUserUpgrade(Map map) {
		logger.info("queryPage：" + map);
		HandlerResult handlerResult = new HandlerResult();
		handlerResult.setResultList(softDao.querySetUserUpgrade(map));
		return handlerResult;
	}

	/**
	 * 指定用户升级
	 * 
	 * @param softId
	 *            客户端ID
	 * @param allIds
	 *            当前列表所有用户逻辑ID
	 * @param ids
	 *            当前选中的用户逻辑ID
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void setUserUpgrade(String softId, List allIdList, List idList) {
		Map map = new HashMap();
		map.put("softId", softId);
		if (allIdList != null && allIdList.size() > 0) {
			map.put("list", allIdList);
			softDao.deleteSoftUserUpgrade(map);
		}
		if (idList != null && idList.size() > 0) {
			map.put("list", idList);
			softDao.setUserUpgrade(map);
		}
		// 查询指定用户数，修改指定用户的状态
		map.clear();
		map.put("softId", softId);
		List<Map> userList = softDao.querySoftUserUpgrade(map);
		Soft soft = new Soft();
		soft.setId(softId);
		if (userList != null && userList.size() > 0) {
			soft.setUserUpdate("1");
		} else {
			soft.setUserUpdate("0");
		}
		soft.setAllUserUpgrade("0");
		softDao.updateSoft(soft);

		// 指定用户升级时，删除冲突的版本升级数据
		if ("1".equals(soft.getUserUpdate())) {
			map.clear();
			map.put("softId", soft.getId());
			map.put("userUpdate", "1");
			// 删除对应的升级包
			this.removeUpgradePackageForConflict(map);
			// 删除冲突的版本关联数据
			softDao.deleteVersionRelConflict(map);

			softDao.deleteSoftUpgradeConflict(map);
		}
	}

	/**
	 * 判断是否指定用户升级
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public boolean canUpgrade(Map paramap) {
		List<Map> list = softDao.querySoftUserUpgrade(paramap);
		return list != null && list.size() > 0;
	}

	@Override
	public void openDownload(Soft soft) {
		// 先让当前可下载的安装包不能下载
		softDao.updateNoDownloadExe(soft);
		// 在开放当前安装包为可下载
		softDao.updateSoft(soft);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Soft getSoft(String id) {
		Map param = new HashMap();
		param.put("id", id);
		List<Soft> list = softDao.querySoft(param);
		if (null != list && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public void updateSoftUpgradeFileOldPath(String id) {
		softDao.updateSoftUpgradeFileOldPath(id);
	}

	@Override
	public void updateSoftUpgradeFileTmpfs(String id) {
		softDao.updateSoftUpgradeFileTmpfs(id);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Soft querySoftByMap(Map paramMap) {
		return softDao.querySoftByMap(paramMap);
	}

	/**
	 * 查询升级版本信息
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public HandlerResult queryUpgradeSoft(Map paramMap) {
		HandlerResult rs = new HandlerResult();
		rs.setResultList(softDao.queryUpgradeSoft(paramMap));
		return rs;
	}

	/**
	 * 查询升级版本信息详情
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Soft queryUpgradeSoftDetail(Map paramMap) {
		List<Soft> list = softDao.queryUpgradeSoft(paramMap);
		if (list.size() == 1) {
			return list.get(0);
		} else {
			return null;
		}
	}

	/**
	 * 创建升级 1.生成升级文件列表 2.修改最新版本的状态 3.建立升级版本和最新版本的关系
	 * 
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void createUpgradeSoft(Soft soft, List<String> upgradeSoftIdList) throws FileNotFoundException, IOException {
		// 1
		this.createSoftUpgradeFile(soft.getId(), upgradeSoftIdList);
		// 2
		softDao.updateSoft(soft);
		// 3
		Map paramMap = new HashMap();
		paramMap.put("softId", soft.getId());
		paramMap.put("list", upgradeSoftIdList);
		softDao.insertUpgradeSoftRel(paramMap);
	}

	/**
	 * 编辑升级 1.查询出目前升级版本列表 2.删除去掉版本的升级文件数据 3.生成添加版本的升级文件数据 4.修改最新版本的状态
	 * 5.删除指定用户升级数据 6.建立升级版本和最新版本的关系
	 * 
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void editUpgradeSoft(Soft soft, List<String> upgradeSoftIdList) throws FileNotFoundException, IOException {
		// 1
		List<String> currUpgradeSoftId = softDao.queryUpgradeSoftId(soft.getId());

		// 2
		List<String> tempList = new ArrayList<String>(currUpgradeSoftId);
		List<String> addList = new ArrayList<String>(upgradeSoftIdList);

		tempList.retainAll(upgradeSoftIdList);// 去交集

		currUpgradeSoftId.removeAll(tempList);
		addList.removeAll(tempList);

		if (currUpgradeSoftId.size() > 0) {
			Map paramMap = new HashMap();
			paramMap.put("newSoftId", soft.getId());
			paramMap.put("list", currUpgradeSoftId);
			softDao.deleteSoftUpgrade(paramMap);
		}

		// 3
		if (addList.size() > 0) {
			this.createSoftUpgradeFile(soft.getId(), addList);
		}

		// 4
		soft.setUserUpdate("0");
		soft.setAllUserUpgrade("0");
		softDao.updateSoft(soft);

		// 5
		Map paramMap = new HashMap();
		paramMap.put("softId", soft.getId());
		softDao.deleteSoftUserUpgrade(paramMap);

		// 6
		// 删除对应的升级包
		this.removeUpgradePackage(paramMap);
		// 建立升级版本和最新版本的关系
		softDao.deleteUpgradeSoftRel(paramMap);

		paramMap.put("list", upgradeSoftIdList);
		softDao.insertUpgradeSoftRel(paramMap);
	}

	/**
	 * 删除升级 1.修改版本的升级状态 2.删除升级文件列表 3.删除升级版本的关联数据 4.删除指定用户升级的数据
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteUpgradeSoft(String softId) {
		// 1
		Soft soft = new Soft();
		soft.setId(softId);
		soft.setUserUpdate("0");
		soft.setIsleveup("0");
		;
		soft.setAllUserUpgrade("0");
		softDao.updateSoft(soft);

		// 2
		Map paramMap = new HashMap();
		paramMap.put("newSoftId", softId);
		softDao.deleteSoftUpgrade(paramMap);

		// 3
		paramMap.clear();
		paramMap.put("softId", softId);
		// 删除对应的升级包
		this.removeUpgradePackage(paramMap);
		// 删除升级版本的关联数据
		softDao.deleteUpgradeSoftRel(paramMap);

		// 4
		softDao.deleteSoftUserUpgrade(paramMap);

	}

	/**
	 * 创建升级文件列表 1.查询当前版本的文件列表 2.与老版本对比生成升级文件列表
	 * 
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void createSoftUpgradeFile(String newSoftId, List<String> oldSoftIdList)
			throws FileNotFoundException, IOException {
		// 1
		Map paramMap = new HashMap();
		paramMap.put("softId", newSoftId);
		List<SoftFile> newSoftFileList = softDao.querySoftFile(paramMap);

		/*
		 * 2.与老版本对比生成升级文件列表: <1>当文件相对路径和md5值都相同时，不需要更新；
		 * <2>当文件相对路径相同并且md5值不相同时，更新类型为"add"; <3>老客户端文件列表中剩余文件的更新类型为"delete";
		 * <4>新客户端文件列表中剩余文件的更新类型为"add"。
		 */
		List<Map> softUpgradeList = new ArrayList<Map>();

		List<SoftFile> oldSoftFileList;
		for (String oldSoftId : oldSoftIdList) {

			Map map = new HashMap();
			map.put("softId", oldSoftId);
			oldSoftFileList = softDao.querySoftFile(map);

			// 客户端文件列表中已经做过匹配的文件
			List<SoftFile> newSoftFileRemoveList = new ArrayList<SoftFile>();
			boolean isused;

			for (SoftFile oldSoftFile : oldSoftFileList) {
				isused = false;

				for (SoftFile newSoftFile : newSoftFileList) {
					if (oldSoftFile.getFilePath().equals(newSoftFile.getFilePath())) {
						isused = true;

						newSoftFileRemoveList.add(newSoftFile);

						if (!oldSoftFile.getFileMd5().equals(newSoftFile.getFileMd5())) {
							Map softUpgradeMap = new HashMap();
							softUpgradeMap.put("id", GUIDGenerator.getUUID32());
							softUpgradeMap.put("oldSoftId", oldSoftFile.getSoftId());
							softUpgradeMap.put("newSoftId", newSoftFile.getSoftId());
							softUpgradeMap.put("softFileId", newSoftFile.getId());
							softUpgradeMap.put("updateType", "add");

							softUpgradeMap.put("absoluteUrl", newSoftFile.getAbsoluteUrl());
							softUpgradeMap.put("filePath", newSoftFile.getFilePath());

							softUpgradeList.add(softUpgradeMap);
						}
					}
				}

				if (!isused) {
					Map softUpgradeMap = new HashMap();
					softUpgradeMap.put("id", GUIDGenerator.getUUID32());
					softUpgradeMap.put("oldSoftId", oldSoftFile.getSoftId());
					softUpgradeMap.put("newSoftId", newSoftId);
					softUpgradeMap.put("softFileId", oldSoftFile.getId());
					softUpgradeMap.put("updateType", "delete");

					softUpgradeList.add(softUpgradeMap);
				}
			}

			for (SoftFile newSoftFile : newSoftFileList) {
				if (!newSoftFileRemoveList.contains(newSoftFile)) {
					Map softUpgradeMap = new HashMap();
					softUpgradeMap.put("id", GUIDGenerator.getUUID32());
					softUpgradeMap.put("oldSoftId", oldSoftId);
					softUpgradeMap.put("newSoftId", newSoftFile.getSoftId());
					softUpgradeMap.put("softFileId", newSoftFile.getId());
					softUpgradeMap.put("updateType", "add");

					softUpgradeMap.put("absoluteUrl", newSoftFile.getAbsoluteUrl());
					softUpgradeMap.put("filePath", newSoftFile.getFilePath());

					softUpgradeList.add(softUpgradeMap);
				}
			}
		}
		if (softUpgradeList != null && softUpgradeList.size() > 0) {

			// //TODO 将新增的文件打包到一个压缩包
			// Map<String, Object> ss = new HashMap<String, Object>();
			// for (Map map : softUpgradeList) {
			// String key = map.get("oldSoftId").toString() + "|" +
			// map.get("newSoftId").toString();
			// List<Map<String, String>> fileList = (List<Map<String, String>>)
			// ss.get(key);
			//
			// if(map.get("updateType").equals("delete"))
			// continue;
			//
			// if (fileList == null)
			// fileList = new ArrayList<Map<String, String>>();
			//
			// Map<String, String> filMap = new HashMap<String, String>();
			// filMap.put("absolute_url", map.get("absoluteUrl").toString());
			// filMap.put("file_path", map.get("filePath").toString());
			// fileList.add(filMap);
			// ss.put(key, fileList);
			// }
			//
			// for (Map.Entry<String, Object> entry : ss.entrySet()) {
			// String key = entry.getKey();
			// List<Map<String, String>> fileList = (List<Map<String, String>>)
			// entry.getValue();
			// String[] softIds = key.split("|");
			// String oldSoftId = softIds[0];
			// String newSoftId1 = softIds[1];
			//// String absoluteUrl =
			// String archive = SysConfig.getStrValue("file_path_pre") +
			// SysConfig.getStrValue("soft_upload_dir")
			// + "upgrade" + File.separator + GUIDGenerator.getUUID32() +
			// ".zip";
			// // TODO zz
			// ZipCompress.writeFileToZip(fileList, archive, "");
			// String absoluteUrl = archive;
			// Map<String,Object> map = new HashMap<String,Object>();
			// map.put("id",GUIDGenerator.getUUID32());
			// map.put("oldSoftId", oldSoftId);
			// map.put("newSoftId", newSoftId1);
			// map.put("absoluteUrl", absoluteUrl);
			// softDao.saveSoftFilePackage(map);
			// }
			softDao.saveSoftFileUpgradeBatch(softUpgradeList);
		}
	}

	/**
	 * 查询所有的绿色包版本号
	 */
	@SuppressWarnings("rawtypes")
	public List<Soft> queryVersionCode(Map paramap) {
		List<Soft> list = softDao.queryVersionCode(paramap);

		return this.sortList(list);
	}

	/**
	 * 取比当前版本小的，不是最新版本，没有当前升级类型的
	 */
	@SuppressWarnings("rawtypes")
	public List<Soft> queryUpgradeVersionCode(Map paramMap) {
		List<Soft> list = softDao.queryVersionCode(paramMap);
		String versionCode = paramMap.get("versionCode").toString();
		List<Soft> tempList = new ArrayList<Soft>();
		for (Soft soft : list) {
			if (compareVersionCode(soft.getVersionCode(), versionCode) < 0) {
				tempList.add(soft);
			}
		}

		return this.sortList(tempList);
	}

	/**
	 * 自定义版本的排序方法 倒序排序，根据版本号倒序
	 * 
	 * @param list
	 * @return
	 */
	private List<Soft> sortList(List<Soft> list) {
		Comparator<Soft> cmp = Collections.reverseOrder(new Comparator<Soft>() {
			public int compare(Soft o1, Soft o2) {
				return compareVersionCode(o1.getVersionCode(), o2.getVersionCode());
			}
		});
		Collections.sort(list, cmp);
		return list;
	}

	private int compareVersionCode(String str1, String str2) {
		List<String> list1 = new ArrayList<String>();
		List<String> list2 = new ArrayList<String>();
		list1.addAll(Arrays.asList(str1.split("\\.")));
		list2.addAll(Arrays.asList(str2.split("\\.")));
		return compareList(list1, list2);
	}

	private int compareList(List<String> list1, List<String> list2) {
		int size1 = list1.size();
		int size2 = list2.size();
		if (size1 > 0 && size2 > 0) {
			String str1 = list1.get(0);
			String str2 = list2.get(0);
			int result = str1.compareTo(str2);
			list1.remove(0);
			list2.remove(0);

			return result != 0 ? result : compareList(list1, list2);
		} else if (size1 > 0 && size2 == 0) {
			return 1;
		} else if (size1 == 0 && size2 > 0) {
			return -1;
		} else if (size1 == 0 && size2 == 0) {
			return 0;
		}
		return 0;
	}

	// 根据用户id，所属平台编码，用户版本号查询新版本列表
	@SuppressWarnings("rawtypes")
	@Override
	public List<CheckNewSoft> newSoftList(Map map) {
		return softDao.newSoftList(map);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Map> newSoftList_1(Map map) {
		return softDao.newSoftList_1(map);
	}

	// 根据用户版本号,参数tag查询新版本列表
	@SuppressWarnings("rawtypes")
	@Override
	public List<CheckNewSilentSoft> newSilentSoftList(Map map) {
		return softDao.newSilentSoftList(map);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Map> newSilentSoftList_1(Map map) {
		return softDao.newSilentSoftList_1(map);
	}

	// 根据用户版本号,最新版本id查询新版本文件列表
	@SuppressWarnings("rawtypes")
	@Override
	public List<ClientFile> clientFileList(Map map) {
		return softDao.clientFileList(map);
	}

	// 根据用户id，用户版本号查询最新版本列表
	@SuppressWarnings("rawtypes")
	@Override
	public List<Soft> queryNewSoftList(Map paramMap) {
		return softDao.queryNewSoftList(paramMap);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setAllUserUpgrade(Soft soft) {
		softDao.updateSoft(soft);
		// 删除指定用户升级数据
		Map<String, Object> map = new HashMap();
		map.put("softId", soft.getId());
		softDao.deleteSoftUserUpgrade(map);
		// 指定全部用户升级时，删除冲突的版本升级数据
		if ("1".equals(soft.getAllUserUpgrade())) {
			map.clear();
			map.put("softId", soft.getId());
			map.put("allUserUpgrade", "1");
			// 删除对应的升级包
			this.removeUpgradePackageForConflict(map);
			// 删除对应的关联数据
			softDao.deleteVersionRelConflict(map);

			softDao.deleteSoftUpgradeConflict(map);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void createUpgradePackage(String softId) throws Exception {
		List<Map> fileList = softDao.queryUpgradeFileList(softId);
		if (fileList == null || fileList.size() == 0) {
			return;
		}

		Map<String, Object> upgradeFile = new HashMap<String, Object>();
		// 分离出个个版本的文件列表
		String old_soft_id;
		Map<String, String> map;
		List<Map<String, String>> list;
		Map<String, Object> oldSoftMap;
		for (Map fileMap : fileList) {
			old_soft_id = fileMap.get("OLD_SOFT_ID").toString();
			map = new HashMap();
			map.put("absolute_url", fileMap.get("ABSOLUTE_URL").toString());
			map.put("file_path", fileMap.get("FILE_PATH").toString());
			if (upgradeFile.get(old_soft_id) == null) {
				oldSoftMap = new HashMap<String, Object>();
				String archive = SysConfig.getStrValue("file_path_pre") + SysConfig.getStrValue("soft_upload_dir")
						+ "upgrade" + File.separator + GUIDGenerator.getUUID32() + ".zip";
				list = new ArrayList<Map<String, String>>();
				list.add(map);

				oldSoftMap.put("archive", archive);
				oldSoftMap.put("list", list);

				upgradeFile.put(old_soft_id, oldSoftMap);
			} else {
				((List) ((Map) upgradeFile.get(old_soft_id)).get("list")).add(map);
			}
		}

		// 生成压缩包
		if (!upgradeFile.isEmpty()) {
			List dataList = new ArrayList();
			long file_size;
			Map<String, Object> dataMap;
			for (Map.Entry<String, Object> entity : upgradeFile.entrySet()) {
				old_soft_id = entity.getKey();
				oldSoftMap = (Map<String, Object>) entity.getValue();
				file_size = ZipCompress.writeFileToZip((List) oldSoftMap.get("list"),
						oldSoftMap.get("archive").toString(), "");

				dataMap = new HashMap();
				dataMap.put("new_soft_id", softId);
				dataMap.put("old_soft_id", old_soft_id);
				dataMap.put("upgrade_package", oldSoftMap.get("archive").toString());
				dataMap.put("file_size", file_size);
				dataList.add(dataMap);
			}
			softDao.updateUpgradePackage(dataList);
		}
	}

	/**
	 * 客户端查询增量升级包
	 */
	@SuppressWarnings("rawtypes")
	public Map queryUpgradePackage(Map param) {
		return softDao.queryUpgradePackage(param);
	}

	/**
	 * 删除版本关联数据时，删除升级包
	 * 
	 * @param paramMap
	 */
	@SuppressWarnings("rawtypes")
	private void removeUpgradePackage(Map paramMap) {
		List<String> upgradePackageList = softDao.queryUpgradeSoftRel(paramMap);
		for (String upgradePackage : upgradePackageList) {
			if (StringUtils.isNotEmpty(upgradePackage)) {
				File upgradePackageFile = new File(upgradePackage);
				if (upgradePackageFile.exists()) {
					upgradePackageFile.deleteOnExit();
					try {
						FileUtils.forceDelete(upgradePackageFile);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	/**
	 * 删除冲突的版本关联数据时，删除对应的升级包
	 * 
	 * @param paramMap
	 */
	@SuppressWarnings("rawtypes")
	private void removeUpgradePackageForConflict(Map paramMap) {
		List<String> upgradePackageList = softDao.queryVersionRelConflict(paramMap);
		for (String upgradePackage : upgradePackageList) {
			if (StringUtils.isNotEmpty(upgradePackage)) {
				File upgradePackageFile = new File(upgradePackage);
				if (upgradePackageFile.exists()) {
					upgradePackageFile.deleteOnExit();
					try {
						FileUtils.forceDelete(upgradePackageFile);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	@Override
	public HandlerResult queryWidgetPage(Map paramMap) {
		logger.info("queryPage:" + paramMap);
		HandlerResult rs = new HandlerResult();
		rs.setResultList(softDao.queryWidgetPage(paramMap));
		return rs;
	}

	@Override
	public List<SoftAreaUpgrade> querySoftAreaUpgrade(Map<String, Object> paramMap) {
		return softDao.queryAreaSoft(paramMap);
	}

	@Override
	public HandlerResult querySoftAreaUpgradePage(Map<String, Object> paramMap) {
		logger.info("queryPage:" + paramMap);
		HandlerResult rs = new HandlerResult();
		rs.setResultList(softDao.queryAreaSoft(paramMap));
		return rs;
	}

	@Override
	public void saveSoftAreaUpgrade(Map<String, Object> softAreaMap) {
		softDao.saveSoftAreaUpgrade(softAreaMap);
	}

	@Override
	public void deleteSoftAreaUpgrade(Map<String, Object> param) {
		softDao.deleteSoftAreaUpgrade(param);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.assis.demo.service.SoftService#ListUpgradeSoft(java.util.Map)
	 */
	@Override
	public List<Soft> ListUpgradeSoft(Map<String, Object> paramMap) {
		return softDao.queryUpgradeSoft(paramMap);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.assis.demo.service.SoftService#updateBaiduBosStatus(com.whty.
	 * assis.demo.model.Soft)
	 */
	@Override
	public void updateBaiduBosStatus(Soft soft) {
		softDao.updateBaiduBosStatus(soft);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.demo.service.SoftService#loadById(java.lang.String)
	 */
	@Override
	public Soft loadById(String id) {
		return softDao.loadById(id);
	}

	@Override
	public List<String> queryVersions(String str) {
		return softDao.queryVersions(str);
	}

	@Override
	public void addClientVersion(Map<String, Object> param) {
		param.put("create_time", new Date());
		param.put("id", GUIDGenerator.getUUID32());
		softDao.addClientVersion(param);
	}

}
