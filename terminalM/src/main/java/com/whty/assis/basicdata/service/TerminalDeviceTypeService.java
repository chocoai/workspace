package com.whty.assis.basicdata.service;

import java.util.List;
import java.util.Map;

import com.whty.assis.basicdata.model.TerminalDeviceType;
import com.whty.page.util.HandlerResult;

public interface TerminalDeviceTypeService {

	public void saveTerminalDeviceType(TerminalDeviceType bean);

	public List<TerminalDeviceType> listByCondition(Map<String, Object> paramMap);

	public HandlerResult queryTerminalDeviceTypePage(Map<String, Object> paramMap);

	public void updateTerminalDeviceType(TerminalDeviceType bean);

	public List<Map<String, Object>> listTerminalType();

	public List<TerminalDeviceType> listTerminalUserTypeCount();

	/**
	 * @param id
	 */
	public void deletTerminalDeviceType(Integer id);

	/**
	 * @param id
	 * @return
	 */
	TerminalDeviceType getById(Integer id);
}
