package com.yhcrt.iHealthCloud.entity;

public class ServiceProviderWithBLOBs extends ServiceProvider {
    private String providerFacility;

    private String content;

    public String getProviderFacility() {
        return providerFacility;
    }

    public void setProviderFacility(String providerFacility) {
        this.providerFacility = providerFacility == null ? null : providerFacility.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}