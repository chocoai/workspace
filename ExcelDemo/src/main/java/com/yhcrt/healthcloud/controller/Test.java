/**
 * 
 */
package com.yhcrt.healthcloud.controller;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/** 
 * @ClassName: Test 
 * @Description: TODO
 * @author: zjd
 * @date: 2018年3月7日 上午10:47:03  
 */
@Aspect
@Component
public class Test {
	@Pointcut("execution (* com.yhcrt.healthcloud.controller.ExcelController.*(..))")
	public void test(){
		System.out.println("通知到位了");
	}
	
	// 声明该方法是一个前置通知：在目标方法开始之前执行 
	@Before("test()")
    public void doAccessCheck() {
        System.out.println("前置通知");
    }
	
	@After("test()")
    public void affAccessCheck() {
        System.out.println("后置通知");
    }
}
