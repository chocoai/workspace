package com.whty.mxbj.api.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.whty.mxbj.base.controller.BaseController;
import com.whty.mxbj.common.constants.ResultConstants;

@Controller
@RequestMapping("api/email")
public class EmailController extends BaseController {

	@Autowired
	private JavaMailSender mailSender; // 自动注入的Bean

	@Value("${spring.mail.username}")
	private String Sender; // 读取配置文件中的参数

	@RequestMapping(value = "sendMessage", method = RequestMethod.POST)
	public void showImg(HttpServletRequest request, HttpServletResponse response, Model model) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String receiver = request.getParameter("receiver");// 接收人
		String projectName = request.getParameter("projectName");// 项目名
		String message = request.getParameter("message");// 消息
		String subject = request.getParameter("subject");// 主题

		if (!receiver.matches("[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+")) {
			resultMap.put(ResultConstants.RESULT, ResultConstants.FAIL_CODE);
			resultMap.put(ResultConstants.RESULT_MSG, "邮箱验证失败");
			printJson(response, resultMap);
		}

		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom(Sender);
		simpleMailMessage.setTo(receiver); // 自己给自己发送邮件
		simpleMailMessage.setSubject("[" + projectName + "]" + subject);
		simpleMailMessage.setText(message);
		mailSender.send(simpleMailMessage);

		resultMap.put(ResultConstants.RESULT, ResultConstants.SUCCESS_CODE);
		resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.SUCCESS_MESSAGE);
		logger.info("to " + receiver + " projectName:" + projectName + "subject:" + subject);
		printJson(response, resultMap);

	}
}
