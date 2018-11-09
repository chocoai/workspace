package com.whty.assis.api.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whty.assis.api.service.DESEDEService;
import com.whty.assis.demo.model.EbpSoftLicence;
import com.whty.assis.manage.dao.HdCodeDao;
import com.whty.assis.manage.dao.HdCodeListenDao;
import com.whty.assis.manage.model.HdCodeListen;
import com.whty.assis.manage.utils.DESEDEUtil;
import com.whty.common.util.SysConfig;

@Service
public class DESEDEServiceImpl implements DESEDEService {

	@Autowired
	private HdCodeListenDao hdCodeListenDao;
	@Autowired
	private HdCodeDao hdCodeDao;

	@Override
	public String encodedData(EbpSoftLicence licence, String random) throws Exception {

		String licenceCode = licence.getLicence_code();

		int maxUseCount = licence.getMax_use_count();

		String maxClientCount = maxUseCount > 9999 ? "9999" : maxUseCount + "";

		Date StartTime = licence.getStart_time();
		String startTime = StartTime == null ? "" : StartTime.getTime() + "";

		String sysTime = new Date().getTime() + "";

		String canUseDayCount = null;
		Date EndTime = licence.getEnd_time();
		
		Calendar cal = Calendar.getInstance();

		cal.setTime(EndTime);

		cal.add(Calendar.DATE,1);

		EndTime =cal.getTime();
		
		if (EndTime != null) {
			long days = TimeUnit.MILLISECONDS.toDays(EndTime.getTime() - StartTime.getTime());
			canUseDayCount = days > 9999 ? "9999" : days + "";
		} else {
			canUseDayCount = "9999";
		}

		String cpu = licence.getCpu_info();
		String CPUInfo = cpu == null ? "" : cpu;

		String mac = licence.getMac_card_info();
		String MacInfo = mac == null ? "" : mac;

		String disk = licence.getHarddisk_info();
		String DiskInfo = disk == null ? "" : disk;

		if (licence.getEbp_auth() == null) {
			licence.setEbp_auth("1");
		}

		if (licence.getSdp_auth() == null) {
			licence.setSdp_auth("1");
		}

		if (licence.getAm_auth() == null) {
			licence.setAm_auth("1");
		}
		// List<HdCode> hdCodes = hdCodeDao.getList();
		List<HdCodeListen> hdCodeListens = hdCodeListenDao.getList(licenceCode);
		int sum = 0;
		for (HdCodeListen HdCodeListen : hdCodeListens) {
			sum = sum | HdCodeListen.getCodeId();
		}

		/*
		 * StringBuffer gongneng = new StringBuffer(); if(hdCodes!=null &&
		 * hdCodes.size()>0){ for(int i=0;i<hdCodes.size();i++){
		 * if(hdCodeListens!=null && hdCodeListens.size()>0){ String test ="0,";
		 * for(int j=0;j<hdCodeListens.size();j++){
		 * if(hdCodes.get(i).getId()==hdCodeListens.get(j).getCodeId()){
		 * test="1,"; break; } } gongneng.append(test); }else {
		 * gongneng.append("0,"); } } }
		 */
		String data = null;
		if (sum == 0) {
			data = licenceCode + "/" + maxClientCount + "/" + startTime + "/" + sysTime + "/" + canUseDayCount + "/"
					+ CPUInfo + "/" + MacInfo + "/" + DiskInfo + "/" + licence.getEbp_auth() + "/"
					+ licence.getSdp_auth() + "/" + licence.getAm_auth();
		} else {
			data = licenceCode + "/" + maxClientCount + "/" + startTime + "/" + sysTime + "/" + canUseDayCount + "/"
					+ CPUInfo + "/" + MacInfo + "/" + DiskInfo + "/" + licence.getEbp_auth() + "/"
					+ licence.getSdp_auth() + "/" + licence.getAm_auth() + "/" + sum;
		}

		byte[] dataBytes = data.getBytes("UTF-8");

		String GK = SysConfig.getStrValue("GK");
		byte[] gkBytes = DESEDEUtil.HexString2Bytes(GK);

		byte[] randomBytes = Base64.decodeBase64(random.getBytes("UTF-8"));

		byte[] left = DESEDEUtil.encrypt(randomBytes, gkBytes);
		byte[] right = DESEDEUtil.change(left);
		byte[] vk = DESEDEUtil.byteMerger(randomBytes, right);

		byte[] encodeData = DESEDEUtil.encrypt(dataBytes, vk);

		return new String(Base64.encodeBase64(encodeData), "UTF-8");
	}

	/**
	 * 拼接字符串---- 1.大于len时，截取len长度 2.小于len时，在字符串头部添加空格补齐
	 * 
	 */
	@SuppressWarnings("unused")
	private String makeStr(String str, int len) {
		int strLen = str.length();
		if (strLen > len) {
			return str.substring(0, len);
		}

		if (strLen < len) {
			StringBuilder sb = new StringBuilder("");
			for (int i = 0; i < len - strLen; i++)
				sb.append(" ");
			sb.append(str);
			return sb.toString();
		}

		return str;
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		byte[] bs = new byte[] { 0x11, 0x22, 0x4F, 0x58, (byte) 0x88, 0x10, 0x40, 0x38 };
		String str = new String(Base64.encodeBase64(bs), "UTF-8");
		System.out.println(str);
	}

}
