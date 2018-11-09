package com.whty.assis.api.service.impl;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.whty.assis.api.service.RSAService;
import com.whty.assis.api.utils.RSACoder;
import com.whty.assis.demo.model.EbpSoftLicence;

/*
 * RSA加密解密service
 */
@Service
public class RSAServiceImpl implements RSAService {

	// @Autowired
	// private EbpSoftLicenceKeyDao ebpSoftLicenceKeyDao;
	/*
	 * RSA加密
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Map encodedData(EbpSoftLicence ebpSoftLicence, Map respMap) throws Exception {
		// 生成密钥
		String publicKey;
		String privateKey;
		Map<String, Object> keyMap = RSACoder.initKey();
		publicKey = RSACoder.getPublicKey(keyMap);
		privateKey = RSACoder.getPrivateKey(keyMap);
		// System.err.println("公钥: \n\r" + publicKey);
		// System.err.println("私钥： \n\r" + privateKey);

		// 加密数据
		// Map map = new HashMap();
		// map.put("mac_card_info",
		// null==ebpSoftLicence.getMac_card_info()?null:ebpSoftLicence.getMac_card_info());
		// map.put("cpu_info",
		// null==ebpSoftLicence.getCpu_info()?null:ebpSoftLicence.getCpu_info());
		// map.put("harddisk_info",
		// null==ebpSoftLicence.getHarddisk_info()?null:ebpSoftLicence.getHarddisk_info());
		// map.put("start_time",
		// null==ebpSoftLicence.getStart_time()?null:ebpSoftLicence.getStart_time().getTime());
		// map.put("end_time",
		// null==ebpSoftLicence.getEnd_time()?null:ebpSoftLicence.getEnd_time().getTime());
		// map.put("max_use_count", ebpSoftLicence.getMax_use_count());

		// System.err.println("公钥加密——私钥解密");
		// String inputStr = JSONObject.fromObject( map).toString();

		/*
		 * 4/8/8/30/30/30
		 * MaxClientCount/StartTime/EndTime/CPUInfo/MacInfo/DiskInfo
		 * 
		 * 4/13/13/4/27/27/27
		 * MaxClientCount/StartTime/sysTime/sanUseDayCount/CPUInfo/MacInfo/
		 * DiskInfo
		 */
		String MaxClientCount = this.makeStr(
				ebpSoftLicence.getMax_use_count() > 9999 ? "9999" : ebpSoftLicence.getMax_use_count().toString(), 4);
		String StartTime = this.makeStr(
				null == ebpSoftLicence.getStart_time() ? "" : ebpSoftLicence.getStart_time().getTime() + "", 13);
		String sysTime = this.makeStr(new Date().getTime() + "", 13);

		String sanUseDayCount = "";
		Date startTime = ebpSoftLicence.getStart_time();
		Date endTime = ebpSoftLicence.getEnd_time();
		if (endTime != null) {
			long days = TimeUnit.MILLISECONDS.toDays(endTime.getTime() - startTime.getTime());
			sanUseDayCount = this.makeStr(days > 9999 ? "9999" : days + "", 4);
		} else {
			sanUseDayCount = this.makeStr("9999", 4);
		}
		String CPUInfo = this
				.makeStr(null == ebpSoftLicence.getCpu_info() ? "" : ebpSoftLicence.getCpu_info().toString(), 27);
		String MacInfo = this.makeStr(
				null == ebpSoftLicence.getMac_card_info() ? "" : ebpSoftLicence.getMac_card_info().toString(), 27);
		String DiskInfo = this.makeStr(
				null == ebpSoftLicence.getHarddisk_info() ? "" : ebpSoftLicence.getHarddisk_info().toString(), 27);

		String inputStr = MaxClientCount + StartTime + sysTime + sanUseDayCount + CPUInfo + MacInfo + DiskInfo;

		byte[] data = inputStr.getBytes();
		byte[] encodedData = RSACoder.encryptByPrivateKey(data, privateKey);
		// String encodedDataStr = new String(encodedData);
		String encodedDataStr = RSACoder.encryptByPrivateKey(inputStr, privateKey);
		// System.out.println("加密后：\r\n" + encodedDataStr);
		// 生成签名
		String sign = RSACoder.sign(encodedData, privateKey);
		// System.err.println("签名:\r" + sign);
		// 验证签名
		// boolean status = RSACoder.verify(encodedData, publicKey, sign);
		// System.err.println("状态:\r" + status);

		// 保存公钥、私钥和签名到数据库
		// EbpSoftLicenceKey ebpSoftLicenceKey = new EbpSoftLicenceKey();
		// ebpSoftLicenceKey.setId(GUIDGenerator.getUUID32());
		// ebpSoftLicenceKey.setPrivateKey(privateKey);
		// ebpSoftLicenceKey.setPublicKey(publicKey);
		// ebpSoftLicenceKey.setSign_info(sign);
		// ebpSoftLicenceKey.setCreate_time(new Date());
		// ebpSoftLicenceKey.setUpdate_time(new Date());
		// ebpSoftLicenceKeyDao.save(ebpSoftLicenceKey);
		// 返回
		respMap.put("publicKey", publicKey);
		respMap.put("sign", sign);
		respMap.put("encryption", encodedDataStr);
		return respMap;
	}

	/**
	 * 拼接字符串 1.大于len时，截取len长度 2.小于len时，在字符串头部添加空格补齐
	 * 
	 * @param str
	 * @param len
	 * @return
	 */
	private String makeStr(String str, int len) {
		if (str.length() > len) {
			str = str.substring(0, len);
		} else if (str.length() < len) {
			int strLen = str.length();
			for (int i = 0; i < len - strLen; i++) {
				str = " " + str;
			}
		}
		return str;
	}

}
