package com.fxzhj.model;

import java.io.Serializable;

public class Port implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    private Long id;

    //所属设备id
    private Long deviceId;
    
    //端口编号
    private Integer portNo;

    //当前状态(0:停用 1：启用)
    private Integer status;

    //节点id
    private String nodeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPortNo() {
		return portNo;
	}

	public void setPortNo(Integer portNo) {
		this.portNo = portNo;
	}

	public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

}