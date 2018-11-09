package com.smart.util;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSendTool {
	// 邮箱服务器
	private String host;
	
	// 邮箱服务器端口号
	private String port;

	// 邮箱账号
	private String username;

	// 邮箱密码
	private String password;

	// 邮件发送者的地址
	private String mailFrom;

	// 邮件发送者的名称
	private String personalName;

	public EmailSendTool() {
		init();
	}

	public void init() {
		this.host = Property.getProperty("/eccs.properties", "mail.smtp.host");
		this.port = Property.getProperty("/eccs.properties", "mail.smtp.port");
		this.username = Property.getProperty("/eccs.properties", "mail.sender.username");
		this.password = Property.getProperty("/eccs.properties", "mail.sender.password");
		this.mailFrom = Property.getProperty("/eccs.properties", "mail.sender.username");
		this.personalName = Property.getProperty("/eccs.properties", "mail.sender.nickname");
	}

	/**
	 * 
	 * @param toAdress
	 *            收件人邮箱地址
	 * @param subject
	 *            邮件主题
	 * @param content
	 *            邮件正文
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException
	 */
	public void send(String toAdress, String subject, String content) {
		try {
			Properties props = new Properties();
			// 进行邮件服务器用户认证
			Authenticator auth = new EmailAutherticator();
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.port", port);
			props.put("mail.smtp.auth", "true");
			Session session = Session.getDefaultInstance(props, auth);
			session.setDebug(true);
			// 设置session和邮件服务器进行通讯
			MimeMessage message = new MimeMessage(session);
			// 设置邮件主题
			message.setSubject(subject);
			// 设置邮件正文
			message.setText(content);
			// 设置邮件发送日期
			message.setSentDate(new Date());
			Address fromAddress = new InternetAddress(mailFrom, personalName);
			// 设置邮件发送者的地址
			message.setFrom(fromAddress);
			// 设置邮件接收方的地址
			InternetAddress[] toAdresss = InternetAddress.parse(toAdress);
			message.setRecipients(Message.RecipientType.TO, toAdresss);
			// 发送邮件
			Transport.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public String getSubject(){
		return Property.getProperty("/eccs.properties", "mail.subject");
	}
	
	public String buildContent(String projectNo, String projectName,
			String currentStep) {
		String prefix = Property.getProperty("/eccs.properties", "mail.content.prefix");
		String content = "项目编号：" + projectNo + "，项目名称：" + projectName + "，当前环节："
				+ currentStep;
		return prefix + content;
	}

	/**
	 * 用来进行服务器对用户的认证
	 */
	public class EmailAutherticator extends Authenticator {
		public EmailAutherticator() {
			super();
		}

		public EmailAutherticator(String user, String pwd) {
			super();
			username = user;
			password = pwd;
		}

		public PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(username, password);
		}
	}

	public static void main(String[] args) {
		EmailSendTool sendEmail = new EmailSendTool();
		try {
			sendEmail.send(
					"raopanfeng@yhcrt.com,,,zhengjiadong@yhcrt.com,,,,",
					sendEmail.getSubject(),
					"欢迎使用XXX平台，要激活您的帐户并验证您的电子邮件地址，请点击以下链接：https://www.baidu.com/");
			System.out.println("Email sent successfully");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}