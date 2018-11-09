package com.yhcrt.healthcloud.device.entity;

public class CallRecord {
	
    private Integer callId;	//呼叫记录表主键id

    private Integer refId;	//呼叫人关联id
    
    private Integer callInOut;	//呼叫类型(1.呼入 2.呼出)

    private String type;	//关联类型(emp doc member)

    private String callName;	//呼叫人姓名

    private String phoneNo;	//呼叫电话号码

    private Integer isAnswer;	//是否接听(1是 0否)

    private String callDuration;	//通话时长
    
    private String callTime;	//接听时间

    private String createTime;	//呼叫时间
    
    public Integer getCallInOut() {
		return callInOut;
	}

	public void setCallInOut(Integer callInOut) {
		this.callInOut = callInOut;
	}

	public String getCallTime() {
		return callTime;
	}

	public void setCallTime(String callTime) {
		this.callTime = callTime;
	}

	public Integer getCallId() {
        return callId;
    }

    public void setCallId(Integer callId) {
        this.callId = callId;
    }

    public Integer getRefId() {
        return refId;
    }

    public void setRefId(Integer refId) {
        this.refId = refId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getCallName() {
        return callName;
    }

    public void setCallName(String callName) {
        this.callName = callName == null ? null : callName.trim();
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo == null ? null : phoneNo.trim();
    }

    public Integer getIsAnswer() {
        return isAnswer;
    }

    public void setIsAnswer(Integer isAnswer) {
        this.isAnswer = isAnswer;
    }

    public String getCallDuration() {
        return callDuration;
    }

    public void setCallDuration(String callDuration) {
        this.callDuration = callDuration == null ? null : callDuration.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }
    
    public String getCallInOutView() {
    	String callInOutView = "";
    	if(callInOut == 1){
    		callInOutView = "呼入";
    	}else if(callInOut == 2){
    		callInOutView = "呼出";
    	}
        return callInOutView;
    }
    
    public String getTypeView() {
    	String typeView = "";
    	if("emp".equals(type)){
    		typeView = "员工";
    	}else if("doc".equals(type)){
    		typeView = "医师";
    	}else if("member".equals(type)){
    		typeView = "会员";
    	}
        return typeView;
    }
    
    public String getIsAnswerView() {
    	String isAnswerView = "";
    	if(1 == isAnswer){
    		isAnswerView = "是";
    	}else if(0 == isAnswer){
    		isAnswerView = "否";
    	}
        return isAnswerView;
    }
    
}