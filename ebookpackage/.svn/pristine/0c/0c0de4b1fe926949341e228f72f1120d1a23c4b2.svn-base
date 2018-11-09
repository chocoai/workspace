package com.whty.ebp.api.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.whty.common.exception.BusinessException;
import com.whty.common.util.CommonFunction;
import com.whty.ebp.api.utils.PrintResult;
import com.whty.ebp.manage.model.Message;
import com.whty.ebp.manage.service.MessageService;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

/**
 * 接口---消息通知相关接口
 */
@Controller
@RequestMapping("/api/message")
public class ApiMessageController {
	@Autowired
	private MessageService messageService;

	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public void list(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String body = CommonFunction.getRequestBody(request.getInputStream());
		
		try {
			// 获取参数
			Map para = this.getQueryListParam(body);
			
			Integer currPage;
			Integer pageSize;
			if (para.get("currPage") == null) {
				currPage = Integer.valueOf(1);
			} else {
				currPage = Integer.parseInt(para.get("currPage").toString());
			}
			if (para.get("pageSize") == null) {
				pageSize = Integer.valueOf(10);
			} else {
				pageSize = Integer.parseInt(para.get("pageSize").toString());
			}
			
			PageContext page = PageContext.getContext();
			// 请自行验证
			if (null == currPage || currPage<1) {
				page.setCurrentPage(1);
				page.setPageSize(pageSize);
				page.setTotalPage(0);
				page.setTotalRows(0);
			} else {
				page.setCurrentPage(currPage);
				page.setPageSize(pageSize);
			}
			page.setPagination(true);
			
			HandlerResult handlerResult = messageService.queryMessageForApi(para);
			List<Message> list = handlerResult.getResultList();
			page.setPagination(false);
			
			List<Map> resultList = new ArrayList<Map>();
			
			Map map = null;
			for(Message message : list){
				map = new HashMap();
				map.put("id", message.getId());
				map.put("theme", message.getTheme());
				map.put("createTime",DateFormatUtils.format(message.getCreateTime(), "yyyy-MM-dd HH:mm:ss") );
				map.put("releaseTime",DateFormatUtils.format(message.getReleaseTime(), "yyyy-MM-dd HH:mm:ss") );
				map.put("status", message.getMessageStateStatus());
				map.put("content",message.getContent());
				map.put("sendOrgName", message.getSendOrgName());
				map.put("messageTypeName", message.getMessageTypeName());
				map.put("receiveObjType",message.getReceiveObjType());
				map.put("receiveObjId",message.getReceiveObjId());
				map.put("receiveObjName",message.getReceiveObjName());
				
				resultList.add(map);
			}
			
			PrintResult.getSuccessPageResult(resultList, page.getTotalPage(), response);
		} catch (BusinessException e) {
			PrintResult.getErrorResult(e.getMessage(), response);
			e.printStackTrace();
		} catch (Exception e) {
			PrintResult.getErrorResult("查询失败", response);
			e.printStackTrace();
		}
			

	}


	/**
	 * 获取参数
	 * 
	 * @param req
	 * @return
	 * @throws BusinessException
	 * @throws com.whty.assis.base.exception.BusinessException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Map getQueryListParam(String body) throws BusinessException {
		JSONObject reqJson = null;
		reqJson = JSONObject
				.fromObject((body == null || body.equals("")) ? "{}" : body);
		// 参数检验
		if (!CommonFunction.isNotNull(reqJson)) {
			throw new BusinessException("参数不存在");
		}
		Map para = new HashMap();
		// 必填字段检查
		CommonFunction.checkParam(para);
		if(reqJson.get("userId")!=null && StringUtils.isNotEmpty(reqJson.get("userId").toString())){
			para.put("userId", reqJson.get("userId"));
		}
		if(reqJson.get("productId")!=null && StringUtils.isNotEmpty(reqJson.get("productId").toString())){
			para.put("productId", reqJson.get("productId"));
		}
		if(reqJson.get("status")!=null && StringUtils.isNotEmpty(reqJson.get("status").toString())){
			para.put("status", reqJson.get("status"));
		}else{
			para.put("status", '1');
		}
		if(reqJson.get("theme")!=null && StringUtils.isNotEmpty(reqJson.get("theme").toString())){
			para.put("theme", reqJson.get("theme"));
		}
		if(reqJson.get("releaseTime")!=null && StringUtils.isNotEmpty(reqJson.get("releaseTime").toString())){
			para.put("releaseTime", reqJson.get("releaseTime"));
		}
		if(reqJson.get("currPage")!=null){
			para.put("currPage", reqJson.get("currPage"));
		}
		if(reqJson.get("pageSize")!=null){
			para.put("pageSize", reqJson.get("pageSize"));
		}
		
		return para;
	}
	
	@RequestMapping(value = "/operate", method = RequestMethod.POST)
	@ResponseBody
	public void operate(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String body = CommonFunction.getRequestBody(request.getInputStream());
		
		JSONObject respJson = new JSONObject();
		try {
			// 获取参数
			Map para = this.getOperateParam(body);
			
			messageService.optMessage(para);
			
			PrintResult.getSuccessResult(null, response);
		} catch (BusinessException e) {
			PrintResult.getErrorResult(e.getMessage(), response);
			e.printStackTrace();
		} catch (Exception e) {
			PrintResult.getErrorResult(null, response);
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 获取参数
	 * 
	 * @param req
	 * @return
	 * @throws BusinessException
	 * @throws com.whty.assis.base.exception.BusinessException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Map getOperateParam(String body) throws BusinessException {
		JSONObject reqJson = null;
		reqJson = JSONObject
				.fromObject((body == null || body.equals("")) ? "{}" : body);
		// 参数检验
		if (!CommonFunction.isNotNull(reqJson)) {
			throw new BusinessException("参数不存在");
		}
		Map para = new HashMap();
		para.put("userId", reqJson.get("userId"));
		para.put("productId", reqJson.get("productId"));
		/*
		 * 操作类型：
		 * 1—标记为已读，2—删除，3—全部标记为已读
		 */
		para.put("optType", reqJson.get("optType"));
		
		if(reqJson.get("optType") != null
				&& ((Integer)reqJson.get("optType")==1 || (Integer)reqJson.get("optType")==2)){
			para.put("id", reqJson.get("id"));
		}
		// 必填字段检查
		CommonFunction.checkParam(para);
		return para;
	}

}
