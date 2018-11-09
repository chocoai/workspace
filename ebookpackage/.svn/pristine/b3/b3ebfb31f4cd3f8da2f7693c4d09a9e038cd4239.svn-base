package com.whty.ebp.manage.utils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

public class QrCode {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String content = "http://www.baidu.com";// 内容  
		int width = 200; // 图像宽度  
		int height = 200; // 图像高度  
		String format = "png";// 图像类型 
		File file = new File("D://temp//zxing.png");
		makeQrCode(format,content,width,height,file);
	}

	public static void makeQrCode(String format,String content,int width,int height,File file){
		try {
			Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();  
			hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");  
			BitMatrix bitMatrix = new MultiFormatWriter().encode(content,  
			        BarcodeFormat.QR_CODE, width, height, hints);// 生成矩阵  
			MatrixToImageWriter.writeToFile(bitMatrix, format, file);// 输出图像
		} catch (WriterException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
