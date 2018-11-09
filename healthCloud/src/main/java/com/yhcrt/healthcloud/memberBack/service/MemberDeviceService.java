/**
 * @Title:   MemberDeviceService.java 
 * @Package: com.yhcrt.healthcloud.memberBack.service  
 * @Description: 
 * @author: rpf
 * @date: 2018年4月2日 
 * @version V1.0 
 * Copyright © 2018 Wuhan YHCRT Technology Co.Ltd. All rights reserved.
 */
package com.yhcrt.healthcloud.memberBack.service;

 /**
 * @ClassName: MemberDeviceService
 * @Description:
 * @version V1.0 
 * @author rpf
 * @date: 2018年4月2日 
 */
public interface MemberDeviceService {
	
	String getImeiByMemberId(Integer memberId);

}
