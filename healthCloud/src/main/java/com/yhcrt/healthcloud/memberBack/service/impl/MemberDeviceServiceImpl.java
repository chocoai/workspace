/**
 * @Title:   MemberDeviceServiceImpl.java 
 * @Package: com.yhcrt.healthcloud.memberBack.service.impl  
 * @Description: 
 * @author: rpf
 * @date: 2018年4月2日 
 * @version V1.0 
 * Copyright © 2018 Wuhan YHCRT Technology Co.Ltd. All rights reserved.
 */
package com.yhcrt.healthcloud.memberBack.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yhcrt.healthcloud.memberBack.entity.MemberDeviceBack;
import com.yhcrt.healthcloud.memberBack.entity.MemberDeviceExampleBack;
import com.yhcrt.healthcloud.memberBack.mapper.MemberDeviceBackMapper;
import com.yhcrt.healthcloud.memberBack.service.MemberDeviceService;

/**
 * @ClassName: MemberDeviceServiceImpl
 * @Description:
 * @version V1.0
 * @author rpf
 * @date: 2018年4月2日
 */

@Service
public class MemberDeviceServiceImpl implements MemberDeviceService {

	@Autowired
	private MemberDeviceBackMapper memberDeviceMapper;
	
	@Override
	public String getImeiByMemberId(Integer memberId) {
		MemberDeviceExampleBack example = new MemberDeviceExampleBack();
		example.createCriteria().andMemberIdEqualTo(memberId);
		List<MemberDeviceBack> memberDevices = memberDeviceMapper.selectByExample(example);
		String imei = "";
		for (MemberDeviceBack memberDevice : memberDevices) {
			imei = memberDevice.getImei()+ "<br>" + imei ;
		}
		return imei;
	}

}
