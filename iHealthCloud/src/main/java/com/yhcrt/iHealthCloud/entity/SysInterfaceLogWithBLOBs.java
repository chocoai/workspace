package com.yhcrt.iHealthCloud.entity;

public class SysInterfaceLogWithBLOBs extends SysInterfaceLog {
    private String requestParams;

    private String returnValue;

    public String getRequestParams() {
        return requestParams;
    }

    public void setRequestParams(String requestParams) {
        this.requestParams = requestParams == null ? null : requestParams.trim();
    }

    public String getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(String returnValue) {
        this.returnValue = returnValue == null ? null : returnValue.trim();
    }
}