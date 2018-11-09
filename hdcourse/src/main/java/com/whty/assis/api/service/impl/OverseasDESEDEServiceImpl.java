/**
 * 
 */
package com.whty.assis.api.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;

import com.whty.assis.api.service.OverseasDESEDEService;
import com.whty.assis.demo.model.OverseasEbpSoftLicence;
import com.whty.assis.manage.utils.DESEDEUtil;
import com.whty.common.util.SysConfig;

/**
 * @author zhangzheng
 * @date 2018年5月17日
 */
@Service
public class OverseasDESEDEServiceImpl implements OverseasDESEDEService {

	@Override
	public String encodedData(OverseasEbpSoftLicence licence, String random) throws Exception {

		String licenceCode = licence.getLicence_code();

		int maxUseCount = licence.getMax_use_count();

		String maxClientCount = maxUseCount > 9999 ? "9999" : maxUseCount + "";

		Date StartTime = licence.getStart_time();
		String startTime = StartTime == null ? "" : StartTime.getTime() + "";

		String sysTime = new Date().getTime() + "";

		String canUseDayCount = null;
		Date EndTime = licence.getEnd_time();
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
		// 13/13/4/27/27/27
		// StartTime/CurrentTime/CanUseCount/CPUInfo/MacInfo/DiskInfo
		String data = licenceCode + "/" + maxClientCount + "/" + startTime + "/" + sysTime + "/" + canUseDayCount + "/"
				+ CPUInfo + "/" + MacInfo + "/" + DiskInfo + "/" + licence.getEbp_auth() + "/" + licence.getSdp_auth()
				+ "/" + licence.getAm_auth();

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