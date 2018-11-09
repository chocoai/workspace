package com.yhcrt.healthcloud.health.vo;

import com.yhcrt.healthcloud.health.entity.HdBloodPressure;
import com.yhcrt.healthcloud.health.entity.HdPulse;
import com.yhcrt.healthcloud.health.entity.HdSleep;
import com.yhcrt.healthcloud.health.entity.HdStep;
import com.yhcrt.healthcloud.memberBack.entity.MemberBack;

/**
 * 会员列表条目vo封装对象
 * @author asus
 *
 */
public class MemberListItem {
	
	private MemberBack member;
	
	private HdPulse pulse;
	
	private HdStep step;
	
	private HdSleep sleep;
	
	private HdBloodPressure pressure;

	public HdPulse getPulse() {
		return pulse;
	}

	public void setPulse(HdPulse pulse) {
		this.pulse = pulse;
	}

	public HdStep getStep() {
		return step;
	}

	public void setStep(HdStep step) {
		this.step = step;
	}

	public HdSleep getSleep() {
		return sleep;
	}

	public void setSleep(HdSleep sleep) {
		this.sleep = sleep;
	}

	public HdBloodPressure getPressure() {
		return pressure;
	}

	public void setPressure(HdBloodPressure pressure) {
		this.pressure = pressure;
	}

	public MemberBack getMember() {
		return member;
	}

	public void setMember(MemberBack member) {
		this.member = member;
	}
	
}
