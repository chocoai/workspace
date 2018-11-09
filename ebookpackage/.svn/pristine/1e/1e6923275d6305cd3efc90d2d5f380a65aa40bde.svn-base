package com.whty.ebp.manage.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bos.BosClient;
import com.baidubce.services.bos.BosClientConfiguration;
import com.baidubce.services.bos.model.PutObjectResponse;
import com.whty.common.util.CommonFunction;
import com.whty.common.util.GUIDGenerator;
import com.whty.common.util.SysConfig;
import com.whty.ebp.manage.dao.DerivativeAppDao;
import com.whty.ebp.manage.dao.FlatModelDao;
import com.whty.ebp.manage.dao.PlatformInfoDao;
import com.whty.ebp.manage.model.ApkInfo;
import com.whty.ebp.manage.model.DerivativeApp;
import com.whty.ebp.manage.model.DerivativeAppApi;
import com.whty.ebp.manage.model.EbpApp;
import com.whty.ebp.manage.service.DerivativeAppService;
import com.whty.ebp.manage.utils.ApkUtils;
import com.whty.ebp.manage.utils.TimeUtil;
import com.whty.ebp.manage.utils.ZipCompress;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

@Service
public class DerivativeAppServiceImpl implements DerivativeAppService {

	@Autowired
	private DerivativeAppDao derivativeAppDao;

	@Autowired
	private FlatModelDao flatModelDao;

	@Autowired
	private PlatformInfoDao platformInfoDao;

	@Override
	public HandlerResult listDerivativeAppByPage(Map<String, Object> paraMap, PageContext page) {
		HandlerResult re = new HandlerResult();
		List<DerivativeApp> appList = derivativeAppDao.listByCondition(paraMap);
		re.setResultList(appList);
		re.setPage(page);
		return re;
	}

	@Override
	public void update(DerivativeApp bean) throws Exception {
		flatModelDao.deleteByDerivativeEbpAppId(bean.getId());// 先删除
		platformInfoDao.deleteByDerivativeEbpAppId(bean.getId());// 先删除

		if (bean.getPlatform_codes() != null) {
			String[] platformCodeArr = bean.getPlatform_codes().split(",");

			if (platformCodeArr != null) {
				for (int i = 0; i < platformCodeArr.length; i++) {
					Map<String, Object> aa = new HashMap<String, Object>();
					String platformCode = platformCodeArr[i];
					aa.put("platformCode", platformCode);
					aa.put("derivativeEbpAppId", bean.getId());

					if ((platformCode != null && !platformCode.equals(""))
							&& (bean.getId() != null && !bean.getId().equals(""))) {
						platformInfoDao.saveDerivativeAppPlatformInfo(aa);
					}
				}
			}
		}

		// 保存映射
		if (bean.getFlat_model_ids() != null) {
			String[] flatModelIdArr = bean.getFlat_model_ids().split(",");
			for (int i = 0; i < flatModelIdArr.length; i++) {
				Map<String, Object> aa = new HashMap<String, Object>();
				String flatModelId = flatModelIdArr[i];
				aa.put("flatModelId", flatModelId);
				aa.put("derivativeEbpAppId", bean.getId());

				if ((flatModelId != null && !flatModelId.equals(""))
						&& (bean.getId() != null && !bean.getId().equals(""))) {
					flatModelDao.saveDerivativeAppFlatModel(aa);
				}

				// flatModelDao.saveAppFlatModel(aa);
			}
		}

		derivativeAppDao.update(bean);
	}

	@Override
	public void save(DerivativeApp bean) throws Exception {

		// 保存应用
		bean.setId(GUIDGenerator.getGUID());
		bean.setCreateTime(new Date());
		bean.setStatus("0");
		bean.setCanUpdate("0");
		bean.setUpdateTime(new Date());
		if ("-1".equals(bean.getFlat_model_ids())) {
			bean.setFlat_model_ids("");
		} else {
			// 保存映射

			String[] platformCodeArr = bean.getPlatform_codes().split(",");

			if (platformCodeArr != null) {
				for (int i = 0; i < platformCodeArr.length; i++) {
					Map<String, Object> aa = new HashMap<String, Object>();
					String platformCode = platformCodeArr[i];
					aa.put("platformCode", platformCode);
					aa.put("derivativeEbpAppId", bean.getId());

					if ((platformCode != null && !platformCode.equals(""))
							&& (bean.getId() != null && !bean.getId().equals(""))) {
						platformInfoDao.saveDerivativeAppPlatformInfo(aa);
					}
				}
			}

			String[] flatModelIdArr = bean.getFlat_model_ids().split(",");
			for (int i = 0; i < flatModelIdArr.length; i++) {
				Map<String, Object> aa = new HashMap<String, Object>();
				String flatModelId = flatModelIdArr[i];
				aa.put("flatModelId", flatModelId);
				aa.put("derivativeEbpAppId", bean.getId());

				if ((flatModelId != null && !flatModelId.equals(""))
						&& (bean.getId() != null && !bean.getId().equals(""))) {
					flatModelDao.saveDerivativeAppFlatModel(aa);
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

		// File f = fi.getStoreLocation();

		File file = new File(bean.getTmpFilePath());

		PutObjectResponse putObjectResponse;
		putObjectResponse = client.putObject(bucketname, bosStringBuffer + file.getName(), file);
		System.out.println(baiduBosUrl + bosStringBuffer + file.getName());
		bean.setBaiduBosUrl(baiduBosUrl + bosStringBuffer + file.getName());

		String uploadDir = SysConfig.getStrValue("file_path_pre") + SysConfig.getStrValue("app_upload_dir")
				+ CommonFunction.getDateSampleString(new Date()) + File.separator;

		ApkInfo apkInfo = new ApkUtils().getApkInfo(bean.getTmpFilePath());
		String decompressDir = uploadDir + apkInfo.getApplicationLable();// 解压路径
		String archive = bean.getTmpFilePath();// 压缩包路径
		ZipCompress.readByApacheZipFile(archive, decompressDir);

		// 上传到百度
		File file2 = new File(decompressDir + File.separator + apkInfo.getApplicationIcon());
		String url = "ebookpackage/appIcon/" + apkInfo.getApplicationLable() + "/"
				+ TimeUtil.date2String(new Date(), TimeUtil.STR_DATETIME_PATTERN) + "/" + file2.getName();
		client.putObject(bucketname, url, file2);// 上传文件
		bean.setIconUrl("http://whty.bj.bcebos.com/" + url);

		derivativeAppDao.save(bean);
	}

	@Override
	public void openUpdate(Map map) {
		// 再修改当前应用为可升级
		map.put("updateTime", new Date());
		derivativeAppDao.updateByCondition(map);

	}

	@Override
	public void delete(String id) {
		flatModelDao.deleteByDerivativeEbpAppId(id);// 先删除
		platformInfoDao.deleteByDerivativeEbpAppId(id);// 先删除

		derivativeAppDao.deleteById(id);
	}

	@Override
	public DerivativeApp queryById(String id) {
		return derivativeAppDao.loadById(id);

	}

	@Override
	public List<DerivativeAppApi> getAPPLineList(Map map) {
		List<DerivativeAppApi> list = derivativeAppDao.getNewProductAppList(map);

		if (map.get("modelCode") != null && !"".equals(map.get("modelCode")) && map.get("platformCode") != null
				&& !"".equals(map.get("platformCode"))) {
			String modelCodeStr = map.get("modelCode").toString();
			String platformCodeStr = map.get("platformCode").toString();

			List<DerivativeAppApi> ebpAppList = derivativeAppDao
					.queryNewProductAppListByModelCodeAndPlatformCodeMap(map);

			for (DerivativeAppApi bean : ebpAppList) {
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

				List<DerivativeAppApi> pkgList = derivativeAppDao.getNewProductAppModelCode(map);

				for (DerivativeAppApi pkgMap : pkgList) {
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
				List<DerivativeAppApi> pkgList = derivativeAppDao.getNewProductAppListPlatformCode(map);
				for (DerivativeAppApi pkgMap : pkgList) {
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
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.ebp.manage.service.DerivativeAppService#getAPPLineList2(java.
	 * util.Map)
	 */
	@Override
	public List<EbpApp> getAPPLineList2(Map map) {
		List<DerivativeAppApi> list = derivativeAppDao.getNewProductAppList(map);

		if (map.get("modelCode") != null && !"".equals(map.get("modelCode")) && map.get("platformCode") != null
				&& !"".equals(map.get("platformCode"))) {
			String modelCodeStr = map.get("modelCode").toString();
			String platformCodeStr = map.get("platformCode").toString();

			List<DerivativeAppApi> ebpAppList = derivativeAppDao
					.queryNewProductAppListByModelCodeAndPlatformCodeMap(map);

			for (DerivativeAppApi bean : ebpAppList) {
				String flatModel = bean.getFlatModel();
				String platformCode = bean.getPlatformCode();
				if (flatModel != null && platformCode != null) {
					if (flatModel.contains(modelCodeStr) && platformCode.contains(platformCodeStr)) {
						list.add(bean);
					}
				}

				if (flatModel != null && platformCode == null) {
					if (flatModel.contains(modelCodeStr)) {
						list.add(bean);
					}
				}

				if (platformCode != null && flatModel == null) {
					if (platformCode.contains(platformCodeStr)) {
						list.add(bean);
					}
				}
			}
		} else {
			if (map.get("modelCode") != null && !"".equals(map.get("modelCode"))) {

				List<DerivativeAppApi> pkgList = derivativeAppDao.getNewProductAppModelCode(map);

				for (DerivativeAppApi pkgMap : pkgList) {

					if (pkgMap.getPlatformCode() == null) {
						list.add(pkgMap);
					}

				}
			}

			if (map.get("platformCode") != null && !"".equals(map.get("platformCode"))) {
				List<DerivativeAppApi> pkgList = derivativeAppDao.getNewProductAppListPlatformCode(map);
				for (DerivativeAppApi pkgMap : pkgList) {
					if (pkgMap.getFlatModel() == null) {
						list.add(pkgMap);
					}
				}
			}
		}

		List<EbpApp> resultList = new ArrayList<EbpApp>();

		if (list != null) {
			for (DerivativeAppApi bean : list) {
				EbpApp ebpApp = new EbpApp();

				ebpApp.setApk_package_name(bean.getApk_package_name());
				ebpApp.setId(bean.getId());
				ebpApp.setVersion_code(bean.getVersion_code());
				ebpApp.setInter_version_code(bean.getInter_version_code());
				ebpApp.setCreate_time(bean.getCreate_time());
				ebpApp.setIconUrl(bean.getIcon_url());
				ebpApp.setFile_size(bean.getFile_size());
				ebpApp.setProduct_ico_url(bean.getProduct_ico_url());
				ebpApp.setProduct_id(bean.getProduct_id());
				ebpApp.setProduct_name(bean.getProduct_name());
				ebpApp.setProduct_type(bean.getProduct_type());
				ebpApp.setDescription(bean.getDescription());
			
				
				resultList.add(ebpApp);
			}

		}
		return resultList;
	}

	// @Override
	// public void update(EbpApp ebpApp) {
	// flatModelDao.deleteByEbpAppId(ebpApp.getId());// 先删除
	// platformInfoDao.deleteByEbpAppId(ebpApp.getId());// 先删除
	// if (ebpApp.getPlatform_codes() != null) {
	// String[] platformCodeArr = ebpApp.getPlatform_codes().split(",");
	//
	// if (platformCodeArr != null) {
	// for (int i = 0; i < platformCodeArr.length; i++) {
	// Map<String, Object> aa = new HashMap<String, Object>();
	// String platformCode = platformCodeArr[i];
	// aa.put("platformCode", platformCode);
	// aa.put("ebpAppId", ebpApp.getId());
	//
	// if ((platformCode != null && !platformCode.equals(""))
	// && (ebpApp.getId() != null && !ebpApp.getId().equals(""))) {
	// platformInfoDao.saveAppPlatformInfo(aa);
	// }
	// }
	// }
	// }
	//
	// // 保存映射
	// if (ebpApp.getFlat_model_ids() != null) {
	// String[] flatModelIdArr = ebpApp.getFlat_model_ids().split(",");
	// for (int i = 0; i < flatModelIdArr.length; i++) {
	// Map<String, Object> aa = new HashMap<String, Object>();
	// String flatModelId = flatModelIdArr[i];
	// aa.put("flatModelId", flatModelId);
	// aa.put("ebpAppId", ebpApp.getId());
	//
	// if ((flatModelId != null && !flatModelId.equals(""))
	// && (ebpApp.getId() != null && !ebpApp.getId().equals(""))) {
	// flatModelDao.saveAppFlatModel(aa);
	// }
	//
	// // flatModelDao.saveAppFlatModel(aa);
	// }
	// }
	//
	// appDao.update(ebpApp);
	// }

}
