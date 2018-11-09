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

import com.whty.assis.api.service.RSAService;
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
 * @author xlz
 */
@Controller
@RequestMapping("/api/recoverLicence")
public class SoftLicenceRecoverController extends BaseController {

	@Autowired
	private SoftLicenceService softLicenceService;
	@Autowired
	private RSAService rSAService;

	/**
	 * 恢复授权码
	 * 
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	@RequestMapping(value = "/getMyLicence")
	public void recover(HttpServletRequest request, HttpServletResponse response, @RequestBody String body)
			throws IOException {
		Map respMap = new HashMap();
		if (logger.isDebugEnabled()) {
			logger.debug("恢复授权码");
		}
		try {
			// 检查参数
			Map map = new HashMap();
			map = checkParamBinding(body);
			// 查询授权码
			String cpu_info = map.get("cpu_info") + "";
			String mac_card_info = map.get("mac_card_info") + "";
			String harddisk_info = map.get("harddisk_info") + "";

			String cpu_infoStr = cpu_info;
			String[] cpu_infoArr = cpu_infoStr.split(";");
			List<String> cpu_infoArrList = Arrays.asList(cpu_infoArr);

			String mac_card_infoStr = mac_card_info;
			String[] mac_card_infoArr = mac_card_infoStr.split(";");
			List<String> mac_card_infoList = Arrays.asList(mac_card_infoArr);
			List<String> harddisk_infoList = new ArrayList<String>();

			// if (harddisk_info.equals("unknow")) {
			// map.put("harddisk_info", null);
			// }

			if (harddisk_info.equals("unknow")) {
				map.put("cpu_info", cpu_infoArrList);
				map.put("mac_card_info", mac_card_infoList);// 不校验MAC
				map.put("harddisk_info", null);
			} else {
				String harddisk_infoStr = harddisk_info;
				String[] harddisk_infoArr = harddisk_infoStr.split(";");
				harddisk_infoList = Arrays.asList(harddisk_infoArr);
				map.put("cpu_info", cpu_infoArrList);
				map.put("mac_card_info", mac_card_infoList);// 不校验MAC
				map.put("harddisk_info", harddisk_infoList);
			}

			EbpSoftLicence ebpSoftLicence = softLicenceService.getEbpSoftLicenceBYMch(map);
			if (null != ebpSoftLicence) {
				Date now = Calendar.getInstance().getTime();// 当前时间为开始时间
				if (ebpSoftLicence.getEnd_time().getTime() < now.getTime()) {// 授权码过期了不能恢复
					respMap.put("result", Constants.FAIL_CODE);
					respMap.put("resultMsg", "授权码过期");
				} else {
					ebpSoftLicence.setBind_time(new Date());
					ebpSoftLicence.setCpu_info(cpu_info);
					ebpSoftLicence.setMac_card_info(mac_card_info);
					ebpSoftLicence.setHarddisk_info(harddisk_info);
					// 加密mac、cpu、硬盘信息
					respMap = rSAService.encodedData(ebpSoftLicence, respMap);
					// 绑定授权码信息
					// softLicenceService.bindingLicence((EbpSoftLicence)JsonUtils.jsonToObj(body,EbpSoftLicence.class));
					// 返回
					respMap.put("licence_code", ebpSoftLicence.getLicence_code());
					respMap.put("result", Constants.SUCCESS_CODE);
					respMap.put("resultMsg", "success");
				}
			} else {
				// 返回
				respMap.put("result", Constants.FAIL_CODE);
				respMap.put("resultMsg", "该机器没有绑定授权码");
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

	private Map checkParamBinding(String body) throws IOException, BusinessException {
		JSONObject reqJson = null;
		reqJson = JSONObject.fromObject((body == null || body.equals("")) ? "{}" : body);
		// 参数检验
		if (!CommonFunction.isNotNull(reqJson)) {
			throw new BusinessException("参数不存在");
		}
		Map para = new HashMap();

		// 必填字段检查
		CommonFunction.checkParam(para);
		if ((reqJson.get("mac_card_info") == null || reqJson.get("mac_card_info").toString().trim().equals(""))
				&& (reqJson.get("cpu_info") == null || reqJson.get("cpu_info").toString().trim().equals(""))
				&& (reqJson.get("harddisk_info") == null
						|| reqJson.get("harddisk_info").toString().trim().equals(""))) {
			throw new BusinessException("参数mac_card_info,cpu_info,harddisk_info不能同时为空");
		}
		para.put("mac_card_info", reqJson.get("mac_card_info"));
		para.put("cpu_info", reqJson.get("cpu_info"));
		para.put("harddisk_info", reqJson.get("harddisk_info"));
		return para;
	}

}
