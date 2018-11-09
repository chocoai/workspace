package com.whty.ebp.manage.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bos.BosClient;
import com.baidubce.services.bos.BosClientConfiguration;
import com.whty.common.util.CommonFunction;
import com.whty.common.util.DelFile;
import com.whty.common.util.GUIDGenerator;
import com.whty.common.util.MD5Filter;
import com.whty.common.util.MyZipUtil;
import com.whty.common.util.SysConfig;
import com.whty.ebp.base.service.BaseService;
import com.whty.ebp.manage.dao.AppDao;
import com.whty.ebp.manage.dao.FlatModelDao;
import com.whty.ebp.manage.dao.PlatformInfoDao;
import com.whty.ebp.manage.model.ApkInfo;
import com.whty.ebp.manage.model.EbpApp;
import com.whty.ebp.manage.model.EbpAppFile;
import com.whty.ebp.manage.service.AppService;
import com.whty.ebp.manage.utils.ApkUtils;
import com.whty.ebp.manage.utils.MD5Utils;
import com.whty.ebp.manage.utils.TimeUtil;
import com.whty.ebp.manage.utils.ZipCompress;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

@Service
public class AppServiceImpl extends BaseService implements AppService {

	@Autowired
	private AppDao appDao;

	@Autowired
	private FlatModelDao flatModelDao;

	@Autowired
	private PlatformInfoDao platformInfoDao;

	@Override
	public HandlerResult listProductByPage(Map<String, Object> param, PageContext page) {
		HandlerResult re = new HandlerResult();
		List<EbpApp> appList = appDao.listByCondition(param);
		re.setResultList(appList);
		re.setPage(page);
		return re;
	}

	/*
	 * 开放升级
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void openUpdate(Map map) {
		// 先修改可升级的产品应用为不可升级
		EbpApp ebpapp = appDao.queryById(map.get("id").toString());

		if (!ebpapp.getProduct_type().equals("5")) {
			appDao.updateNoUpdate(map);
		}

		// 再修改当前应用为可升级
		map.put("updateTime", new Date());
		appDao.updateByCondition(map);
	}

	/*
	 * 开放升级
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void openUpdate(String id, String productId, String canUpdate) {
		// 先修改本产品所有应用为不可升级
		Map map = new HashMap();
		map.put("productId", productId);
		appDao.updateNoUpdate(map);

		map.clear();
		map.put("id", id);
		map.put("can_update", canUpdate);
		map.put("updateTime", new Date());
		// 再修改当前应用为可升级
		appDao.updateByCondition(map);
	}

	@Override
	public void save(EbpApp ebpApp) throws Exception {
		// 保存应用
		ebpApp.setId(GUIDGenerator.getGUID());
		ebpApp.setUpdate_time(new Date());
		ebpApp.setCreate_time(new Date());
		ebpApp.setStatus("0");

		if ("-1".equals(ebpApp.getFlat_model_ids())) {
			ebpApp.setFlat_model_ids("");
		} else {
			// 保存映射

			String[] platformCodeArr = ebpApp.getPlatform_codes().split(",");

			if (platformCodeArr != null) {
				for (int i = 0; i < platformCodeArr.length; i++) {
					Map<String, Object> aa = new HashMap<String, Object>();
					String platformCode = platformCodeArr[i];
					aa.put("platformCode", platformCode);
					aa.put("ebpAppId", ebpApp.getId());

					if ((platformCode != null && !platformCode.equals(""))
							&& (ebpApp.getId() != null && !ebpApp.getId().equals(""))) {
						platformInfoDao.saveAppPlatformInfo(aa);
					}
				}
			}

			String[] flatModelIdArr = ebpApp.getFlat_model_ids().split(",");
			for (int i = 0; i < flatModelIdArr.length; i++) {
				Map<String, Object> aa = new HashMap<String, Object>();
				String flatModelId = flatModelIdArr[i];
				aa.put("flatModelId", flatModelId);
				aa.put("ebpAppId", ebpApp.getId());

				if ((flatModelId != null && !flatModelId.equals(""))
						&& (ebpApp.getId() != null && !ebpApp.getId().equals(""))) {
					flatModelDao.saveAppFlatModel(aa);
				}

			}
		}

		String ak = SysConfig.getStrValue("baidu.bos.ak");
		String sk = SysConfig.getStrValue("baidu.bos.sk");
		String bucketname = SysConfig.getStrValue("baidu.bos.bucketname");
		String pathProductName = SysConfig.getStrValue("baidu.bos.path.productname");
		String pathVersion = SysConfig.getStrValue("baidu.bos.path.version");
		String baiduBosUrl = SysConfig.getStrValue("baidu.bos.url");

		StringBuffer bosStringBuffer = new StringBuffer();
		Calendar cal = Calendar.getInstance();

		bosStringBuffer.append(pathProductName).append("/").append(pathVersion).append("/")
				.append(cal.get(Calendar.YEAR)).append(cal.get(Calendar.MONTH)).append(cal.get(Calendar.DAY_OF_MONTH))
				.append("/").append(cal.getTime().getTime());

		BosClientConfiguration config = new BosClientConfiguration();
		config.setCredentials(new DefaultBceCredentials(ak, sk));

		BosClient client = new BosClient(config);


		File file = new File(ebpApp.getFile_path());
		ebpApp.setMd5(MD5Utils.getFileMD5(file));
		
		
		client.putObject(bucketname, bosStringBuffer + file.getName(), file);
		System.out.println(baiduBosUrl + bosStringBuffer + file.getName());
		ebpApp.setBaiduBosUrl(baiduBosUrl + bosStringBuffer + file.getName());

		if ("5".equals(ebpApp.getProduct_type())) {// 第三方应用则从应用中取出图标，保存到百度云
			String uploadDir = SysConfig.getStrValue("file_path_pre") + SysConfig.getStrValue("app_upload_dir")
					+ CommonFunction.getDateSampleString(new Date()) + File.separator;

			ApkInfo apkInfo = new ApkUtils().getApkInfo(ebpApp.getFile_path());
			String decompressDir = uploadDir + apkInfo.getApplicationLable();// 解压路径
			String archive = ebpApp.getFile_path();// 压缩包路径
			ZipCompress.readByApacheZipFile(archive, decompressDir);

			// 上传到百度
			File file2 = new File(decompressDir + File.separator + apkInfo.getApplicationIcon());
			String url = "ebookpackage/appIcon/" + apkInfo.getApplicationLable() + "/"
					+ TimeUtil.date2String(new Date(), TimeUtil.STR_DATETIME_PATTERN) + "/" + file2.getName();
			client.putObject(bucketname, url, file2);// 上传文件
			ebpApp.setIconUrl("http://whty.bj.bcebos.com/" + url);
		}
		
		
		
		appDao.save(ebpApp);

	}

	@Override
	public void update(EbpApp ebpApp) {
		flatModelDao.deleteByEbpAppId(ebpApp.getId());// 先删除
		platformInfoDao.deleteByEbpAppId(ebpApp.getId());// 先删除
		if (ebpApp.getPlatform_codes() != null) {
			String[] platformCodeArr = ebpApp.getPlatform_codes().split(",");

			if (platformCodeArr != null) {
				for (int i = 0; i < platformCodeArr.length; i++) {
					Map<String, Object> aa = new HashMap<String, Object>();
					String platformCode = platformCodeArr[i];
					aa.put("platformCode", platformCode);
					aa.put("ebpAppId", ebpApp.getId());

					if ((platformCode != null && !platformCode.equals(""))
							&& (ebpApp.getId() != null && !ebpApp.getId().equals(""))) {
						platformInfoDao.saveAppPlatformInfo(aa);
					}
				}
			}
		}

		// 保存映射
		if (ebpApp.getFlat_model_ids() != null) {
			String[] flatModelIdArr = ebpApp.getFlat_model_ids().split(",");
			for (int i = 0; i < flatModelIdArr.length; i++) {
				Map<String, Object> aa = new HashMap<String, Object>();
				String flatModelId = flatModelIdArr[i];
				aa.put("flatModelId", flatModelId);
				aa.put("ebpAppId", ebpApp.getId());

				if ((flatModelId != null && !flatModelId.equals(""))
						&& (ebpApp.getId() != null && !ebpApp.getId().equals(""))) {
					flatModelDao.saveAppFlatModel(aa);
				}

				// flatModelDao.saveAppFlatModel(aa);
			}
		}

		appDao.update(ebpApp);
	}

	/**
	 * 查询各个产品最新的版本应用
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public List<EbpApp> getNewProductAppList(Map map) {
		List<EbpApp> list = appDao.getNewProductAppList(map);

		if (map.get("modelCode") != null && !"".equals(map.get("modelCode")) && map.get("platformCode") != null
				&& !"".equals(map.get("platformCode"))) {
			String modelCodeStr = map.get("modelCode").toString();
			String platformCodeStr = map.get("platformCode").toString();

			List<EbpApp> ebpAppList = appDao.queryNewProductAppListByModelCodeAndPlatformCodeMap(map);

			for (EbpApp bean : ebpAppList) {
				String flatModel = bean.getFlatModel();
				String platformCode = bean.getPlatformCode();
				if (flatModel != null && platformCode != null) {
					if (flatModel.contains(modelCodeStr) && platformCode.contains(platformCodeStr)) {
						list.add(bean);
					}
				}

				if (flatModel != null && platformCode == null) {
					// String flatMode = pkgMap.get("flat_model").toString();
					if (flatModel.contains(modelCodeStr)) {
						list.add(bean);
					}
				}

				if (platformCode != null && flatModel == null) {
					// String platformCode =
					// pkgMap.get("platform_code").toString();

					if (platformCode.contains(platformCodeStr)) {
						list.add(bean);
					}
				}
			}
		} else {
			if (map.get("modelCode") != null && !"".equals(map.get("modelCode"))) {

				List<EbpApp> pkgList = appDao.getNewProductAppModelCode(map);

				for (EbpApp pkgMap : pkgList) {
					// String pkgStr = pkgMap.get("pkg").toString();
					// Map<String, Object> pkgParam = new HashMap<String,
					// Object>();
					// pkgParam.put("pkg", pkgStr);

					if (pkgMap.getPlatformCode() == null) {
						list.add(pkgMap);
					}

					// if (pkgMap.get("platform_code") == null) {
					// list.add(pkgParam);
					// }

				}

				// list.addAll(appDao.getNewProductAppModelCode(map));
			}

			if (map.get("platformCode") != null && !"".equals(map.get("platformCode"))) {
				List<EbpApp> pkgList = appDao.getNewProductAppListPlatformCode(map);
				for (EbpApp pkgMap : pkgList) {
					// String pkgStr = pkgMap.get("pkg").toString();
					// Map<String, Object> pkgParam = new HashMap<String,
					// Object>();
					// pkgParam.put("pkg", pkgStr);

					if (pkgMap.getFlatModel() == null) {
						list.add(pkgMap);
					}
				}
			}
		}
		return list;
	}

	/*
	 * 根据id查询应用
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public EbpApp loadProductNewApp(Map map) {
		if(map.get("productType")==null){
			return appDao.loadProductNewApp(map);
		}else{
			String productType = map.get("productType").toString();
			
			if("5".equals(productType)){
				return appDao.loadApp(map);
			}else{
				return appDao.loadProductNewApp(map);
			}
		}
	}

	@Override
	public EbpApp queryById(String id) {
		return appDao.queryById(id);
	}

	/**
	 * 解压入库
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public boolean unzipApp(String appId) {
		EbpApp ebpApp = this.queryById(appId);
		if (ebpApp != null) {
			try {
				List idList = new ArrayList();
				idList.add(appId);
				// 删除绿色包客户端文件列表
				appDao.deleteAppFile(idList);
				// 删除对应生成的升级文件数据
				Map map = new HashMap();
				map.put("list", idList);
				appDao.deleteAppUpgradeFile(map);

				String unzipfilePath = this.unzip(ebpApp.getFile_path());
				List<EbpAppFile> list = this.readfile(unzipfilePath, appId, unzipfilePath);

				this.saveAppFile(list);

			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}

		return true;
	}

	/**
	 * 解压客户端完整包文件
	 */
	private String unzip(String filePath) throws Exception {
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
			MyZipUtil.upZIPFile(filePath, unzipfilePath);
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
	 * @param appId
	 *            客户端的ID
	 * @param unzipfilePath
	 *            客户端的解压路径，用于获取文件的相对路径
	 * @return
	 */
	private List<EbpAppFile> readfile(String filePath, String appId, String unzipfilePath) {
		List<EbpAppFile> list = new ArrayList<EbpAppFile>();
		EbpAppFile appFile;
		File file = new File(filePath);
		if (!file.isDirectory()) { // 如果是单个文件
			appFile = new EbpAppFile();
			appFile.setId(GUIDGenerator.getGUID());
			appFile.setAppId(appId);
			appFile.setAbsoluteUrl(file.getPath());
			appFile.setFileName(file.getName());
			String relative = file.getPath().replace(unzipfilePath, "");
			if (relative.indexOf(File.separator) != -1) {
				relative = relative.substring(relative.indexOf(File.separator));
			}
			appFile.setFilePath(relative);
			appFile.setFileSize(file.length() + "");
			appFile.setFileMd5(MD5Filter.getMd5ByFile(file));
			list.add(appFile);
		} else if (file.isDirectory()) {// 如果是文件夹目录
			String[] filelist = file.list();
			for (int i = 0; i < filelist.length; i++) {
				list.addAll(readfile(filePath + File.separator + filelist[i], appId, unzipfilePath));
			}

		}
		return list;
	}

	@Override
	public void saveAppFile(List<EbpAppFile> list) {
		if (list != null && list.size() > 0) {
			appDao.saveAppFileBatch(list);
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<EbpAppFile> queryAppFile(Map map) {
		return appDao.queryAppFile(map);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<EbpAppFile> queryAppUpgradeFile(Map map) {
		return appDao.queryAppUpgradeFile(map);
	}

	/**
	 * 创建升级文件列表 1.先清空升级文件表EBP_APP_FILE 2.查询当前版本的文件列表 3.与老版本对比生成升级文件列表
	 * 4.标记所有版本为不可升级
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void createAppUpgradeFile(String appId, String productId) {
		// 1
		Map map = new HashMap();
		map.put("productId", productId);
		appDao.deleteAppUpgradeFile(map);
		// 2
		Map paramMap = new HashMap();
		paramMap.put("appId", appId);
		List<EbpAppFile> newSoftFileList = appDao.queryAppFile(paramMap);
		// 3
		this.saveAppUpgradeFile(newSoftFileList, appId, productId);
		// 4
		// 修改本产品所有应用为不可升级
		appDao.updateNoUpdate(map);
	}

	/**
	 * 老版本对比生成升级文件列表
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void saveAppUpgradeFile(List<EbpAppFile> newAppFileList, String newAppId, String productId) {

		// 查询所有有效的版本列表
		Map paraMap = new HashMap();
		paraMap.put("product_id", productId);// 产品ID
		List<EbpApp> appList = appDao.listByCondition(paraMap);

		/*
		 * 对比生成老客户端到新客户端的文件升级列表: 1.当文件相对路径和md5值都相同时，不需要更新；
		 * 2.当文件相对路径相同并且md5值不相同时，更新类型为"add"; 3.老客户端文件列表中剩余文件的更新类型为"delete";
		 * 4.新客户端文件列表中剩余文件的更新类型为"add"。
		 */
		if (appList != null && appList.size() > 1) {
			List<Map> appUpgradeList = new ArrayList<Map>();

			List<EbpAppFile> oldAppFileList;
			for (EbpApp ebpApp : appList) {
				String oldAppId = ebpApp.getId();
				if (oldAppId.equals(newAppId))
					continue;

				Map map = new HashMap();
				map.put("appId", oldAppId);
				oldAppFileList = appDao.queryAppFile(map);

				// 客户端文件列表中已经做过匹配的文件
				List<EbpAppFile> newAppFileRemoveList = new ArrayList<EbpAppFile>();
				boolean isused;

				for (EbpAppFile oldAppFile : oldAppFileList) {
					isused = false;

					for (EbpAppFile newAppFile : newAppFileList) {
						if (oldAppFile.getFilePath().equals(newAppFile.getFilePath())) {
							isused = true;

							newAppFileRemoveList.add(newAppFile);

							if (!oldAppFile.getFileMd5().equals(newAppFile.getFileMd5())) {
								Map appUpgradeMap = new HashMap();
								appUpgradeMap.put("id", GUIDGenerator.getGUID());
								appUpgradeMap.put("oldAppId", oldAppFile.getAppId());
								appUpgradeMap.put("newAppId", newAppFile.getAppId());
								appUpgradeMap.put("appFileId", newAppFile.getId());
								appUpgradeMap.put("updateType", "add");
								appUpgradeMap.put("productId", productId);

								appUpgradeList.add(appUpgradeMap);
							}
						}
					}

					if (!isused) {
						Map appUpgradeMap = new HashMap();
						appUpgradeMap.put("id", GUIDGenerator.getGUID());
						appUpgradeMap.put("oldAppId", oldAppFile.getAppId());
						appUpgradeMap.put("newAppId", newAppId);
						appUpgradeMap.put("appFileId", oldAppFile.getId());
						appUpgradeMap.put("updateType", "delete");
						appUpgradeMap.put("productId", productId);

						appUpgradeList.add(appUpgradeMap);
					}
				}

				for (EbpAppFile newAppFile : newAppFileList) {
					if (!newAppFileRemoveList.contains(newAppFile)) {
						Map appUpgradeMap = new HashMap();
						appUpgradeMap.put("id", GUIDGenerator.getGUID());
						appUpgradeMap.put("oldAppId", oldAppId);
						appUpgradeMap.put("newAppId", newAppFile.getAppId());
						appUpgradeMap.put("appFileId", newAppFile.getId());
						appUpgradeMap.put("updateType", "add");
						appUpgradeMap.put("productId", productId);

						appUpgradeList.add(appUpgradeMap);
					}
				}
			}
			if (appUpgradeList != null && appUpgradeList.size() > 0) {
				appDao.saveAppFileUpgradeBatch(appUpgradeList);
			}
		}
	}

	@Override
	public EbpApp getNewyidongjiangtaiEbpApp() {
		return appDao.getNewyidongjiangtaiEbpApp();
	}

	@Override
	public String getbaiduDownUrl(String id) {
		return appDao.getbaiduDownUrl(id);
	}

	@Override
	public void updateMd5(EbpApp bean) {
		appDao.updateMd5(bean);
		
	}

}
