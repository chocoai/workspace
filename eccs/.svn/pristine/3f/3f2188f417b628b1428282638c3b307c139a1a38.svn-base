/**
 * 
 */
package com.smart.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.model.ProceStepDef;
import com.smart.model.Project;
import com.smart.model.T_hremployee;
import com.smart.util.Constants;
import com.smart.util.EmailSendTool;

/**
 * @Description:
 */
@Service
public class EmailService {

	public EmailService() {

	}

	/**
	 * 
	 * @param userIds 接收邮件用户ID集合
	 * @param project 
	 * @param stepCode
	 */
	public void sendEmail(String userIds, Project project, String stepCode) {
		StringBuilder emailAddress = new StringBuilder();
		if (StringUtils.isNotBlank(userIds)) {
			String[] userIdArr = userIds.split(",");
			for (int i = 0; i < userIdArr.length; i++) {
				if (StringUtils.isNumeric(userIdArr[i])) {
					T_hremployee hrEmployee = hremployeeService
							.getSysCode(Integer.valueOf(userIdArr[i]));
					if (hrEmployee != null) {
						emailAddress.append(hrEmployee.getEmail()).append(",");
					}
				}
			}
			ProceStepDef nextStep = proceStepDefService
					.getStepByStepCode(Constants.StepCode.STEP6);
			EmailSendTool sendEmail = new EmailSendTool();
			String subject = sendEmail.getSubject();
			String emailContent = sendEmail.buildContent(project.getNo(),
					project.getName(), nextStep.getStepName());
			sendEmail.send(emailAddress.toString(), subject, emailContent);
		}
	}

	@Autowired
	private T_hremployeeService hremployeeService;

	@Autowired
	private ProceStepDefService proceStepDefService;
}