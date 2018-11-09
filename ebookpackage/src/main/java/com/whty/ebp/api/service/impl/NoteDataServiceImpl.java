package com.whty.ebp.api.service.impl;

import java.io.File;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Enumeration;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bos.BosClient;
import com.baidubce.services.bos.BosClientConfiguration;
import com.baidubce.services.bos.model.PutObjectResponse;
import com.whty.common.util.GUIDGenerator;
import com.whty.common.util.SysConfig;
import com.whty.ebp.api.dao.NoteDataDao;
import com.whty.ebp.api.model.NoteData;
import com.whty.ebp.api.model.OutSideNoteData;
import com.whty.ebp.api.service.NoteDataService;

@Service("noteDataService")
public class NoteDataServiceImpl implements NoteDataService {

	@Autowired
	private NoteDataDao noteDataDao;

	@Override
	public void saveNoteDateFile(NoteData bean, MultipartFile file) {
		String ak = SysConfig.getStrValue("baidu.bos.ak");
		String sk = SysConfig.getStrValue("baidu.bos.sk");
		String bucketname = SysConfig.getStrValue("baidu.bos.bucketname");
		String pathProductName = SysConfig.getStrValue("baidu.bos.path.productname");
		String pathVersion = SysConfig.getStrValue("baidu.bos.path.version");
		String baiduBosUrl = SysConfig.getStrValue("baidu.bos.url");
		StringBuffer bosStringBuffer = new StringBuffer();

		Calendar cal = Calendar.getInstance();

		bosStringBuffer.append(pathProductName).append("/").append(pathVersion).append("/")
				.append(bean.getUserId() + "_" + bean.getPlatformCode()).append("/").append(cal.get(Calendar.YEAR))
				.append(cal.get(Calendar.MONTH)).append(cal.get(Calendar.DAY_OF_MONTH)).append("/")
				.append(cal.getTime().getTime());

		BosClientConfiguration config = new BosClientConfiguration();
		config.setCredentials(new DefaultBceCredentials(ak, sk));

		BosClient client = new BosClient(config);

		// File f = fi.getStoreLocation();

		PutObjectResponse putObjectResponse;
		try {
			CommonsMultipartFile cf = (CommonsMultipartFile) file;
			DiskFileItem fi = (DiskFileItem) cf.getFileItem();
			File f = fi.getStoreLocation();
			ZipFile zip = new ZipFile(f);
			for (Enumeration entries = zip.getEntries(); entries.hasMoreElements();) {
				ZipEntry entry = (ZipEntry) entries.nextElement();
				String zipEntryName = entry.getName();
				InputStream in = zip.getInputStream(entry);
				String filePath = bosStringBuffer.toString() + "/" + zipEntryName;
				putObjectResponse = client.putObject(bucketname, filePath, in);
				System.out.println(putObjectResponse.getETag());
				in.close();
				bean.setId(GUIDGenerator.getUUID32());
				bean.setDataFileUrl(baiduBosUrl + filePath);
				System.out.println(baiduBosUrl + filePath);
				noteDataDao.save(bean);
			}
			zip.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void saveOutSideNoteDateFile(OutSideNoteData bean, MultipartFile file) {
		String ak = SysConfig.getStrValue("baidu.bos.ak");
		String sk = SysConfig.getStrValue("baidu.bos.sk");
		String bucketname = SysConfig.getStrValue("baidu.bos.bucketname");
		String pathProductName = SysConfig.getStrValue("baidu.bos.path.productname");
		String pathVersion = SysConfig.getStrValue("baidu.bos.path.version");
		String baiduBosUrl = SysConfig.getStrValue("baidu.bos.url");
		StringBuffer bosStringBuffer = new StringBuffer();

		Calendar cal = Calendar.getInstance();

		bosStringBuffer.append(pathProductName).append("/").append(pathVersion).append("/")
				.append(bean.getUserId() + "_" + bean.getPlatformCode()).append("/").append(cal.get(Calendar.YEAR))
				.append(cal.get(Calendar.MONTH)).append(cal.get(Calendar.DAY_OF_MONTH)).append("/")
				.append(cal.getTime().getTime());

		BosClientConfiguration config = new BosClientConfiguration();
		config.setCredentials(new DefaultBceCredentials(ak, sk));

		BosClient client = new BosClient(config);

		// File f = fi.getStoreLocation();

		PutObjectResponse putObjectResponse;
		try {
			CommonsMultipartFile cf = (CommonsMultipartFile) file;
			DiskFileItem fi = (DiskFileItem) cf.getFileItem();
			File f = fi.getStoreLocation();
			ZipFile zip = new ZipFile(f);
			for (Enumeration entries = zip.getEntries(); entries.hasMoreElements();) {
				ZipEntry entry = (ZipEntry) entries.nextElement();
				String zipEntryName = entry.getName();
				InputStream in = zip.getInputStream(entry);
				String filePath = bosStringBuffer.toString() + "/" + zipEntryName;
				putObjectResponse = client.putObject(bucketname, filePath, in);
//				System.out.println(putObjectResponse.getETag());
				in.close();
				bean.setId(GUIDGenerator.getUUID32());
				bean.setDataFileUrl(baiduBosUrl + filePath);
//				System.out.println(baiduBosUrl + filePath);
				noteDataDao.saveOutSideNoteData(bean);
			}
			zip.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
