package com.whty.assis.basicdata.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whty.assis.basicdata.dao.SchoolDao;
import com.whty.assis.basicdata.dao.StudentDao;
import com.whty.assis.basicdata.dao.TeacherDao;
import com.whty.assis.basicdata.model.School;
import com.whty.assis.basicdata.model.SchoolAreaTreeNode;
import com.whty.assis.basicdata.model.Student;
import com.whty.assis.basicdata.model.Teacher;
import com.whty.assis.basicdata.service.SchoolService;
import com.whty.assis.basicdata.service.StudentService;
import com.whty.assis.basicdata.service.TeacherService;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentDao studentDao;

	@Override
	public void saveStudent(Student student) {
		studentDao.saveStudent(student);
	}

	@Override
	public List<Student> listByCondition(Map<String, Object> paramMap) {
		return studentDao.listByCondition(paramMap);
	}

	@Override
	public HandlerResult queryStudentPage(Map<String, Object> paramMap, PageContext page) {
		HandlerResult rs = new HandlerResult();
		rs.setResultList(studentDao.listByCondition(paramMap));
		return rs;
	}

	@Override
	public void updateStudent(Student student) {
		studentDao.updateStudent(student);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.assis.basicdata.service.SchoolService#listByConditionByAreaCode(
	 * java.util.Map)
	 */
	@Override
	public List<Map<String, Object>> listByConditionByAreaCode(Map<String, Object> paraMap) {
		return studentDao.listByConditionByAreaCode(paraMap);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.assis.basicdata.service.SchoolService#listSchooleMap(java.util.
	 * Map)
	 */
	@Override
	public List<Map<String, Object>> listStudentMap(Map<String, Object> param) {
		return studentDao.listStudentMap(param);
	}

	private SchoolAreaTreeNode getProvince(School school) {
		SchoolAreaTreeNode bean = null;
		if (school.getProvinceCode() != null) {
			bean = new SchoolAreaTreeNode();
			bean.setId(school.getProvinceCode());
			bean.setName(school.getProvinceName());
			
			if(school.getCityCode()!=null){
				SchoolAreaTreeNode subNode = new SchoolAreaTreeNode();
				subNode.setId(school.getCityCode());
				subNode.setName(school.getCityName());
				
				List<SchoolAreaTreeNode> subList = new ArrayList<SchoolAreaTreeNode>();
				subList.add(subNode);
				bean.setSubNodeList(subList);
			}
		}
		return bean;
	}

	private SchoolAreaTreeNode getCity(School school) {
		SchoolAreaTreeNode bean = null;
		if (school.getCityCode() != null) {
			bean = new SchoolAreaTreeNode();
			bean.setId(school.getCityCode());
			bean.setName(school.getCityName());
			
			if(school.getAreaCode()!=null){
				SchoolAreaTreeNode subNode = new SchoolAreaTreeNode();
				subNode.setId(school.getCityCode());
				subNode.setName(school.getCityName());
				
				List<SchoolAreaTreeNode> subList = new ArrayList<SchoolAreaTreeNode>();
				subList.add(subNode);
				bean.setSubNodeList(subList);
			}
		}
		return bean;
	}

	private SchoolAreaTreeNode getArea(School school) {
		SchoolAreaTreeNode bean = null;
		if (school.getCityCode() != null) {
			bean = new SchoolAreaTreeNode();
			bean.setId(school.getAreaCode());
			bean.setName(school.getAreaName());
			
			SchoolAreaTreeNode subNode = new SchoolAreaTreeNode();
			subNode.setId(Integer.toString(school.getId()));
			subNode.setName(school.getName());
			
			List<SchoolAreaTreeNode> subList = new ArrayList<SchoolAreaTreeNode>();
			subList.add(subNode);
			bean.setSubNodeList(subList);
		}
		return bean;
	}

	private SchoolAreaTreeNode getSchoolParam(School school) {
		SchoolAreaTreeNode bean = new SchoolAreaTreeNode();
		bean.setId(Integer.toString(school.getId()));
		bean.setName(school.getName());
		return bean;
	}

	
	
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see
//	 * com.whty.assis.basicdata.service.SchoolService#listSchoolAreaTree(java.
//	 * util.Map)
//	 */
//	@Override
//	public List<SchoolAreaTreeNode> listSchoolAreaTree(Map<String, Object> paraMap) {
//
//		List<SchoolAreaTreeNode> provinceTree = new ArrayList<SchoolAreaTreeNode>();
//
//		List<SchoolAreaTreeNode> cityTree = new ArrayList<SchoolAreaTreeNode>();
//		
//		List<SchoolAreaTreeNode> areaTree = new ArrayList<SchoolAreaTreeNode>();
//		
//		List<SchoolAreaTreeNode> schoolTree = new ArrayList<SchoolAreaTreeNode>();
//		
//		String schoolId = null;
//
//		if (paraMap.get("schoolId") != null) {
//			schoolId = paraMap.get("schoolId").toString();
//		}
//
//		School school = schoolDao.loadById(Integer.valueOf(schoolId));
//		
//		String proviceCode = school.getProvinceCode();
//		String cityCode = school.getCityCode();
//		String areaCode = school.getAreaCode();
//
//		
//		provinceTree.add(getProvince(school));
//		cityTree.add(getCity(school));
//		areaTree.add(getArea(school));
//		schoolTree.add(getSchoolParam(school));
//		
//		
//		
//		return provinceTree;
//	}

}
