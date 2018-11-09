package com.yhcrt.healthcloud.health.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.yhcrt.healthcloud.health.entity.HealthRecord;
import com.yhcrt.healthcloud.health.entity.HrOperation;
import com.yhcrt.healthcloud.health.entity.HrTransfusion;
import com.yhcrt.healthcloud.health.entity.HrTrauma;
import com.yhcrt.healthcloud.health.service.HealthRecordService;
import com.yhcrt.healthcloud.health.service.HrOperationService;
import com.yhcrt.healthcloud.health.service.HrTransfusionService;
import com.yhcrt.healthcloud.health.service.HrTraumaService;
import com.yhcrt.healthcloud.health.service.MemberQueryService;
import com.yhcrt.healthcloud.memberBack.entity.MemberBack;
import com.yhcrt.healthcloud.memberBack.service.MemberDeviceService;

/**
 * 健康档案Controller
 * 
 * @author huzelin
 *
 */
@Controller
@RequestMapping("/healthService")
public class HealthRecordController {

	@Autowired
	private HealthRecordService healthRecordService;
	@Autowired
	private MemberQueryService memberService;
	@Autowired
	private MemberDeviceService memberDeviceService;
	@Autowired
	private HrOperationService operationService;
	@Autowired
	private HrTransfusionService tansfusionService;
	@Autowired
	private HrTraumaService traumaService;

	@RequestMapping(value = "/record")
	public String healthRecord(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap,
			@RequestParam(name = "memberId", defaultValue = "0") Integer memberId) {

		String imei = memberDeviceService.getImeiByMemberId(memberId);
		modelMap.addAttribute("imei", imei);
		// 根据memberId获取健康档案对象
		HealthRecord record = healthRecordService.getRecordByMemberId(memberId);
		// 根据memberId获取会员对象
		MemberBack member = memberService.getMemberByMemberId(memberId);
		// 向页面传入数据
		modelMap.addAttribute("record", record);
		modelMap.addAttribute("member", member);
		modelMap.addAttribute("/health/jsp/health_record");
		addData(modelMap, record);
		return "/health/jsp/health_record";
	}

	/**
	 * 生成部分档案信息
	 * 
	 * @param modelMap
	 *            页面数据map
	 * @param record
	 *            健康档案
	 */
	public void addData(ModelMap modelMap, HealthRecord record) {
		// 高血压
		if (record.getIsHypertension() != null) {
			String isHypertension = record.getIsHypertension().equals("是") ? "高血压	" : "";
			modelMap.addAttribute("isHypertension", isHypertension);
		}
		// 糖尿病
		if (record.getIsDm() != null) {
			String isDm = record.getIsDm().equals("是") ? "糖尿病	" : "";
			modelMap.addAttribute("isDm", isDm);
		}
		// 心脏病
		if (record.getIsCahd() != null) {
			String isCahd = record.getIsCahd().equals("是") ? "心脏病	" : "";
			modelMap.addAttribute("isCahd", isCahd);
		}
		// 癌症
		if (record.getIsCancer() != null) {
			String isCancer = record.getIsCancer().equals("是") ? "癌症	" : "";
			modelMap.addAttribute("isCancer", isCancer);
		}
		// 中风
		if (record.getIsCerebralApoplexy() != null) {
			String isCerebralApoplexy = record.getIsCerebralApoplexy().equals("是	") ? "中风" : "";
			modelMap.addAttribute("isCerebralApoplexy", isCerebralApoplexy);
		}
		// 肺炎
		if (record.getIsCopd() != null) {
			String isCopd = record.getIsCopd().equals("是") ? "肺炎	" : "";
			modelMap.addAttribute("isCopd", isCopd);
		}
		// 肺结核
		if (record.getIsTuberculosis() != null) {
			String isTuberculosis = record.getIsTuberculosis().equals("是") ? "肺结核	" : "";
			modelMap.addAttribute("isTuberculosis", isTuberculosis);
		}
		// 肝炎
		if (record.getIsHepatitis() != null) {
			String isHepatitis = record.getIsHepatitis().equals("是") ? "肝炎	" : "";
			modelMap.addAttribute("isHepatitis", isHepatitis);
		}
		// 高胆固醇
		if (record.getIsCholesterol() != null) {
			String isCholesterol = record.getIsCholesterol().equals("是") ? "高胆固醇	" : "";
			modelMap.addAttribute("isCholesterol", isCholesterol);
		}
		// 高血脂
		if (record.getIsHyperlipoidemia() != null) {
			String isHyperlipoidemia = record.getIsHyperlipoidemia().equals("是") ? "高血脂	" : "";
			modelMap.addAttribute("isHyperlipoidemia", isHyperlipoidemia);
		}
		// 皮肤病
		if (record.getIsDermatosis() != null) {
			String isDermatosis = record.getIsDermatosis().equals("是") ? "皮肤病	" : "";
			modelMap.addAttribute("isDermatosis", isDermatosis);
		}
		// 手术史
		List<HrOperation> operations = operationService.selectByRecordId(record.getRecordId());
		if (operations.size() > 0) {
			StringBuffer sb = new StringBuffer();
			for (HrOperation operation : operations) {
				sb.append(
						"手术名称： " + operation.getOperationName() + "   手术时间: " + operation.getOperationTime() + "<br/>");
			}
			modelMap.addAttribute("operation", sb.toString());
		}
		// 输血史
		List<HrTransfusion> transfusions = tansfusionService.selectByRecordId(record.getRecordId());
		if (transfusions.size() > 0) {
			StringBuffer sb = new StringBuffer();
			for (HrTransfusion transfusion : transfusions) {
				sb.append("输血名称： " + transfusion.getTransfusionName() + "   输血时间: " + transfusion.getTransfusionTime()
						+ "<br/>");
			}
			modelMap.addAttribute("transfusion", sb.toString());
		}
		// 外伤史
		List<HrTrauma> traumas = traumaService.selectByRecordId(record.getRecordId());
		if (traumas.size() > 0) {
			StringBuffer sb = new StringBuffer();
			for (HrTrauma trauma : traumas) {
				sb.append("外伤名称： " + trauma.getTraumaName() + "   外伤时间: " + trauma.getTraumaTime() + "<br/>");
			}
			modelMap.addAttribute("trauma", sb.toString());
		}

	}


}
