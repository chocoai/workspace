package com.whty.assis.api.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.whty.assis.api.service.DESEDEService;
import com.whty.assis.api.service.SoftLicenceService;
import com.whty.assis.base.controller.BaseController;
import com.whty.assis.base.exception.BusinessException;
import com.whty.assis.demo.model.EbpSoftLicence;
import com.whty.common.util.CommonFunction;
import com.whty.common.util.Constants;

import net.sf.json.JSONObject;

/**
 * 电子书包软件授权码Controller
 * 
 * 将rsa加密算法修改为3des对称加密
 * 
 * 
 * @author shenlinxiang
 */
@Controller
@RequestMapping("/api/recoverLicence2")
public class SoftLicenceRecoverController_1 extends BaseController {

	@Autowired
	private SoftLicenceService softLicenceService;

	@Autowired
	private DESEDEService desedeService;

	private Object lock = new Object();

	/**
	 * 授权文件恢复
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/getMyLicence2")
	public void recover(HttpServletRequest request, HttpServletResponse response, @RequestBody String body)
			throws IOException {
		Map respMap = new HashMap();
		if (logger.isDebugEnabled()) {
			logger.debug("恢复授权码");
		}
		try {
			synchronized (lock) {
				// 检查参数
				Map map = new HashMap();
				map = checkParamGetMyLicence(body);

				// 查询授权码
				String random = map.get("random") + "";
				String cpu_info = map.get("cpu_info") + "";
				String mac_card_info = map.get("mac_card_info") + "";
				String harddisk_info = map.get("harddisk_info") + "";

				String cpu_infoStr = cpu_info;
				String[] cpu_infoArr = cpu_infoStr.split(";");
				List<String> cpu_infoArrList = Arrays.asList(cpu_infoArr);

				String mac_card_infoStr = mac_card_info;
				String[] mac_card_infoArr = mac_card_infoStr.split(";");
				List<String> mac_card_infoList = Arrays.asList(mac_card_infoArr);

				// List<String> harddisk_infoList = new ArrayList<String>();

				List<String> harddisk_infoArrList = Arrays.asList(harddisk_info);

				// if (harddisk_info.equals("unknow")) {
				// map.put("cpu_info", cpu_infoArrList);
				// map.put("mac_card_info", mac_card_infoList);// 不校验MAC
				// map.put("harddisk_info", null);
				// } else {
				// String harddisk_infoStr = harddisk_info;
				// String[] harddisk_infoArr = harddisk_infoStr.split(";");
				// harddisk_infoList = Arrays.asList(harddisk_infoArr);
				//
				// map.put("cpu_info", cpu_infoArrList);
				// map.put("mac_card_info", mac_card_infoList);// 不校验MAC
				// map.put("harddisk_info", harddisk_infoList);
				//
				// }

				// if (harddisk_info.equals("unknow")) {
				// map.put("cpu_info", cpu_infoArrList);
				// map.put("mac_card_info", mac_card_infoList);
				// map.put("harddisk_info", null);
				// } else {
				// String harddisk_infoStr = harddisk_info;
				// String[] harddisk_infoArr = harddisk_infoStr.split(";");
				// harddisk_infoList = Arrays.asList(harddisk_infoArr);
				//
				// map.put("cpu_info", cpu_infoArrList);
				// map.put("mac_card_info", mac_card_infoList);
				//// map.put("harddisk_info", harddisk_infoList);
				// map.put("harddisk_info", null);
				// }

				map.put("cpu_info", cpu_infoArrList);
				map.put("mac_card_info", null);// 不校验MAC
				map.put("harddisk_info", harddisk_infoArrList);

				List<EbpSoftLicence> list = softLicenceService.findEbpSoftLicenceBYMch(map);
				//
				EbpSoftLicence ebpSoftLicence = null;

				for (int i = 0; i < list.size(); i++) {
					EbpSoftLicence bean = list.get(i);
					if (bean.getMac_card_info() != null) {
						if (bean.getMac_card_info().trim().contains(mac_card_info)) {

							Date now = Calendar.getInstance().getTime();// 当前时间为开始时间
							if (bean.getEnd_time().getTime() > now.getTime()) {
								// respMap.put("result", Constants.FAIL_CODE);
								// respMap.put("resultMsg", "授权码过期");
								ebpSoftLicence = bean;
								break;
							}
						}
					}

				}

				if (ebpSoftLicence == null) {
					for (int i = 0; i < list.size(); i++) {
						EbpSoftLicence bean = list.get(i);
						if (bean.getHarddisk_info() != null) {
							if (bean.getHarddisk_info().trim().contains(harddisk_info)) {

								Date now = Calendar.getInstance().getTime();// 当前时间为开始时间
								if (bean.getEnd_time().getTime() > now.getTime()) {
									// respMap.put("result",
									// Constants.FAIL_CODE);
									// respMap.put("resultMsg", "授权码过期");
									ebpSoftLicence = bean;
									break;
								}
							}
						}
					}
				}

				// if (ebpSoftLicence == null) {
				// for (int i = 0; i < list.size(); i++) {
				// EbpSoftLicence bean = list.get(i);
				// if (bean.getHarddisk_info().trim().equals("unknow")) {
				//
				// Date now = Calendar.getInstance().getTime();// 当前时间为开始时间
				// if (bean.getEnd_time().getTime() > now.getTime()) {
				// // respMap.put("result", Constants.FAIL_CODE);
				// // respMap.put("resultMsg", "授权码过期");
				// ebpSoftLicence = bean;
				// break;
				// }
				// }
				// }
				// }

				// if(ebpSoftLicence==null){
				// for(int i=0;i<list.size();i++){
				// EbpSoftLicence bean = list.get(i);
				// if(bean.getHarddisk_info().contains("unknow")){
				// ebpSoftLicence = bean;
				// break;
				// }
				// }
				// }

				// if (ebpSoftLicence == null) {
				// map.put("mac_card_info", null);// 不查mac,在查询一遍
				// map.put("cpu_info", cpu_infoArrList);
				// map.put("harddisk_info", harddisk_infoList);
				// ebpSoftLicence =
				// softLicenceService.getEbpSoftLicenceBYMch(map);
				// }

				// 还没查到，则只找mac，和cpu
				// if(ebpSoftLicence == null){
				// map.put("mac_card_info", null);// 不查mac,在查询一遍
				// map.put("harddisk_info", null);
				// ebpSoftLicence =
				// softLicenceService.getEbpSoftLicenceBYMch(map);
				// }

				if (null != ebpSoftLicence) {
					Date now = Calendar.getInstance().getTime();// 当前时间为开始时间
					if (ebpSoftLicence.getEnd_time().getTime() < now.getTime()) {// 授权码过期了不能恢复
						respMap.put("result", Constants.FAIL_CODE);
						respMap.put("resultMsg", "授权码过期");
					} else {
						Date currentDate = new Date();

						String ebpSoftLicenceMac = ebpSoftLicence.getMac_card_info();
						String ebpSoftLicenceCpu = ebpSoftLicence.getCpu_info();
						String ebpSoftLicenceHarddisk = ebpSoftLicence.getHarddisk_info();

						// ebpSoftLicence.setBind_time(currentDate);
						// ebpSoftLicence.setUpdate_time(currentDate);
						// ebpSoftLicence.setCpu_info(cpu_info);
						// ebpSoftLicence.setMac_card_info(mac_card_info);
						// ebpSoftLicence.setHarddisk_info(harddisk_info);
						ebpSoftLicence.setStatus(1); // 1绑定状态

						// 加密mac、cpu、硬盘信息
						String encryption = desedeService.encodedData(ebpSoftLicence, random);
						// respMap = rSAService.encodedData(ebpSoftLicence,
						// respMap);

						// Set<String> cpu_infoSet = new HashSet<String>();
						// Set<String> mac_infoSet = new HashSet<String>();
						// Set<String> disddisk_infoSet = new HashSet<String>();
						//
						// cpu_infoSet.addAll(cpu_infoArrList);
						// mac_infoSet.addAll(mac_card_infoList);
						// disddisk_infoSet.addAll(harddisk_infoList);
						//
						// cpu_infoSet.addAll(Arrays.asList(ebpSoftLicenceCpu.split(";")));
						// mac_infoSet.addAll(Arrays.asList(ebpSoftLicenceMac.split(";")));
						// disddisk_infoSet.addAll(Arrays.asList(ebpSoftLicenceHarddisk.split(";")));
						//
						// ebpSoftLicence.setCpu_info(StringUtils.join(cpu_infoSet,
						// ";"));
						// ebpSoftLicence.setMac_card_info(StringUtils.join(mac_infoSet,
						// ";"));
						// ebpSoftLicence.setHarddisk_info(StringUtils.join(disddisk_infoSet,
						// ";"));

						// 绑定授权码信息, 内部是一条update语句

						ebpSoftLicence.setHarddisk_info(harddisk_info);
						ebpSoftLicence.setCpu_info(cpu_info);
						ebpSoftLicence.setMac_card_info(mac_card_info);
						softLicenceService.bindingLicence(ebpSoftLicence);

						// 返回
						respMap.put("result", Constants.SUCCESS_CODE);
						respMap.put("resultMsg", "success");
						respMap.put("encryption", encryption);
					}
				} else {
					// 返回
					respMap.put("result", Constants.FAIL_CODE);
					respMap.put("resultMsg", "该机器没有绑定授权码");
				}

			}
		} catch (IOException e) {
			respMap.put("result", Constants.FAIL_CODE);
			respMap.put("resultMsg", e.getMessage());
			e.printStackTrace();
		} catch (BusinessException e) {
			respMap.put("result", Constants.FAIL_CODE);
			respMap.put("resultMsg", e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			respMap.put("result", Constants.FAIL_CODE);
			respMap.put("resultMsg", e.getMessage());
			e.printStackTrace();
		}

		CommonFunction.writeResp(response, JSONObject.fromObject(respMap).toString());
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Map checkParamGetMyLicence(String body) throws IOException, BusinessException {
		logger.info(body);
		JSONObject reqJson = null;
		reqJson = JSONObject.fromObject((body == null || body.equals("")) ? "{}" : body);
		// 参数检验
		if (!CommonFunction.isNotNull(reqJson)) {
			throw new BusinessException("参数不存在");
		}
		Map para = new HashMap();
		para.put("random", reqJson.get("random"));
		// 必填字段检查
		CommonFunction.checkParam(para);
		if ((reqJson.get("mac_card_info") == null || reqJson.get("mac_card_info").toString().trim().equals(""))
				&& (reqJson.get("cpu_info") == null || reqJson.get("cpu_info").toString().trim().equals(""))
				&& (reqJson.get("harddisk_info") == null
						|| reqJson.get("harddisk_info").toString().trim().equals(""))) {
			throw new BusinessException("参数mac_card_info,cpu_info,harddisk_info不能为空");
		}
		para.put("mac_card_info", reqJson.get("mac_card_info"));
		para.put("cpu_info", reqJson.get("cpu_info"));
		para.put("harddisk_info", reqJson.get("harddisk_info"));
		return para;
	}

}
