package com.whty.assis.api.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
 * @author zhujg
 */
@Controller
@RequestMapping("/api/softLicence")
public class SoftLicenceController extends BaseController {

	@Autowired
	private SoftLicenceService softLicenceService;
	@Autowired
	private RSAService rSAService;

	/**
	 * 得到所有授权码
	 * 
	 * @param hdktLicenceList
	 * @param jxzsLicenceList
	 * @return
	 */
	public static Set<EbpSoftLicence> getAllLicenceList(List<EbpSoftLicence> hdktLicenceList,
			List<EbpSoftLicence> jxzsLicenceList) {
		Set<EbpSoftLicence> licenceSet = new HashSet<EbpSoftLicence>();

		for (EbpSoftLicence licence : jxzsLicenceList) {
			licenceSet.add(licence);
		}

		for (EbpSoftLicence licence : hdktLicenceList) {
			licenceSet.add(licence);
		}

		return licenceSet;
	}

	/**
	 * 查询授权码
	 * 
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/searchLicence")
	public void searchLicence(HttpServletRequest request, HttpServletResponse response, @RequestBody String body)
			throws IOException {
		Map respMap = new HashMap();
		if (logger.isDebugEnabled()) {
			logger.debug("查询授权码");
		}
		JSONObject dataJson = new JSONObject();
		try {
			Map map = new HashMap();
			map = checkSearchLicence(body);

			// String licenceCode = map.get("licence_ode").toString();

			String mac_card_info = map.get("mac_card_info").toString();
			String cpu_info = map.get("cpu_info").toString();
			String harddisk_info = map.get("harddisk_info").toString();

			EbpSoftLicence ebpSoftLicence = softLicenceService.getEbpSoftLicence(map);

			if (ebpSoftLicence != null) {

				if (ebpSoftLicence.getStatus().equals(0)) {// 未绑定

					if (ebpSoftLicence.getEnd_time() != null) {// 判断授权码是否已过期,永久的不在判断之列
						Date now = Calendar.getInstance().getTime();// 当前时间为开始时间

						Calendar endTimeCal = Calendar.getInstance();

						endTimeCal.setTime(ebpSoftLicence.getEnd_time());

						endTimeCal.set(Calendar.HOUR, 23);
						endTimeCal.set(Calendar.SECOND, 59);
						endTimeCal.set(Calendar.MINUTE, 23);

						if (ebpSoftLicence.getStart_time().getTime() > now.getTime()) {
							// 授权码生效时间未到
							respMap.put("result", Constants.FAIL_LICENCE_NOTUSED_CODE);
							respMap.put("resultMsg", "生效时间未到");
						} else if (endTimeCal.getTime().getTime() < now.getTime()) {
							// 授权码有效时间已过
							respMap.put("result", Constants.FAIL_LICENCE_EXPIRED_CODE);
							respMap.put("resultMsg", "权码已过期");
						} else {
							respMap.put("result", Constants.SUCCESS_CODE);
							respMap.put("resultMsg", "您要激活的授权码");
						}

					} else {
						respMap.put("result", Constants.SUCCESS_CODE);
						respMap.put("resultMsg", "您要激活的授权码");
					}

				} else if (ebpSoftLicence.getStatus().equals(1)) {// 已绑定

					if (mac_card_info.equals(ebpSoftLicence.getMac_card_info())
							&& cpu_info.equals(ebpSoftLicence.getCpu_info())
							&& harddisk_info.equals(ebpSoftLicence.getHarddisk_info())) {

						respMap.put("result", Constants.FAIL_COMPUTER_INFO_SAME_CODE);
						respMap.put("resultMsg", "授权码已被本机绑定");
					} else {
						respMap.put("result", Constants.FAIL_COMPUTER_INFO_NOSAME_CODE);
						respMap.put("resultMsg", "授权码已被使用，但不是本机绑定");
					}

				} else if (ebpSoftLicence.getStatus().equals(3)) {// 注销
					respMap.put("result", Constants.FAIL_LICENCE_CANCEL_CODE);
					respMap.put("resultMsg", "授权码已注销");
				} else {
					respMap.put("result", Constants.FAIL_CODE);
					respMap.put("resultMsg", "未知状态");
				}

				if (ebpSoftLicence.getLimit_day() != null) {
					if (ebpSoftLicence.getStatus().equals(0)) {
						Calendar cal = Calendar.getInstance();
						String startTime = CommonFunction.getSampleString(cal.getTime());
						cal.set(Calendar.DAY_OF_YEAR,
								cal.get(Calendar.DAY_OF_YEAR) + Integer.valueOf(ebpSoftLicence.getLimit_day()));

						String endTime = CommonFunction.getSampleString(cal.getTime());
						dataJson.put("startTime", startTime);
						dataJson.put("endTime", endTime);
					} else {
						dataJson.put("endTime", CommonFunction.getSampleString(ebpSoftLicence.getEnd_time()));
						dataJson.put("startTime", CommonFunction.getSampleString(ebpSoftLicence.getStart_time()));
					}
				} else {
					dataJson.put("endTime", CommonFunction.getSampleString(ebpSoftLicence.getEnd_time()));
					dataJson.put("startTime", CommonFunction.getSampleString(ebpSoftLicence.getStart_time()));
				}

				dataJson.put("maxUserCount", ebpSoftLicence.getMax_use_count());
				dataJson.put("ebpAuth", ebpSoftLicence.getEbp_auth() == null ? "1" : ebpSoftLicence.getEbp_auth());
				dataJson.put("sdpAuth", ebpSoftLicence.getSdp_auth() == null ? "1" : ebpSoftLicence.getSdp_auth());
				dataJson.put("amAuth", ebpSoftLicence.getAm_auth() == null ? "1" : ebpSoftLicence.getAm_auth());
			} else {
				respMap.put("result", Constants.FAIL_LICENCE_NOTFOUND_CODE);
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
		respMap.put("data", dataJson);
		CommonFunction.writeResp(response, JSONObject.fromObject(respMap).toString());
	}

	private static Object lock = new Object();

	/**
	 * 绑定软件授权码
	 * 
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/binding")
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
				String cpu_info = map.get("cpu_info") + "";
				String mac_card_info = map.get("mac_card_info") + "";
				String harddisk_info = map.get("harddisk_info") + "";
				EbpSoftLicence ebpSoftLicence = softLicenceService.getEbpSoftLicence(map);
				if (null != ebpSoftLicence) {
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
							respMap = rSAService.encodedData(ebpSoftLicence, respMap);
							// 绑定授权码信息
							softLicenceService.bindingLicence(ebpSoftLicence);

							// softLicenceService
							// .bindingLicence((EbpSoftLicence)
							// JsonUtils.jsonToObj(body, EbpSoftLicence.class));
							// 返回
							respMap.put("result", Constants.SUCCESS_CODE);
							respMap.put("resultMsg", "success");
						} else {
							if (ebpSoftLicence.getEnd_time().getTime() < now.getTime()) {
								// 授权码有效时间已过
								respMap.put("result", Constants.FAIL_CODE);
								respMap.put("resultMsg", "授权码有效时间已过");
							} else {
								Date currentDate = new Date();
								// ebpSoftLicence.setStatus(1); // 1绑定状态
								ebpSoftLicence.setBind_time(currentDate);
								ebpSoftLicence.setUpdate_time(currentDate);
								ebpSoftLicence.setCpu_info(cpu_info);
								ebpSoftLicence.setMac_card_info(mac_card_info);
								ebpSoftLicence.setHarddisk_info(harddisk_info);
								// 加密mac、cpu、硬盘信息
								respMap = rSAService.encodedData(ebpSoftLicence, respMap);
								// 绑定授权码信息
								softLicenceService.bindingLicence(ebpSoftLicence);

								// softLicenceService
								// .bindingLicence((EbpSoftLicence)
								// JsonUtils.jsonToObj(body,
								// EbpSoftLicence.class));
								// 返回
								respMap.put("result", Constants.SUCCESS_CODE);
								respMap.put("resultMsg", "success");
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
							respMap = rSAService.encodedData(ebpSoftLicence, respMap);
							// 绑定授权码信息
							softLicenceService.bindingLicence(ebpSoftLicence);
							// softLicenceService
							// .bindingLicence((EbpSoftLicence)
							// JsonUtils.jsonToObj(body, EbpSoftLicence.class));
							// 返回
							respMap.put("result", Constants.SUCCESS_CODE);
							respMap.put("resultMsg", "success");
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
	@RequestMapping(value = "/validate")
	public void validate(HttpServletRequest request, HttpServletResponse response, @RequestBody String body)
			throws IOException {
		Map respMap = new HashMap();
		if (logger.isDebugEnabled()) {
			logger.debug("验证软件授权码");
		}
		try {
			// 检查参数
			Map map = new HashMap();
			map = checkParamValidate(body);
			// 查询授权码
			EbpSoftLicence ebpSoftLicence = softLicenceService.getEbpSoftLicence(map);
			// 验证授权
			respMap = validateLicence(ebpSoftLicence, map, respMap);
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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Map validateLicence(EbpSoftLicence ebpSoftLicence, Map map, Map respMap) throws Exception {
		String cpu_info = (String) map.get("cpu_info");
		String mac_card_info = (String) map.get("mac_card_info");
		String harddisk_info = (String) map.get("harddisk_info");
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
					licence = ebpSoftLicence.getCpu_info().trim().contains(cpu_info)
							&& ebpSoftLicence.getMac_card_info().trim().contains(mac_card_info)
							&& ebpSoftLicence.getHarddisk_info().trim().contains(harddisk_info);
				}
				// 绑定
				if (allUnknow || licence) {
					Date now = new Date();
					if (ebpSoftLicence.getStart_time().getTime() > now.getTime()) {
						// 授权码生效时间未到
						respMap.put("result", Constants.FAIL_CODE);
						respMap.put("resultMsg", "生效时间未到");
					} else if (ebpSoftLicence.getEnd_time().getTime() < now.getTime()) {
						// 授权码有效时间已过
						respMap.put("result", Constants.FAIL_CODE);
						respMap.put("resultMsg", "授权码有效时间已过");
					} else {
						// 加密mac、cpu、硬盘信息
						respMap = rSAService.encodedData(ebpSoftLicence, respMap);
						respMap.put("result", Constants.SUCCESS_CODE);
						respMap.put("resultMsg", "success");
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
	private Map checkSearchLicence(String body) throws IOException, BusinessException {
		JSONObject reqJson = null;
		reqJson = JSONObject.fromObject((body == null || body.equals("")) ? "{}" : body);
		// 参数检验
		if (!CommonFunction.isNotNull(reqJson)) {
			throw new BusinessException("参数不存在");
		}
		Map para = new HashMap();
		para.put("licence_code", reqJson.get("licence_code"));

		para.put("mac_card_info", reqJson.get("mac_card_info"));
		para.put("cpu_info", reqJson.get("cpu_info"));

		// para.put("userId", reqJson.get("userId"));
		// para.put("orgaId", reqJson.get("orgaId"));
		// para.put("platformCode", reqJson.get("platformCode"));

		// 必填字段检查
		CommonFunction.checkParam(para);
		para.put("harddisk_info", reqJson.get("harddisk_info"));

		return para;

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
