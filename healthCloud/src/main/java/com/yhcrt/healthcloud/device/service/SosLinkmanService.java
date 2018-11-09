package com.yhcrt.healthcloud.device.service;

import java.util.List;

import com.yhcrt.healthcloud.device.entity.DsSosLinkman;

public interface SosLinkmanService {
	
	int insert(DsSosLinkman dsSosLinkman);
	
	int delete(Integer cId);
	
	int update(DsSosLinkman dsSosLinkman);
	
	List<DsSosLinkman> listByArgs(String imei);
	
	DsSosLinkman getLinkmanByCid(Integer cId);

}
