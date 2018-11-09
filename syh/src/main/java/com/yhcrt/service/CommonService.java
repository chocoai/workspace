package com.yhcrt.service;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yhcrt.dao.DaoSupport;
import com.yhcrt.entity.Tree;
import com.yhcrt.entity.Tree2;
import com.yhcrt.entity.project.ClassManager;
import com.yhcrt.entity.project.ProjectManager;
import com.yhcrt.shiro.TokenManager.TokenManager;
import com.yhcrt.utils.Constants;
import com.yhcrt.utils.StringUtils;

/**
 * 系统参数的service
 * @author kejunzhong
 * 2017年5月11日
 * 版权所有：武汉炎黄创新服务有限公司
 */
@Service("CommonService")
public class CommonService {

	@Resource
	private DaoSupport dao;

	/**
	 * 查询项目树形结构
	 * @param getTree
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Tree> getTree() throws Exception {
		List<Tree> trees=new ArrayList<Tree>();
		
		HashMap<String,Integer> map=new HashMap<String,Integer>();
		map.put("ersionNum", TokenManager.getErsionNum());
		
		List<ClassManager> classList= (List<ClassManager>) dao.findForList("ClassManagerMapper.queryClassTree", map);
		Integer classCids[] = new Integer[classList.size()];
		for (int i=0;i<classList.size();i++) {
			ClassManager classManager = classList.get(i);
			classCids[i] = classManager.getCid();
			Tree tree=new Tree();
			tree.setId(classManager.getCid());
			tree.setParentId(classManager.getClassCid());
			tree.setNodeValue(classManager.getClassifyName());
			tree.setIsClass(Constants.Middle);//分类节点
			tree.setIsTeam(Constants.Middle_01);
			tree.setIsSex(Constants.Middle_01);
			trees.add(tree);
		}
		
		List<ProjectManager> projectManagers=(ArrayList<ProjectManager>) dao.findForList("ProjectManagerMapper.queryTree", classCids);
		
		for (ProjectManager projectManager : projectManagers) {
			Tree tree=new Tree();
			tree.setId(projectManager.getCid());
			tree.setParentId(projectManager.getpCid());
			tree.setNodeValue(projectManager.getProjectName());
			tree.setIsClass(Constants.Middle_1);//项目节点
			tree.setIsTeam(projectManager.getIsTeam());//团体赛、个人赛
			tree.setIsSex(projectManager.getGender());//男、女、混合
			trees.add(tree);
		}
		return trees;
	}
	/**
	 * @Title: getAsynTree
	 * @Description: 异步获取分类树结构
	 * @return: List<Tree>
	 */
	public List<Tree> getAsynTree(Map<String, Object> model) {
		List<Tree> trees=new ArrayList<Tree>();
		try {
			List<ClassManager> classList = (List<ClassManager>) dao.findForList("ClassManagerMapper.getAsynTree",model);
			Integer classCids[] = new Integer[classList.size()];
			for (int i=0;i<classList.size();i++) {
				ClassManager classManager = classList.get(i);
				classCids[i] = classManager.getCid();
				Tree2 tree=new Tree2();
				tree.setId(classManager.getCid());
				tree.setParentId(classManager.getClassCid());
				tree.setNodeValue(classManager.getClassifyName());
				tree.setIsClass(Constants.Middle);//分类节点
				tree.setIsTeam(Constants.Middle_01);
				tree.setIsSex(Constants.Middle_01);
				Integer count = (Integer) dao.findForList("ClassManagerMapper.countByPid",model);
				if(count>0){
					tree.setIsParent(true);
				}
				trees.add(tree);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return  trees;
	}
	
	/**
	 * 利用反射获取分类的限制条件
	 * @param getLimit
	 * @param attr ClassManager类中的属性
	 * @throws Exception
	 */
	public String getLimit(Integer classCid,Integer classType,String attr) throws Exception{
		ClassManager bean = (ClassManager)dao.findForObject("ClassManagerMapper.searchObjectByCid", classCid);
		String getMethodName = "get" + attr.substring(0, 1).toUpperCase() + attr.substring(1);
		Class<? extends ClassManager> tCls = bean.getClass();
		Method getMethod = tCls.getMethod(getMethodName, new Class[] {});
		Object value = getMethod.invoke(bean, new Object[] {});

		//当查到限制条件时直接返回
		if((value!=null))
			return value.toString();
		//如果查不到限制条件但分类类型为classType时直接返回
		if(bean.getClassType().equals(classType))
			return  value.toString();
		
		return getLimit(bean.getClassCid(),classType,attr);
	}
	
	/**
	 * 利用反射获取分类的限制条件
	 * @param getLimit
	 * @param  attrs ClassManager类中的属性集合
	 * @throws Exception
	 */
	public Map<String, String> getLimits(Map<String,String> map,Integer classCid,Integer classType,String[] attrs) throws Exception{
		ClassManager bean = (ClassManager)dao.findForObject("ClassManagerMapper.searchObjectByCid", classCid);
		Class<? extends ClassManager> tCls = bean.getClass();
		
		List<String> list = new ArrayList<String>(); 
		
		for (int i = 0; i < attrs.length; i++) {
			String getMethodName = "get" + attrs[i].substring(0, 1).toUpperCase() + attrs[i].substring(1);
			Method getMethod = tCls.getMethod(getMethodName, new Class[] {});
			Object value = getMethod.invoke(bean, new Object[] {});
			
			if(value!=null){
				String v = value.toString();
				if(value instanceof Integer&&!v.equals(0)&&!v.equals("0")){
					map.put(attrs[i],value.toString());
				}else if(value instanceof String&&!StringUtils.isBlank(v)){
					map.put(attrs[i],value.toString());
				}else{
					list.add(attrs[i]);
				}
			}else{
				list.add(attrs[i]);
			}
		}
		//全部查到时返回
		if(list.size()==0)
			return map;
		//查到项目一级时返回
		if(bean.getClassType().equals(classType)){
//			//没有知道的属性全写入空字符串
//			for (String str : list) {
//				map.put(str, "");
//			}
			return map;
		}
		return getLimits(map,bean.getClassCid(),classType,StringUtils.list2array(list));
	}
	/**
	 * 根据分类cid查询所有上级名称以及限制条件
	 * @param classManager
	 * @return
	 * @throws Exception
	 */
//	public HashMap<String,Object> getName(Integer classCid){
//		HashMap<String,Object> map = new HashMap<String,Object>();
//		String str = "";
//		Integer in = classCid;
//		try {
//			for (int i = 0; i < 10; i++) {
//				ClassManager c = new ClassManager();
//				c.setCid(in);
//				ClassManager cmg = (ClassManager)dao.findForObject("ClassManagerMapper.selectByExample", c);
//				in = cmg.getClassCid();
//				if(in != 0){
//					str += cmg.getClassifyName()+",";
//					if(cmg.getAgeStart() != null && cmg.getAgeStart() != "" && cmg.getAgeEnd() != null && cmg.getAgeEnd() != ""){
//						map.put("ageStart", cmg.getAgeStart());
//						map.put("ageEnd", cmg.getAgeEnd());
//					}
//					if(cmg.getPersonSinglenum()!= null){
//						if(cmg.getPersonSinglenum() != 0){
//							map.put("personSinglenum", cmg.getPersonSinglenum());
//						}
//					}
//					if(cmg.getPersonTeamnum() !=null){
//						if(cmg.getPersonTeamnum() !=0){
//							map.put("personTeamnum", cmg.getPersonTeamnum());
//						}
//					}
//					if(cmg.getPersonTotnum()!=null){
//						if(cmg.getPersonTotnum()!=0){
//							map.put("personTotnum", cmg.getPersonTotnum());
//						}
//					}
//					if(cmg.getUnitSinglenum() != null){
//						if(cmg.getUnitSinglenum() !=0){
//							map.put("unitSinglenum", cmg.getUnitSinglenum());
//						}
//					}
//					if(cmg.getUnitTotnum() != null){
//						if(cmg.getUnitTotnum() !=0){
//							map.put("unitTotnum", cmg.getUnitTotnum());
//						}
//					}
//					if(cmg.getUnitTeamnum() != null){
//						if(cmg.getUnitTeamnum() !=0){
//							map.put("unitTeamnum", cmg.getUnitTeamnum());
//						}
//					}
//					continue;
//				}else{
//					break;
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		String[] strings = str.split(",");
//	    for (int start = 0, end = strings.length - 1; start < end; start++, end--) {
//	        String temp = strings[end];
//	        strings[end] = strings[start];
//	        strings[start] = temp;
//	    }
//	    String s ="";
//	    for (int i = 0; i < strings.length; i++) {
//	    	if (i==0) {
//	    		s  += strings[i];
//			}else {
//				s  +="|"+ strings[i];
//			}
//		}
//	    map.put("className", s);
//	    return map;
//	}
	
	
	/**
	 * 
	 * @Title: getFullName
	 * @Description: 根据某个分类树节点的ID拼接所有上级分类名（直到类型为classType结束）
	 * @return: ClassManager
	 */
	public String  getFullName(Integer classCid,Integer classType,String s) throws Exception{
		
		ClassManager bean = (ClassManager)dao.findForObject("ClassManagerMapper.searchObjectByCid", classCid);
		if(bean.getClassType().equals(classType)){//类型为classType结束
			return bean.getClassifyName() + s;
		}else{
			s =  "|" + bean.getClassifyName()+  s;
			return getFullName(bean.getClassCid(),classType, s);
		}
	}
	
	/**
	 * 
	 * @Title: getByClassCid
	 * @Description: 根据分类id查询项目大项（足球、篮球）的分类对象
	 * @return: ClassManager
	 */
	public ClassManager getByClassCid(Integer classCid,Integer classType) throws Exception{
		ClassManager bean = (ClassManager)dao.findForObject("ClassManagerMapper.searchObjectByCid", classCid);
		if(bean.getClassType().equals(classType)){//项目大项或等于当前届时停止
			return bean;
		}else{
			return getByClassCid(bean.getClassCid(),classType);
		}
	}


}
