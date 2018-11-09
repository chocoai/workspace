/**
 * 
 */
package com.yhcrt.healthcloud.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/** 
 * @ClassName: Ceshi 
 * @Description: TODO
 * @author: zjd
 * @date: 2018年3月7日 上午11:09:39  
 */
public class Ceshi {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ApplicationContext cxt = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		ExcelController ExcelController = (com.yhcrt.healthcloud.controller.ExcelController) cxt.getBean("ExcelController");
		ExcelController.test();
	}
}
