package com.whty.ebp.api.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.whty.common.exception.BusinessException;
import com.whty.common.util.CommonFunction;
import com.whty.common.util.SysConfig;
import com.whty.ebp.api.utils.PrintResult;
import com.whty.ebp.manage.dao.SchoolAppDao;
import com.whty.ebp.manage.model.EbpApp;
import com.whty.ebp.manage.model.SchoolApp;
import com.whty.ebp.manage.service.AppService;
import com.whty.ebp.manage.service.DerivativeAppService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/api/newProductInfo")
public class NewProductAppController {
	private static final Logger logger = LoggerFactory.getLogger(NewProductAppController.class);

	@Autowired
	private AppService appService;

	@Autowired
	private DerivativeAppService derivativeAppService;

	@Autowired
	private SchoolAppDao schoolAppDao;

	/**
	 * 检测新版本信息接口
	 * 
	 * @param request
	 * @param response
	 * @param body
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/newProductList", method = RequestMethod.POST)
	@ResponseBody
	public void newProductList(HttpServletRequest request, HttpServletResponse response, @RequestBody String body) {
		logger.info("--------start newProductList------------");
		logger.info(body);
		Map map = new HashMap();
		List<EbpApp> list = new ArrayList<EbpApp>();
		try {
			// 检查参数
			map = checkParam(body);
			// 查询各个产品最新的版本应用
			map.put("ebookpackage_code", SysConfig.getStrValue("ebookpackage_code"));

			if (map.get("derivativeAppId") == null || "".equals(map.get("derivativeAppId"))) {
				list.addAll(appService.getNewProductAppList(map));// 正规线路app列表

				// List<EbpApp> list2 = new ArrayList<EbpApp>();// 临时list
				//
				// for (int i = 0; i < list.size(); i++) {
				// EbpApp bean = (EbpApp) list.get(i);
				//
				// if (bean.getMd5() == null) {
				// String md5 = MD5Utils.getFileMD5(new
				// File(bean.getFile_path()));
				// bean.setMd5(md5);
				// appService.updateMd5(bean);
				// }
				// list2.add(bean);
				// }
				// list = list2;
			} else {// 衍生产品线路应用列表
				list.addAll(derivativeAppService.getAPPLineList2(map));
				// list = derivativeAppService.getAPPLineList(map);

				// for (int i = 0; i < list.size(); i++) {
				// DerivativeAppApi bean = (DerivativeAppApi)list.get(i);
				//
				// bean.getMd5();
				//
				// }

			}

			// if (map.get("productType") != null &&
			// !"".equals(map.get("productType"))) {
			//
			// for(Iterator<EbpApp> iter=list.iterator();iter.hasNext();){
			// EbpApp bean = iter.next();
			// if (!bean.getProduct_type().equals(map.get("productType"))) {
			// iter.remove();
			// }
			// }

			// for (EbpApp bean : list) {
			// if (!bean.getProduct_type().equals(map.get("productType"))) {
			// list.remove(bean);
			// }
			// }
			// }

			if (map.get("orgId") != null && map.get("loginPlatformCode") != null) {
				Map<String, Object> schoolAppParam = new HashMap<String, Object>();
				schoolAppParam.put("orgId", map.get("orgId").toString());
				schoolAppParam.put("loginPlatformCode", map.get("loginPlatformCode").toString());
				schoolAppParam.put("status", "2");
				List<SchoolApp> schoolAppList = schoolAppDao.listByCondition(schoolAppParam);

				if (schoolAppList != null && schoolAppList.size() > 0) {

					for (SchoolApp bean : schoolAppList) {
						EbpApp ebpApp = new EbpApp();

						ebpApp.setApk_package_name(bean.getApkPackageName());
						ebpApp.setApp_name(bean.getAppName());
						ebpApp.setMd5(bean.getMd5());
						ebpApp.setBaiduBosUrl(bean.getDownUrl());
						ebpApp.setDescription(bean.getDescription());
						ebpApp.setFile_size(Long.valueOf(bean.getFileSize()));
						ebpApp.setIconUrl(bean.getIcon());
						ebpApp.setId(bean.getId().toString());
						ebpApp.setVersion_code(bean.getVersionCode());
						ebpApp.setInter_version_code(bean.getInterVersionCode());
						ebpApp.setProduct_id(bean.getId().toString());
						ebpApp.setProduct_name(bean.getAppName());
						ebpApp.setProduct_type("2");// 指定学校类型
						ebpApp.setProduct_ico_url(bean.getIcon());

						boolean isAdd = false;
						for (int i = 0; i < list.size(); i++) {
							EbpApp eg = list.get(i);

							if (eg.getApk_package_name().equals(ebpApp.getApk_package_name())) {
//								list.add(i, ebpApp);
								list.set(i, ebpApp);
//								eg = ebpApp;
								isAdd = true;
								break;
							}
						}

						if (!isAdd) {
							list.add(ebpApp);
						}
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
			PrintResult.getFailNeedShowResult(e.getMessage(), response);
		} catch (BusinessException e) {
			e.printStackTrace();
			PrintResult.getFailNeedShowResult(e.getMessage(), response);
		}
		PrintResult.getSuccessResult(list, response);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Map checkParam(String body) throws IOException, BusinessException {
		JSONObject reqJson = null;
		reqJson = JSONObject.fromObject((body == null || body.equals("")) ? "{}" : body);
		// 参数检验
		if (!CommonFunction.isNotNull(reqJson)) {
			throw new BusinessException("参数不存在");
		}
		Map para = new HashMap();
		// 必填字段检查
		// CommonFunction.checkParam(para);
		para.put("can_update", (reqJson.get("update") == null || !reqJson.get("update").toString().trim().equals("2"))
				? "1" : reqJson.get("update"));// 0不可升级，1全部用户可升级，2根据参数升级
		para.put("modelCode", reqJson.optString("modelCode"));
		para.put("productType", reqJson.optString("productType"));
		para.put("platformCode", reqJson.optString("platformCode"));

		para.put("derivativeAppId", reqJson.optString("derivativeAppId"));

		para.put("loginPlatformCode", reqJson.optString("loginPlatformCode"));
		para.put("personId", reqJson.optString("personId"));
		para.put("classId", reqJson.optString("classId"));
		para.put("orgId", reqJson.optString("orgId"));

		return para;
	}
}
