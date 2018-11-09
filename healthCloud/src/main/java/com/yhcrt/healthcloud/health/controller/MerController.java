package com.yhcrt.healthcloud.health.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yhcrt.healthcloud.health.entity.MedicalExaminationReport;
import com.yhcrt.healthcloud.health.entity.MerBloodGlucose;
import com.yhcrt.healthcloud.health.entity.MerBloodOxygen;
import com.yhcrt.healthcloud.health.entity.MerBloodPressure;
import com.yhcrt.healthcloud.health.entity.MerBmi;
import com.yhcrt.healthcloud.health.entity.MerBodyFat;
import com.yhcrt.healthcloud.health.entity.MerCholesterol;
import com.yhcrt.healthcloud.health.entity.MerElectrocardiogram;
import com.yhcrt.healthcloud.health.entity.MerTemperature;
import com.yhcrt.healthcloud.health.entity.MerUricAcid;
import com.yhcrt.healthcloud.health.entity.MerWaistHipRatio;
import com.yhcrt.healthcloud.health.service.MemberQueryService;
import com.yhcrt.healthcloud.health.service.MerService;
import com.yhcrt.healthcloud.memberBack.entity.MemberBack;
import com.yhcrt.healthcloud.util.DateUtil;

/**
 * 体检报告Controller
 * 
 * @author huzelin
 *
 */
@Controller
@RequestMapping("/healthService")
public class MerController {

	@Autowired
	private MerService merService;
	@Autowired
	private MemberQueryService memberService;

	@RequestMapping(value = "/mer")
	public String mer(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap,
			@RequestParam(name = "pageValue", defaultValue = "1") Integer page,
			@RequestParam(name = "memberId", defaultValue = "0") Integer memberId) {

		String merId = request.getParameter("merId");
		String merTime = request.getParameter("merTime");
		HashMap<String, Object> args = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(merId)) {
			args.put("merId", merId.trim());
		}
		if (StringUtils.isNotBlank(merTime)) {
			args.put("merTime", merTime);
		}
		args.put("memberId", memberId);
		// 开始分页,获取体检报告集合
		// PageHelper.startPage(page, 10);
		List<MedicalExaminationReport> merList = merService.getMersByArgs(args);
		// PageInfo<MedicalExaminationReport> p = new PageInfo<>(merList);

		// 向页面传入数据
		modelMap.addAttribute("merList", merList);
		// modelMap.addAttribute("page", p);
		modelMap.addAttribute("memberId", memberId);
		modelMap.addAttribute("merId", merId);
		modelMap.addAttribute("merTime", merTime);
		return "/health/jsp/mer";
	}

	@RequestMapping(value = "/merDetail")
	public String merDetail(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap,
			@RequestParam(name = "merId", defaultValue = "") String merId,
			@RequestParam(name = "memberId", defaultValue = "0") Integer memberId) {
		// 获取体检报告对象集合
		List<MedicalExaminationReport> merList = merService.getMerByMemberId(memberId);
		// 获取体检报告对象
		if (StringUtils.isNotBlank(merId)) {
			MedicalExaminationReport mer = merService.getMerByMerId(Integer.parseInt(merId));
			modelMap.addAttribute("mer", mer);
		}
		// 获取会员对象
		MemberBack member = memberService.getMemberByMemberId(memberId);
		// 向页面传入数据
		modelMap.addAttribute("member", member);
		modelMap.addAttribute("glucose", merService.getGlucoseByMerId(merId));
		modelMap.addAttribute("pressure", merService.getPressureByMerId(merId));
		modelMap.addAttribute("oxygen", merService.getOxygenByMerId(merId));
		modelMap.addAttribute("bmi", merService.getBmiByMerId(merId));
		modelMap.addAttribute("bodyFat", merService.getBodyFatByMerId(merId));
		modelMap.addAttribute("cholesterol", merService.getCholesterolByMerId(merId));
		modelMap.addAttribute("electrocardiogram", merService.getElectrocardiogramByMerId(merId));
		modelMap.addAttribute("temperature", merService.getTemperatureByMerId(merId));
		modelMap.addAttribute("uricAcid", merService.getUricAcidByMerId(merId));
		modelMap.addAttribute("waistHipRatio", merService.getWaistHipRatioByMerId(merId));
		modelMap.addAttribute("merList", merList);
		return "/health/jsp/mer_detail";
	}

	@RequestMapping(value = "/toAddMerBmi")
	public String toAddMer(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap,
			@RequestParam(name = "merId", defaultValue = "") String merId,
			@RequestParam(name = "memberId", defaultValue = "0") Integer memberId) {
		// 获取会员对象
		MemberBack member = memberService.getMemberByMemberId(memberId);
		MerBmi merBmi = merService.getBmiByMerId(merId);
		modelMap.addAttribute("merId", merId);
		modelMap.addAttribute("member", member);
		modelMap.addAttribute("bmi", merBmi);
		return "/health/jsp/add_mer_bmi";
	}

	@RequestMapping(value = "/addMerBmi", method = RequestMethod.POST)
	public String addMerBmi(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, MerBmi bmi,
			RedirectAttributes attr) {
		String memberId = request.getParameter("memberId");
		if (null != bmi.getCid()) {
			MerBmi merBmi = merService.getOneBmiByCid(bmi.getCid());
			BeanUtils.copyProperties(bmi, merBmi);
			merBmi.setUploadTime(DateUtil.getDateTime());
			merService.updateMerBmi(merBmi);
			attr.addAttribute("merId", bmi.getMerId());
		} else {
			MerBmi merBmi = merService.insertMerBmi(bmi, memberId);
			attr.addAttribute("merId", merBmi.getMerId());
		}
		attr.addAttribute("memberId", memberId);
		return "redirect:toAddMerBodyFat";
	}

	@RequestMapping(value = "/toAddMerBodyFat")
	public String toAddMerBodyFat(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap,
			@RequestParam(name = "merId", defaultValue = "") String merId,
			@RequestParam(name = "memberId", defaultValue = "0") Integer memberId) {
		// 获取会员对象
		MemberBack member = memberService.getMemberByMemberId(memberId);
		MerBodyFat bodyFat = merService.getBodyFatByMerId(merId);
		modelMap.addAttribute("merId", merId);
		modelMap.addAttribute("member", member);
		modelMap.addAttribute("bodyFat", bodyFat);
		return "/health/jsp/add_mer_bodyfat";
	}

	@RequestMapping(value = "/addMerBodyFat", method = RequestMethod.POST)
	public String addMerBodyFat(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap,
			MerBodyFat bodyFat, RedirectAttributes attr) {
		String memberId = request.getParameter("memberId");
		if (null != bodyFat.getCid()) {
			MerBodyFat merBodyFat = merService.getOneBodyFatByCid(bodyFat.getCid());
			BeanUtils.copyProperties(bodyFat, merBodyFat);
			merBodyFat.setUploadTime(DateUtil.getDateTime());
			merService.updateMerBodyFat(merBodyFat);
			attr.addAttribute("merId", bodyFat.getMerId());
		} else {
			MerBodyFat merBodyFat = merService.insertMerBodyFat(bodyFat, memberId);
			attr.addAttribute("merId", merBodyFat.getMerId());
		}
		attr.addAttribute("memberId", memberId);
		return "redirect:toAddMerBP";
	}

	@RequestMapping(value = "/toAddMerBP")
	public String toAddMerBP(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap,
			@RequestParam(name = "merId", defaultValue = "") String merId,
			@RequestParam(name = "memberId", defaultValue = "0") Integer memberId) {
		// 获取会员对象
		MemberBack member = memberService.getMemberByMemberId(memberId);
		MerBloodPressure bloodPressure = merService.getPressureByMerId(merId);
		modelMap.addAttribute("merId", merId);
		modelMap.addAttribute("member", member);
		modelMap.addAttribute("bp", bloodPressure);
		return "/health/jsp/add_mer_bp";
	}
	
	@RequestMapping(value = "/addMerBP", method = RequestMethod.POST)
	public String addMerBP(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap,
			MerBloodPressure bloodPressure, RedirectAttributes attr) {
		String memberId = request.getParameter("memberId");
		if (null != bloodPressure.getCid()) {
			MerBloodPressure bp = merService.getOnePressureByCid(bloodPressure.getCid());
			BeanUtils.copyProperties(bloodPressure, bp);
			bp.setUploadTime(DateUtil.getDateTime());
			bp.setDataDate(new Date());
			merService.updateMerBP(bp);
			attr.addAttribute("merId", bloodPressure.getMerId());
		} else {
			MerBloodPressure bp = merService.insertMerBP(bloodPressure, memberId);
			attr.addAttribute("merId", bp.getMerId());
		}
		attr.addAttribute("memberId", memberId);
		return "redirect:toAddMerBG";
	}
	
	@RequestMapping(value = "/toAddMerBG")
	public String toAddMerBG(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap,
			@RequestParam(name = "merId", defaultValue = "") String merId,
			@RequestParam(name = "memberId", defaultValue = "0") Integer memberId) {
		// 获取会员对象
		MemberBack member = memberService.getMemberByMemberId(memberId);
		MerBloodGlucose bloodGlucose = merService.getGlucoseByMerId(merId);
		modelMap.addAttribute("merId", merId);
		modelMap.addAttribute("member", member);
		modelMap.addAttribute("bg", bloodGlucose);
		return "/health/jsp/add_mer_bg";
	}
	
	@RequestMapping(value = "/addMerBG", method = RequestMethod.POST)
	public String addMerBG(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap,
			MerBloodGlucose bloodGlucose, RedirectAttributes attr) {
		String memberId = request.getParameter("memberId");
		if (null != bloodGlucose.getCid()) {
			MerBloodGlucose bg = merService.getOneGlucoseByCid(bloodGlucose.getCid());
			BeanUtils.copyProperties(bloodGlucose, bg);
			bg.setUploadTime(DateUtil.getDateTime());
			bg.setDataTime(DateUtil.getDate());
			merService.updateMerBG(bg);
			attr.addAttribute("merId", bloodGlucose.getMerId());
		} else {
			MerBloodGlucose bg = merService.insertMerBG(bloodGlucose, memberId);
			attr.addAttribute("merId", bg.getMerId());
		}
		attr.addAttribute("memberId", memberId);
		return "redirect:toAddMerUA";
	}
	
	@RequestMapping(value = "/toAddMerUA")
	public String toAddMerUA(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap,
			@RequestParam(name = "merId", defaultValue = "") String merId,
			@RequestParam(name = "memberId", defaultValue = "0") Integer memberId) {
		// 获取会员对象
		MemberBack member = memberService.getMemberByMemberId(memberId);
		MerUricAcid uricAcid = merService.getUricAcidByMerId(merId);
		modelMap.addAttribute("merId", merId);
		modelMap.addAttribute("member", member);
		modelMap.addAttribute("ua", uricAcid);
		return "/health/jsp/add_mer_ua";
	}
	
	@RequestMapping(value = "/addMerUA", method = RequestMethod.POST)
	public String addMerUA(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap,
			MerUricAcid uricAcid, RedirectAttributes attr) {
		String memberId = request.getParameter("memberId");
		if (null != uricAcid.getCid()) {
			MerUricAcid merUricAcid = merService.getOneUricAcidByCid(uricAcid.getCid());
			BeanUtils.copyProperties(uricAcid, merUricAcid);
			merUricAcid.setUploadTime(DateUtil.getDateTime());
			merService.updateMerUricAcid(merUricAcid);
			attr.addAttribute("merId", uricAcid.getMerId());
		} else {
			MerUricAcid merUricAcid = merService.insertMerUricAcid(uricAcid, memberId);
			attr.addAttribute("merId", merUricAcid.getMerId());
		}
		attr.addAttribute("memberId", memberId);
		return "redirect:toAddMerCholesterol";
	}
	
	@RequestMapping(value = "/toAddMerCholesterol")
	public String toAddMerCholesterol(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap,
			@RequestParam(name = "merId", defaultValue = "") String merId,
			@RequestParam(name = "memberId", defaultValue = "0") Integer memberId) {
		// 获取会员对象
		MemberBack member = memberService.getMemberByMemberId(memberId);
		MerCholesterol cholesterol = merService.getCholesterolByMerId(merId);
		modelMap.addAttribute("merId", merId);
		modelMap.addAttribute("member", member);
		modelMap.addAttribute("cholesterol", cholesterol);
		return "/health/jsp/add_mer_cholesterol";
	}
	
	@RequestMapping(value = "/addMerCholesterol", method = RequestMethod.POST)
	public String addMerCholesterol(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap,
			MerCholesterol cholesterol, RedirectAttributes attr) {
		String memberId = request.getParameter("memberId");
		if (null != cholesterol.getCid()) {
			MerCholesterol merCholesterol = merService.getOneCholesterolByCid(cholesterol.getCid());
			BeanUtils.copyProperties(cholesterol, merCholesterol);
			merCholesterol.setUploadTime(DateUtil.getDateTime());
			merService.updateMerCholesterol(merCholesterol);
			attr.addAttribute("merId", cholesterol.getMerId());
		} else {
			MerCholesterol merCholesterol = merService.insertMerCholesterol(cholesterol, memberId);
			attr.addAttribute("merId", merCholesterol.getMerId());
		}
		attr.addAttribute("memberId", memberId);
		return "redirect:toAddMerBO";
	}
	
	@RequestMapping(value = "/toAddMerBO")
	public String toAddMerBO(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap,
			@RequestParam(name = "merId", defaultValue = "") String merId,
			@RequestParam(name = "memberId", defaultValue = "0") Integer memberId) {
		// 获取会员对象
		MemberBack member = memberService.getMemberByMemberId(memberId);
		MerBloodOxygen bloodOxygen = merService.getOxygenByMerId(merId);
		modelMap.addAttribute("merId", merId);
		modelMap.addAttribute("member", member);
		modelMap.addAttribute("bo", bloodOxygen);
		return "/health/jsp/add_mer_bo";
	}
	
	@RequestMapping(value = "/addMerBO", method = RequestMethod.POST)
	public String addMerBO(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap,
			MerBloodOxygen bloodOxygen, RedirectAttributes attr) {
		String memberId = request.getParameter("memberId");
		if (null != bloodOxygen.getCid()) {
			MerBloodOxygen bo = merService.getOneOxygenByCid(bloodOxygen.getCid());
			BeanUtils.copyProperties(bloodOxygen, bo);
			bo.setUploadTime(DateUtil.getDateTime());
			merService.updateMerBO(bo);
			attr.addAttribute("merId", bloodOxygen.getMerId());
		} else {
			MerBloodOxygen bo = merService.insertMerBO(bloodOxygen, memberId);
			attr.addAttribute("merId", bo.getMerId());
		}
		attr.addAttribute("memberId", memberId);
		return "redirect:toAddMerTemp";
	}
	
	@RequestMapping(value = "/toAddMerTemp")
	public String toAddMerTemp(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap,
			@RequestParam(name = "merId", defaultValue = "") String merId,
			@RequestParam(name = "memberId", defaultValue = "0") Integer memberId) {
		// 获取会员对象
		MemberBack member = memberService.getMemberByMemberId(memberId);
		MerTemperature temperature = merService.getTemperatureByMerId(merId);
		modelMap.addAttribute("merId", merId);
		modelMap.addAttribute("member", member);
		modelMap.addAttribute("temp", temperature);
		return "/health/jsp/add_mer_temp";
	}
	
	@RequestMapping(value = "/addMerTemp", method = RequestMethod.POST)
	public String addMerTemp(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap,
			MerTemperature temperature, RedirectAttributes attr) {
		String memberId = request.getParameter("memberId");
		if (null != temperature.getCid()) {
			MerTemperature temp = merService.getOneTemperatureByCid(temperature.getCid());
			BeanUtils.copyProperties(temperature, temp);
			temp.setUploadTime(DateUtil.getDateTime());
			merService.updateMerTemperature(temp);
			attr.addAttribute("merId", temperature.getMerId());
		} else {
			MerTemperature temp = merService.insertMerTemperature(temperature, memberId);
			attr.addAttribute("merId", temp.getMerId());
		}
		attr.addAttribute("memberId", memberId);
		return "redirect:toAddMerECG";
	}
	
	@RequestMapping(value = "/toAddMerECG")
	public String toAddMerECG(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap,
			@RequestParam(name = "merId", defaultValue = "") String merId,
			@RequestParam(name = "memberId", defaultValue = "0") Integer memberId) {
		// 获取会员对象
		MemberBack member = memberService.getMemberByMemberId(memberId);
		MerElectrocardiogram electrocardiogram = merService.getElectrocardiogramByMerId(merId);
		modelMap.addAttribute("merId", merId);
		modelMap.addAttribute("member", member);
		modelMap.addAttribute("ecg", electrocardiogram);
		return "/health/jsp/add_mer_ecg";
	}
	
	@RequestMapping(value = "/addMerECG", method = RequestMethod.POST)
	public String addMerECG(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap,
			MerElectrocardiogram electrocardiogram, RedirectAttributes attr) {
		String memberId = request.getParameter("memberId");
		if (null != electrocardiogram.getCid()) {
			MerElectrocardiogram ecg = merService.getOneElectrocardiogramByCid(electrocardiogram.getCid());
			BeanUtils.copyProperties(electrocardiogram, ecg);
			ecg.setUploadTime(DateUtil.getDateTime());
			merService.updateMerECG(ecg);
			attr.addAttribute("merId", electrocardiogram.getMerId());
		} else {
			MerElectrocardiogram ecg = merService.insertMerECG(electrocardiogram, memberId);
			attr.addAttribute("merId", ecg.getMerId());
		}
		attr.addAttribute("memberId", memberId);
		return "redirect:toAddMerWHR";
	}
	
	@RequestMapping(value = "/toAddMerWHR")
	public String toAddMerWHR(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap,
			@RequestParam(name = "merId", defaultValue = "") String merId,
			@RequestParam(name = "memberId", defaultValue = "0") Integer memberId) {
		// 获取会员对象
		MemberBack member = memberService.getMemberByMemberId(memberId);
		MerWaistHipRatio waistHipRatio = merService.getWaistHipRatioByMerId(merId);
		modelMap.addAttribute("merId", merId);
		modelMap.addAttribute("member", member);
		modelMap.addAttribute("whr", waistHipRatio);
		return "/health/jsp/add_mer_whr";
	}
	
	@RequestMapping(value = "/addMerWHR", method = RequestMethod.POST)
	public String addMerWHR(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap,
			MerWaistHipRatio waistHipRatio, RedirectAttributes attr) {
		String memberId = request.getParameter("memberId");
		if (null != waistHipRatio.getCid()) {
			MerWaistHipRatio whr = merService.getOneWaistHipRatioByCid(waistHipRatio.getCid());
			BeanUtils.copyProperties(waistHipRatio, whr);
			whr.setUploadTime(DateUtil.getDateTime());
			merService.updateMerWHR(whr);
			attr.addAttribute("merId", waistHipRatio.getMerId());
		} else {
			MerWaistHipRatio whr = merService.insertMerWHR(waistHipRatio, memberId);
			attr.addAttribute("merId", whr.getMerId());
		}
		attr.addAttribute("memberId", memberId);
		return "redirect:mer";
	}

}
