package com.whty.assis.demo.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.whty.page.util.HandlerResult;

public interface InstallLogService {

	HandlerResult queryInstallLog(Map<String, Object> paramMap);

	HSSFWorkbook exportExcel(HttpServletRequest request, HttpServletResponse response) throws Exception;

}
