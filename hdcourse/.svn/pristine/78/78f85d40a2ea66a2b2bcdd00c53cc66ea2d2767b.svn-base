/**
 * 
 */
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.whty.assis.api.service.OverseasDESEDEService;
import com.whty.assis.api.service.OverseasSoftLicenceService;
import com.whty.assis.base.controller.BaseController;
import com.whty.assis.base.exception.BusinessException;
import com.whty.assis.demo.model.EbpSoftLicence;
import com.whty.assis.demo.model.OverseasEbpSoftLicence;
import com.whty.common.util.CommonFunction;
import com.whty.common.util.Constants;
import com.whty.common.util.TimeUtils;

import net.sf.json.JSONObject;

/**
 * @author zhangzheng
 * @date 2018年5月17日
 */
@Controller
@RequestMapping("/api/overseasSoftLicence")
public class OverseasSoftLientController extends BaseController {

	@Autowired
	private OverseasSoftLicenceService overseasSoftLicenceService;

	@Autowired
	private OverseasDESEDEService overseasdesedeService;

	private static Object lock = new Object();

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

				List<String> harddisk_infoList = new ArrayList<String>();

				map.put("cpu_info", cpu_infoArrList);
				map.put("mac_card_info", null);// 不校验MAC
				map.put("harddisk_info", null);

				List<OverseasEbpSoftLicence> list = overseasSoftLicenceService.findOverseasEbpSoftLicenceBYMch(map);
				//
				OverseasEbpSoftLicence ebpSoftLicence = null;

				for (int i = 0; i < list.size(); i++) {
					OverseasEbpSoftLicence bean = list.get(i);
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
						OverseasEbpSoftLicence bean = list.get(i);
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

						ebpSoftLicence.setStatus(1); // 1绑定状态

						// 加密mac、cpu、硬盘信息
						String encryption = overseasdesedeService.encodedData(ebpSoftLicence, random);

						ebpSoftLicence.setHarddisk_info(harddisk_info);
						ebpSoftLicence.setCpu_info(cpu_info);
						ebpSoftLicence.setMac_card_info(mac_card_info);
						overseasSoftLicenceService.bindingLicence(ebpSoftLicence);

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

			OverseasEbpSoftLicence ebpSoftLicence = overseasSoftLicenceService.getOverseasEbpSoftLicence(map);

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
	 * 绑定软件授权码
	 * 
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/binding2", method = RequestMethod.POST)
	public void binding2(HttpServletRequest request, HttpServletResponse response, @RequestBody String body)
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
				// String licenceCode = map.get("licence_code") + "";

				String cpu_info = map.get("cpu_info") + "";
				String mac_card_info = map.get("mac_card_info") + "";
				String harddisk_info = map.get("harddisk_info") + "";

				OverseasEbpSoftLicence ebpSoftLicence = overseasSoftLicenceService.getOverseasEbpSoftLicence(map);

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

							// ebpSoftLicence.setEnd_time(
							// TimeUtils.string2Date(endTime + " 23:59:59",
							// TimeUtils.STR_DATETIME_PATTERN_LONG));

							// 加密mac、cpu、硬盘信息
							String encryption = overseasdesedeService.encodedData(ebpSoftLicence, random);

							// 绑定授权码信息,会修改状态为1
							overseasSoftLicenceService.bindingLicence(ebpSoftLicence);

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
								String encryption = overseasdesedeService.encodedData(ebpSoftLicence, random);

								// 绑定授权码信息,会修改状态为1
								overseasSoftLicenceService.bindingLicence(ebpSoftLicence);

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
							String encryption = overseasdesedeService.encodedData(ebpSoftLicence, random);
							// 绑定授权码信息
							overseasSoftLicenceService.bindingLicence(ebpSoftLicence);

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
				OverseasEbpSoftLicence ebpSoftLicence = overseasSoftLicenceService.getOverseasEbpSoftLicence(map);
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
	private Map validateLicence(OverseasEbpSoftLicence ebpSoftLicence, Map map, Map respMap) throws Exception {
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

						String encryption = overseasdesedeService.encodedData(ebpSoftLicence, random);

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
