package com.yhcrt.iHealthCloud.entity;

public class SysInterfaceLog {
    private String cid;

    private String callTime;

    private String callIp;

    private String serviceName;

    private String callUrl;

    private String replyTime;

    private String exceptionMsg;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid == null ? null : cid.trim();
    }

    public String getCallTime() {
        return callTime;
    }

    public void setCallTime(String callTime) {
        this.callTime = callTime == null ? null : callTime.trim();
    }

    public String getCallIp() {
        return callIp;
    }

    public void setCallIp(String callIp) {
        this.callIp = callIp == null ? null : callIp.trim();
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName == null ? null : serviceName.trim();
    }

    public String getCallUrl() {
        return callUrl;
    }

    public void setCallUrl(String callUrl) {
        this.callUrl = callUrl == null ? null : callUrl.trim();
    }

    public String getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(String replyTime) {
        this.replyTime = replyTime == null ? null : replyTime.trim();
    }

    public String getExceptionMsg() {
        return exceptionMsg;
    }

    public void setExceptionMsg(String exceptionMsg) {
        this.exceptionMsg = exceptionMsg == null ? null : exceptionMsg.trim();
    }
}