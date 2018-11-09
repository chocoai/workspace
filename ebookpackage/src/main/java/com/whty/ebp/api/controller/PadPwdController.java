package com.whty.ebp.api.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.whty.ebp.api.service.PadPwdService;
import com.whty.ebp.api.utils.PadPwdUtils;
import com.whty.ebp.api.utils.TOTP;

@Controller
@RequestMapping("/api/padPwd")
public class PadPwdController {

	@Autowired
	private PadPwdService padPwdService;

	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request, HttpServletResponse response, Model model) {

//		String personId = request.getParameter("personId");
//		String platformCode = request.getParameter("platformCode");
//
//		Map<String, Object> param = new HashMap<String, Object>();
//		param.put("userId", personId);
//		param.put("userPlatformCode", platformCode);
//		List<Map<String, Object>> touchTeacher = padPwdService.find(param);
//
//		String isShow = "2";
//
//		if (personId != null && platformCode != null) {
//
//			if (touchTeacher != null && touchTeacher.size() > 0) {
//				isShow = "1";
//				Calendar cal = Calendar.getInstance();
//				SimpleDateFormat sdf = new SimpleDateFormat("ddHHmm");
//				Random random = new Random(new Date().getTime());
//				Integer min = 30;
//
//				int r = random.nextInt(100) % (100 - 0 + 1) + 0;
//
//				cal.add(Calendar.MINUTE, min);
//				String pwd = "";
//				String time = sdf.format(cal.getTime());
//				pwd = time + r; // 日期小时分钟 +有效分钟
//				pwd = PadPwdUtils.creatPwd(r);
//
//				// pwd = (Integer.parseInt(pwd) + 121212000) + "";
//
//				// pwd = PadPwdUtils.toHexStr(pwd); // 16进制密码
//				String invaildTime = cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE);
//
//				model.addAttribute("pwd", pwd.toUpperCase());
//				model.addAttribute("invaildTime", invaildTime);
//			}
//
//		}
//
//		model.addAttribute("isShow", isShow);
//		model.addAttribute("personId", personId);
//		model.addAttribute("platformCode", platformCode);
//		return "padPwd/index";
		
		String personId = request.getParameter("personId");
		String platformCode = request.getParameter("platformCode");

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("userId", personId);
		param.put("userPlatformCode", platformCode);
		List<Map<String, Object>> touchTeacher = padPwdService.find(param);

		String isShow = "2";

		if (personId != null && platformCode != null) {

			if (touchTeacher != null && touchTeacher.size() > 0) {
				isShow = "1";
				Calendar cal = Calendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat("ddHHmm");
				Random random = new Random(new Date().getTime());
				Integer min = 30;

				int r = random.nextInt(100) % (100 - 0 + 1) + 0;

				cal.add(Calendar.MINUTE, min);
				String pwd = "";
				String time = sdf.format(cal.getTime());
				pwd = time + r; // 日期小时分钟 +有效分钟
				pwd = PadPwdUtils.creatPwd(r);

				// pwd = (Integer.parseInt(pwd) + 121212000) + "";

				// pwd = PadPwdUtils.toHexStr(pwd); // 16进制密码
				String invaildTime = cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE);

				String steps = "0";
				long T0 = 0;
				long X = 1800 * 1000;
				Date date = new Date();
				Long nowTime = date.getTime();
				// long T = (testTime[i] - T0) / X;

				long T = (nowTime - T0) / X;

				steps = Long.toHexString(T).toUpperCase();
				while (steps.length() < 16)
					steps = "0" + steps;
				String seed = "3132333435363738393031323334353637383930";
				String pwd2 = TOTP.generateTOTP(seed, steps, "6", "HmacSHA1");

				model.addAttribute("pwd", pwd2);
				model.addAttribute("invaildTime", invaildTime);
			}

		}

		model.addAttribute("isShow", isShow);
		model.addAttribute("personId", personId);
		model.addAttribute("platformCode", platformCode);
		return "padPwd/showPwd";
	}

}
