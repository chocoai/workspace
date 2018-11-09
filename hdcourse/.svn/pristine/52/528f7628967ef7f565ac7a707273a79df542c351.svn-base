package com.whty.assis.api.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
 * 将原来的rsa加密算法换成3des对称加密算法
 * 
 * @date 2016-7-26
 * @author shenlinxiang
 */
@Controller
@RequestMapping("/api/softLicence2")
public class SoftLicenceController_1 extends BaseController {

	@Autowired
	private SoftLicenceService softLicenceService;

	@Autowired
	private DESEDEService desedeService;

	private static Object lock = new Object();

	/**
	 * 绑定软件授权码
	 * 
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/binding2", method = RequestMethod.POST)
	public void binding(HttpServletRequest request, HttpServletResponse response, @RequestBody String body)
			throws IOException {

		synchronized (lock) {
			Map respMap = new HashMap();
			if (logger.isDebugEnabled()) {
				logger.debug("绑定软件授权码");
			}
			try {
				// 检查参数
				Map map = new HashMap();
				map = checkParamBinding(body);
				// 查询授权码
				// logger.info("binding...");

				String random = map.get("random") + "";
				String licenceCode = map.get("licence_code") + "";

				String cpu_info = map.get("cpu_info") + "";
				String mac_card_info = map.get("mac_card_info") + "";
				String harddisk_info = map.get("harddisk_info") + "";

				EbpSoftLicence ebpSoftLicence = softLicenceService.getEbpSoftLicence(map);

				if (ebpSoftLicence.getEbp_auth() == null) {
					ebpSoftLicence.setEbp_auth("1");
				}

				if (ebpSoftLicence.getSdp_auth() == null) {
					ebpSoftLicence.setSdp_auth("1");
				}

				if (ebpSoftLicence.getAm_auth() == null) {
					ebpSoftLicence.setAm_auth("1");
				}

				if (null != ebpSoftLicence) {
					// 0新建，1绑定，3注销

					if (!ebpSoftLicence.getStatus().equals(1)) {
						Date now = new Date();

						if (ebpSoftLicence.getLimit_day() != null) {
							Calendar cal = Calendar.getInstance();
							String startTime = CommonFunction.getSampleString(cal.getTime());
							cal.set(Calendar.DAY_OF_YEAR,
									cal.get(Calendar.DAY_OF_YEAR) + Integer.valueOf(ebpSoftLicence.getLimit_day()));

							String endTime = CommonFunction.getSampleString(cal.getTime());

							ebpSoftLicence.setBind_time(cal.getTime());
							ebpSoftLicence.setUpdate_time(cal.getTime());
							ebpSoftLicence.setCpu_info(cpu_info);
							ebpSoftLicence.setMac_card_info(mac_card_info);
							ebpSoftLicence.setHarddisk_info(harddisk_info);
							ebpSoftLicence.setStart_time(CommonFunction.getDateTimeString(startTime));
							ebpSoftLicence.setEnd_time(CommonFunction.getDateTimeString(endTime));
							// 加密mac、cpu、硬盘信息
							String encryption = desedeService.encodedData(ebpSoftLicence, random);

							// 绑定授权码信息,会修改状态为1
							softLicenceService.bindingLicence(ebpSoftLicence);

							// 返回
							respMap.put("result", Constants.SUCCESS_CODE);
							respMap.put("resultMsg", "success");
							respMap.put("encryption", encryption);
						} else {
							if (ebpSoftLicence.getEnd_time().getTime() < now.getTime()) {
								// 授权码有效时间已过
								respMap.put("result", Constants.FAIL_CODE);
								respMap.put("resultMsg", "授权码有效时间已过");
							} else {
								Date currentDate = new Date();
								ebpSoftLicence.setBind_time(currentDate);
								ebpSoftLicence.setUpdate_time(currentDate);
								ebpSoftLicence.setCpu_info(cpu_info);
								ebpSoftLicence.setMac_card_info(mac_card_info);
								ebpSoftLicence.setHarddisk_info(harddisk_info);
								// 加密mac、cpu、硬盘等信息
								// respMap =
								// rSAService.encodedData(ebpSoftLicence,
								// respMap);
								String encryption = desedeService.encodedData(ebpSoftLicence, random);

								// 绑定授权码信息,会修改状态为1
								softLicenceService.bindingLicence(ebpSoftLicence);

								// 返回
								respMap.put("result", Constants.SUCCESS_CODE);
								respMap.put("resultMsg", "success");
								respMap.put("encryption", encryption);
							}
						}

					} else {
						// 已绑定，查询再次绑定的机器码是否与上次绑定的机器码一样
						if (ebpSoftLicence.getCpu_info().trim().contains(cpu_info)
								|| ebpSoftLicence.getMac_card_info().trim().contains(mac_card_info)
								|| ebpSoftLicence.getHarddisk_info().trim().contains(harddisk_info)) {
							// 同一台机器可以绑定
							Date currentDate = new Date();
							ebpSoftLicence.setBind_time(currentDate);
							ebpSoftLicence.setUpdate_time(currentDate);
							ebpSoftLicence.setCpu_info(cpu_info);
							ebpSoftLicence.setMac_card_info(mac_card_info);
							ebpSoftLicence.setHarddisk_info(harddisk_info);
							// 加密mac、cpu、硬盘信息
							// respMap = rSAService.encodedData(ebpSoftLicence,
							// respMap);
							String encryption = desedeService.encodedData(ebpSoftLicence, random);
							// 绑定授权码信息
							softLicenceService.bindingLicence(ebpSoftLicence);

							// 返回
							respMap.put("result", Constants.SUCCESS_CODE);
							respMap.put("resultMsg", "success");
							respMap.put("encryption", encryption);
						} else {
							respMap.put("result", Constants.LICENCE_BINDINGED_CODE);
							respMap.put("resultMsg", Constants.LICENCE_BINDINGED_MSG);
						}
					}
				} else {
					// 返回
					respMap.put("result", Constants.FAIL_CODE);
					respMap.put("resultMsg", "授权码不存在");
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

	}

	/**
	 * 验证软件授权码
	 * 
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/validate2", method = RequestMethod.POST)
	public void validate(HttpServletRequest request, HttpServletResponse response, @RequestBody String body)
			throws IOException {
		Map respMap = new HashMap();
		if (logger.isDebugEnabled()) {
			logger.debug("验证软件授权码");
		}
		try {
			synchronized (lock) {
				// 检查参数
				Map map = new HashMap();
				map = checkParamValidate(body);
				// 查询授权码, 通过licence_code查询数据库记录
				EbpSoftLicence ebpSoftLicence = softLicenceService.getEbpSoftLicence(map);
				// 验证授权
				respMap = validateLicence(ebpSoftLicence, map, respMap);
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

	/*
	 * 验证授权码
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Map validateLicence(EbpSoftLicence ebpSoftLicence, Map map, Map respMap) throws Exception {
		String cpu_info = (String) map.get("cpu_info");
		String mac_card_info = (String) map.get("mac_card_info");
		String harddisk_info = (String) map.get("harddisk_info");
		String random = (String) map.get("random");
		if (null != ebpSoftLicence) {
			if (ebpSoftLicence.getStatus() == 1) {
				boolean allUnknow = cpu_info.equals("unknow") && mac_card_info.equals("unknow")
						&& harddisk_info.equals("unknow");
				boolean harddiskUnknow = !cpu_info.equals("unknow") && !mac_card_info.equals("unknow")
						&& harddisk_info.equals("unknow");
				boolean licence = true;
				if (harddiskUnknow) {
					licence = ebpSoftLicence.getCpu_info().trim().contains(cpu_info)
							&& ebpSoftLicence.getMac_card_info().trim().contains(mac_card_info);
				} else if (!allUnknow && !harddiskUnknow) {
					licence = ebpSoftLicence.getMac_card_info().trim().contains(mac_card_info)
							&& ebpSoftLicence.getCpu_info().trim().contains(cpu_info)
							&& ebpSoftLicence.getHarddisk_info().trim().contains(harddisk_info);
				}

				// 绑定
				if (allUnknow || licence) {
					Date now = new Date();
					Calendar cal = Calendar.getInstance();
					cal.setTime(ebpSoftLicence.getEnd_time());
					cal.add(Calendar.DATE, 1);
					if (ebpSoftLicence.getStart_time().getTime() > now.getTime()) {
						// 授权码生效时间未到
						respMap.put("result", Constants.FAIL_CODE);
						respMap.put("resultMsg", "生效时间未到");
					} else if (cal.getTime().getTime() < now.getTime()) {
						// 授权码有效时间已过
						respMap.put("result", Constants.FAIL_CODE);
						respMap.put("resultMsg", "授权码有效时间已过");
					} else {
						// 加密mac、cpu、硬盘信息
						// respMap = rSAService.encodedData(ebpSoftLicence,
						// respMap);

						ebpSoftLicence.setCpu_info(cpu_info);
						ebpSoftLicence.setMac_card_info(mac_card_info);
						ebpSoftLicence.setHarddisk_info(harddisk_info);

						String encryption = desedeService.encodedData(ebpSoftLicence, random);

						respMap.put("result", Constants.SUCCESS_CODE);
						respMap.put("resultMsg", "success");
						respMap.put("encryption", encryption);
					}
				} else {
					// 授权码绑定信息验证失败
					respMap.put("result", Constants.LICENCE_WRONG_BINDING_INFO_CODE);
					respMap.put("resultMsg", Constants.LICENCE_WRONG_BINDING_INFO_MSG);
				}
			} else if (ebpSoftLicence.getStatus() == 3) {
				// 已注销
				respMap.put("result", Constants.LICENCE_CANCEL_CODE);
				respMap.put("resultMsg", Constants.LICENCE_CANCEL_MSG);
			} else {
				// 未绑定
				respMap.put("result", Constants.LICENCE_UNBINDING_CODE);
				respMap.put("resultMsg", Constants.LICENCE_UNBINDING_MSG);
			}
		} else {
			// 授权码不存在
			respMap.put("result", Constants.FAIL_CODE);
			respMap.put("resultMsg", "授权码不存在");
		}
		return respMap;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Map checkParamBinding(String body) throws IOException, BusinessException {
		JSONObject reqJson = null;
		reqJson = JSONObject.fromObject((body == null || body.equals("")) ? "{}" : body);
		// 参数检验
		if (!CommonFunction.isNotNull(reqJson)) {
			throw new BusinessException("参数不存在");
		}
		Map para = new HashMap();
		para.put("licence_code", reqJson.get("licence_code"));
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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Map checkParamValidate(String body) throws IOException, BusinessException {
		JSONObject reqJson = null;
		reqJson = JSONObject.fromObject((body == null || body.equals("")) ? "{}" : body);
		// 参数检验
		if (!CommonFunction.isNotNull(reqJson)) {
			throw new BusinessException("参数不存在");
		}
		Map para = new HashMap();
		para.put("licence_code", reqJson.get("licence_code"));
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
