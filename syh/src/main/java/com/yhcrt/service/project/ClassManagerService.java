package com.yhcrt.service.project;

import static com.yhcrt.utils.Constants.CLASS_MANAGER;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yhcrt.controller.BaseController;
import com.yhcrt.dao.DaoSupport;
import com.yhcrt.entity.project.ClassManager;
import com.yhcrt.service.CommonService;
import com.yhcrt.shiro.TokenManager.TokenManager;
import com.yhcrt.utils.Constants;
import com.yhcrt.utils.DateUtil;
import com.yhcrt.utils.GetPinyin;
import com.yhcrt.utils.GetSequence;


/**
 * 分类管理的service
 * @author kejunzhong
 * 2017年5月11日
 * 版权所有：武汉炎黄创新服务有限公司
 */
@Service("classManagerService")
public class ClassManagerService {
	@Resource
	private DaoSupport dao;
	@Resource
	private CommonService commonService;
	/**
	 * 查询项目分类列表
	 * @param classManager
	 * @return
	 * @throws Exception
	 */
	public List<ClassManager> queryList(Integer classCid) throws Exception {
		HashMap<String,Object> map = new HashMap<String,Object>();
		ClassManager classManager = new ClassManager();
		classManager.setClassCid(classCid);
		classManager.setErsionNum(TokenManager.getErsionNum());
		List<ClassManager> list = (List<ClassManager>) dao.findForList("ClassManagerMapper.selectByExample", classManager);
		return list;
	}
	
	/**
	 * 添加项目分类
	 * @param jsonString
	 * @return
	 * @throws Exception
	 */
	public String insertClassManager(ClassManager classManager) {
		String result="";
			try {
				classManager.setCid(GetSequence.getSequenceByName(dao, CLASS_MANAGER));
				classManager.setAbbreviation(GetPinyin.getPinYinHeadChar(classManager.getClassifyName()));
				classManager.setCreaRen(BaseController.getUserCode());
				classManager.setCreaTime(DateUtil.getTime());
				classManager.setErsionNum(TokenManager.getErsionNum());
				/*
				 * 当classCid为空或者null时则默认为根节点，取值为0
				 * 当简称为空串或者null时，根据项目名称首字母去取值
				 */
				if(classManager.getClassCid() == null || "".equals(classManager.getClassCid())){
					classManager.setClassCid(0);
				}
				dao.save("ClassManagerMapper.insert", classManager);
				result="success";
			} catch (Exception e) {
				e.printStackTrace();
				result="failed";
			}
		return result;
	}
	 
	/**
	 * 通过CID获取项目分类信息
	 * @return
	 * @throws Exception 
	 */
	public ClassManager searchObjectByCid(Integer cid) {
		try {
			ClassManager classManager = (ClassManager)dao.findForObject("ClassManagerMapper.searchObjectByCid", cid);
			return classManager;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 修改项目分类
	 * @param jsonString
	 * @return
	 * @throws Exception
	 */
	public String updateClassManager(ClassManager classManager) {
		String result="";
			try {
				classManager.setAbbreviation(GetPinyin.getPinYinHeadChar(classManager.getClassifyName()));
				classManager.setErsionNum(TokenManager.getErsionNum());
				dao.save("ClassManagerMapper.update", classManager);
				result="success";
			} catch (Exception e) {
				e.printStackTrace();
				result="failed";
			}
		return result;
	}
	
	/**
	 * 删除项目分类
	 * @param cid
	 * @return 
	 * @throws Exception
	 */
	public HashMap<String,Object> deleteClassManager(Integer cid) throws Exception {
		HashMap<String,Object> map=new HashMap<String,Object>();
		Integer countClass = (Integer) dao.findForObject("ClassManagerMapper.countByPid", cid);
		if(countClass>0){
			map.put("status", "noclass");
		}else{
			Integer countProject= (Integer) dao.findForObject("ProjectManagerMapper.countByClassCid", cid);
			if(countProject>0){
				map.put("status", "noproject");
			}else{
				dao.delete("ClassManagerMapper.delete", cid);
				map.put("status", "success");
			}
		}
		return map;
	}
	
	/**
	 * 构造树结构
	 * @param cid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ClassManager> queryClassToTree() {
		List<ClassManager> classList = new ArrayList<ClassManager>();
		try {
			HashMap<String,Integer> map=new HashMap<String,Integer>();
			map.put("ersionNum", TokenManager.getErsionNum());
			classList= (List<ClassManager>) dao.findForList("ClassManagerMapper.queryClassTree", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return classList;
	}

	/**
	 * 新增时根据上级CID以及分类名称去查询是否重复输入
	 * @param classifyName,classCid
	 * @return 
	 * @throws Exception
	 */
	public String getObjByNameAndClassCid(String classifyName,String classCid) {
		String result="";
		if (classifyName==null||classifyName.equals("")) {
			result="nodata";
		}else {
			try {
				ClassManager classManager = new ClassManager();
				classManager.setClassifyName(classifyName);
				if(classCid != null && !classCid.equals("")){
					classManager.setClassCid(Integer.valueOf(classCid));
				}else{
					classManager.setClassCid(0);
				}
				Integer count = (Integer) dao.findForObject("ClassManagerMapper.countByNameAndClassCid", classManager);
				if(count>0){
					result = "failed";
				}else{
					result="success";
				}
			} catch (Exception e) {
				e.printStackTrace();
				result="failed";
			}
		}
		return result;
	}
	/**
	 * 选择项目时判断是否还有下级分类
	 * @param classCidNode
	 * @return 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> queryIsNode(Integer classCidNode) {
		Map<String, Object> result = new LinkedHashMap<String, Object>();
		if (classCidNode==null||classCidNode.equals("")) {
			result.put("states", "nodata");
		}else {
			try {
				ClassManager classManager = new ClassManager();
				classManager.setClassCid(classCidNode);
				List<ClassManager> cm = (List<ClassManager>) dao.findForList("ClassManagerMapper.selectByExample", classManager);
				if(cm.size()>0){
					result.put("states", "failed");
				}else{
					result.put("states", "success");
					String className="";
					className = commonService.getFullName(classCidNode, Constants.Middle_2,className);
					result.put("className",className);
				}
			} catch (Exception e) {
				e.printStackTrace();
				result.put("states", "failed");
			}
		}
		return result;
	}
}
