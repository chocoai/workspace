package com.smart.service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.smart.model.Project;
import com.smart.model.ProjectProcessState;
import com.smart.model.Requisition;
import com.smart.model.Step1;
import com.smart.model.Step10;
import com.smart.model.Step11;
import com.smart.model.Step12;
import com.smart.model.Step13;
import com.smart.model.Step14;
import com.smart.model.Step15;
import com.smart.model.Step2;
import com.smart.model.Step3;
import com.smart.model.Step4;
import com.smart.model.Step5;
import com.smart.model.Step5Logo;
import com.smart.model.Step6;
import com.smart.model.Step7;
import com.smart.model.Step8;
import com.smart.model.Step9;
import com.smart.util.Page;
import com.smart.util.StringUtil;
import com.smart.dao.ProjectDao;
import com.smart.dao.ProjectProcessStateDao;
import com.smart.dao.Step10Dao;
import com.smart.dao.Step11Dao;
import com.smart.dao.Step12Dao;
import com.smart.dao.Step13Dao;
import com.smart.dao.Step14Dao;
import com.smart.dao.Step15Dao;
import com.smart.dao.Step1Dao;
import com.smart.dao.Step2Dao;
import com.smart.dao.Step3Dao;
import com.smart.dao.Step4Dao;
import com.smart.dao.Step5Dao;
import com.smart.dao.Step5LogoDao;
import com.smart.dao.Step6Dao;
import com.smart.dao.Step7Dao;
import com.smart.dao.Step8Dao;
import com.smart.dao.Step9Dao;

/**
 * ProjectService. @author Auto Tools by 充满智慧的威哥
 */
@Service
public class ProjectService {

	@Autowired
	private ProjectDao projectDao;

	@Autowired
	private Step1Dao step1Dao;

	@Autowired
	private Step2Dao step2Dao;

	@Autowired
	private Step3Dao step3Dao;

	@Autowired
	private Step4Dao step4Dao;

	@Autowired
	private Step5Dao step5Dao;

	@Autowired
	private Step5LogoDao step5LogoDao;

	@Autowired
	private Step6Dao step6Dao;

	@Autowired
	private Step7Dao step7Dao;

	@Autowired
	private Step8Dao step8Dao;

	@Autowired
	private Step9Dao step9Dao;

	@Autowired
	private Step10Dao step10Dao;

	@Autowired
	private Step11Dao step11Dao;

	@Autowired
	private Step12Dao step12Dao;
	
	@Autowired
	private RequisitionService requisitionService;

	@Autowired
	private Step13Dao step13Dao;

	@Autowired
	private Step14Dao step14Dao;

	@Autowired
	private Step15Dao step15Dao;

	@Autowired
	private ProjectProcessStateDao projectProcessStateDao;

	/**
	 * 查询当前用户处理过得项目
	 * 
	 * @paramer:@param pageNo
	 * @paramer:@param pageSize
	 * @paramer:@param no
	 * @paramer:@param name
	 * @paramer:@param userId
	 * @paramer:@return
	 * @return: Page<Project>
	 */
	public Page<Project> getPageHistory(Integer pageNo, Integer pageSize,
			String no, String name, Integer userId) {
		Page<Project> page = projectDao.getPageHistory(pageNo, pageSize, no,
				name, userId);
		/*
		 * for (int i = 0; i < page.getList().size(); i++) { ProjectProcessState
		 * pps =
		 * projectProcessStateDao.getOneByProjectIdAndBusinesstype(page.getList(
		 * ).get(i).getId(), 1);
		 * page.getList().get(i).setCurrentStep(pps.getCurrentStep()); }
		 */
		return page;
	}
	
	public String getProjectLast() {
		return projectDao.getProjectLast();
	}

	/**
	 * 分页查询
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param search
	 * @param projectTypeId
	 * @param deptId
	 * @return
	 */
	public Page<Project> getPage(int pageNo, int pageSize, String no,
			String name, Integer projectTypeId) {
		StringBuilder hql = new StringBuilder(
				"from Project o where o.status<>0 ");
		List<Object> paramList = new ArrayList<Object>();
		if (!StringUtil.isBlank(no)) {
			hql.append(" and o.no like ? ");
			paramList.add("%" + no + "%");
		}
		if (!StringUtil.isBlank(name)) {
			hql.append("and o.name like ? ");
			paramList.add("%" + name + "%");
		}
		if (projectTypeId != null) {
			hql.append("and o.projectType.id = ? ");
			paramList.add(projectTypeId);
		}
		hql.append("order by o.id desc");
		return projectDao.getPage(pageNo, pageSize, hql.toString(), paramList);
	}

	public Page<Project> getDonePage(int pageNo, int pageSize, String no,
			String name, int userId) {
		StringBuilder hql = new StringBuilder();
		hql.append("from Project o where o.status <> 0 ");
		List<Object> paramList = new ArrayList<Object>();
		if (!StringUtil.isBlank(no)) {
			hql.append(" and o.no like ? ");
			paramList.add("%" + no + "%");
		}
		if (!StringUtil.isBlank(name)) {
			hql.append("and o.name like ? ");
			paramList.add("%" + name + "%");
		}
		hql.append(" and o.id in (");
		hql.append(
				"select b.project.id from ProjectProcessHistory b where b.operateUser.id = ? )");
		paramList.add(userId);
		hql.append("order by o.id desc");
		return projectDao.getPage(pageNo, pageSize, hql.toString(), paramList);
	}

	
	public Page<Project> getAllPage(int pageNo, int pageSize, String no,
			String name, int userId) {
		StringBuilder hql = new StringBuilder();
		hql.append("from Project o where o.status <> 0 ");
		List<Object> paramList = new ArrayList<Object>();
		if (!StringUtil.isBlank(no)) {
			hql.append(" and o.no like ? ");
			paramList.add("%" + no + "%");
		}
		if (!StringUtil.isBlank(name)) {
			hql.append("and o.name like ? ");
			paramList.add("%" + name + "%");
		}
		hql.append(" and o.id in (");
		hql.append("select b.project.id from ProjectProcessHistory b ");
		hql.append("where b.operateUser.id = ? ");
		hql.append("or find_in_set(?,b.nextHandlers) > 0 ");
		paramList.add(userId);
		paramList.add(userId);
		hql.append(") order by o.id desc");
		return projectDao.getPage(pageNo, pageSize, hql.toString(), paramList);
	}
	
	/**
	 * 查询项目管理环节的所有项目
	 * @param pageNo
	 * @param pageSize
	 * @param no
	 * @param name
	 * @param userId
	 * @return
	 */
	public Page<Project> getAllProject(int pageNo, int pageSize, String no,
			String name, int userId) {
		StringBuilder hql = new StringBuilder();
		hql.append("from Project o where o.status <> 0 ");
		List<Object> paramList = new ArrayList<Object>();
		if (!StringUtil.isBlank(no)) {
			hql.append(" and o.no like ? ");
			paramList.add("%" + no + "%");
		}
		if (!StringUtil.isBlank(name)) {
			hql.append("and o.name like ? ");
			paramList.add("%" + name + "%");
		}
		hql.append(" and o.id in (");
		hql.append("select b.project.id from ProjectProcessHistory b ");
		hql.append("where (b.operateUser.id = ? and b.operateStep.businessType = 1 ) ");
		hql.append("or (find_in_set(?,b.nextHandlers) > 0 and b.nextStep.businessType = 1 ) ");
		paramList.add(userId);
		paramList.add(userId);
		hql.append(") order by o.id desc");
		return projectDao.getPage(pageNo, pageSize, hql.toString(), paramList);
	}

	public Page<Project> getPageByNoAndName(int pageNo, int pageSize, String no,
			String name) {
		StringBuilder hql = new StringBuilder(
				"from Project o where o.status <> 0 ");
		List<Object> paramList = new ArrayList<Object>();
		if (!StringUtil.isBlank(no)) {
			hql.append(" and o.no like ? ");
			paramList.add("%" + no + "%");
		}
		if (!StringUtil.isBlank(name)) {
			hql.append("and o.name like ? ");
			paramList.add("%" + name + "%");
		}
		hql.append("order by o.id desc");
		return projectDao.getPage(pageNo, pageSize, hql.toString(), paramList);
	}

	public Page<Project> getPage(int pageNo, int pageSize, String no,
			String name) {
		StringBuilder hql = new StringBuilder(
				"from Project o where o.status <> 0 ");
		List<Object> paramList = new ArrayList<Object>();
		if (!StringUtil.isBlank(no)) {
			hql.append(" and o.no like ? ");
			paramList.add("%" + no + "%");
		}
		if (!StringUtil.isBlank(name)) {
			hql.append("and o.name like ? ");
			paramList.add("%" + name + "%");
		}
		hql.append("order by o.id desc");
		return projectDao.getPage(pageNo, pageSize, hql.toString(), paramList);
	}

	public Page<Project> getProjectPageAll(int pageNo, int pageSize, String no,
			String name, Integer managerid, Integer type) {
		StringBuilder hql = new StringBuilder(
				"from Project o where o.status<>0  ");
		List<Object> paramList = new ArrayList<Object>();
		if (!StringUtil.isBlank(no)) {
			hql.append(" and o.no like ? ");
			paramList.add("%" + no + "%");
		}
		if (!StringUtil.isBlank(name)) {
			hql.append(" and o.name like ? ");
			paramList.add("%" + name + "%");
		}
		hql.append(" order by o.id desc");
		Page<Project> pageBean = projectDao.getPage(pageNo, pageSize,
				hql.toString(), paramList);

		return pageBean;
	}

	public String getGanttSource(Project project) {
		Integer projectid = project.getId();
		ProjectProcessState pps = projectProcessStateDao
				.getOneByProjectIdAndBusinesstype(projectid,
						Integer.parseInt(ProjectProcessState.TYPE_PM));

		Integer currentStep = Integer
				.parseInt(pps.getCurrentStep().getStepCode());
		String stepTemplete = project.getStepTemplete().getStepItems();
		String[] steparr = stepTemplete.split(",");

		Date stepTime = null;
		Date stepTime2 = null;
		String stepName = "";
		Integer stepId = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		JSONArray sourcedata = new JSONArray();
		for (int i = 0; i <= steparr.length; i++) {
			String ganttColor = "";
			String step = "";
			if (i < steparr.length) {
				step = steparr[i];
			} else {
				step = "16";
			}
			String name = "";
			String desc = "";
			String from = null;
			String to = null;
			if ("1".equals(step)) {
				name = "咨询任务书";
				Step1 step1 = step1Dao.getUnique(
						"from Step1 o where o.project.id=" + projectid);
				if (currentStep > 1 && step1.getConfirmTime() != null) {
					stepTime = step1.getCtime();
					stepTime2 = step1.getConfirmTime();
				} else {

					stepTime = step1.getCtime();
					stepTime2 = step1.getCtime();
				}
				stepName = name;
				continue;
			} else if ("2".equals(step)) {
				name = "接收资料登记";
				Step2 step2 = step2Dao.getUnique(
						"from Step2 o where o.project.id=" + projectid);
				if (currentStep >= 2) {
					long intervalMilli = stepTime2.getTime()
							- stepTime.getTime(); // 相差的天数
					desc = (int) (intervalMilli / (24 * 60 * 60 * 1000) + 1)
							+ "天";
					from = sdf.format(stepTime);
					to = sdf.format(stepTime2);
					stepTime = stepTime2;
					if (currentStep > 2) {
						stepTime2 = step2.getConfirmTime();
					}
				} else if (currentStep == 1) {
					desc = "正在进行";
					ganttColor = "red";
					from = sdf.format(stepTime2);
					to = sdf.format(stepTime2);
				} else if (currentStep < 1) {
					desc = "未进行";
					ganttColor = "white";
					from = sdf.format(stepTime2);
					to = sdf.format(stepTime2);
				}
			} else if ("3".equals(step)) {
				name = "编制咨询方案";
				Step3 step3 = step3Dao.getUnique(
						"from Step3 o where o.project.id=" + projectid);
				if (currentStep >= 3) {
					long intervalMilli = stepTime2.getTime()
							- stepTime.getTime(); // 相差的天数
					desc = (int) (intervalMilli / (24 * 60 * 60 * 1000) + 1)
							+ "天";
					from = sdf.format(stepTime);
					to = sdf.format(stepTime2);
					stepTime = stepTime2;
					if (currentStep > 3) {
						stepTime2 = step3.getConfirmTime();
					}
				} else if (currentStep == 2) {
					desc = "正在进行";
					ganttColor = "red";
					from = sdf.format(stepTime2);
					to = sdf.format(stepTime2);

				} else if (currentStep < 2) {
					desc = "未进行";
					from = sdf.format(stepTime2);
					ganttColor = "white";
					to = sdf.format(stepTime2);

				}
			} else if ("4".equals(step)) {
				name = "整理资料清单";
				Step4 step4 = step4Dao.getUnique(
						"from Step4 o where o.project.id=" + projectid);
				if (currentStep >= 4) {
					long intervalMilli = stepTime2.getTime()
							- stepTime.getTime(); // 相差的天数
					desc = (int) (intervalMilli / (24 * 60 * 60 * 1000) + 1)
							+ "天";
					from = sdf.format(stepTime);
					to = sdf.format(stepTime2);
					stepTime = stepTime2;
					if (currentStep > 4) {
						stepTime2 = step4.getConfirmTime();
					}
				} else if (currentStep == 3) {
					desc = "正在进行";
					ganttColor = "red";
					from = sdf.format(stepTime2);
					to = sdf.format(stepTime2);

				} else if (currentStep < 3) {
					desc = "未进行";
					from = sdf.format(stepTime2);
					ganttColor = "white";
					to = sdf.format(stepTime2);

				}
			} else if ("5".equals(step)) {
				name = "勘查记录";
				Step5 step5 = step5Dao.getUnique(
						"from Step5 o where o.project.id=" + projectid);

				if (currentStep >= 5) {
					long intervalMilli = stepTime2.getTime()
							- stepTime.getTime(); // 相差的天数
					desc = (int) (intervalMilli / (24 * 60 * 60 * 1000) + 1)
							+ "天";
					from = sdf.format(stepTime);
					to = sdf.format(stepTime2);
					stepTime = stepTime2;
					if (currentStep > 5) {
						stepId = step5.getId();
						stepTime2 = step5.getConfirmTime();
					}
				} else if (currentStep == 4) {
					desc = "正在进行";
					ganttColor = "red";
					from = sdf.format(stepTime2);
					to = sdf.format(stepTime2);

				} else if (currentStep < 4) {
					desc = "未进行";
					from = sdf.format(stepTime2);
					ganttColor = "white";
					to = sdf.format(stepTime2);

				}
			} else if ("6".equals(step)) {
				name = "底稿编制";
				Step6 step6 = step6Dao.getUnique(
						"from Step6 o where o.project.id=" + projectid);

				Step5Logo step5Logo = step5LogoDao
						.getUnique("from Step5Logo o where o.step5.id=" + stepId
								+ " order by o.ctime");
				if (currentStep >= 6) {
					long intervalMilli = stepTime2.getTime()
							- stepTime.getTime(); // 相差的天数
					desc = (int) (intervalMilli / (24 * 60 * 60 * 1000) + 1)
							+ "天";
					from = sdf.format(stepTime);
					to = sdf.format(stepTime2);
					if (step5Logo != null) {
						stepTime = step5Logo.getCtime();
					}
					if (currentStep > 6) {
						stepTime2 = step6.getConfirmTime();
					}
				} else if (currentStep == 5) {
					desc = "正在进行";
					ganttColor = "red";
					from = sdf.format(stepTime2);
					to = sdf.format(stepTime2);

				} else if (currentStep < 5) {
					desc = "未进行";
					from = sdf.format(stepTime2);
					ganttColor = "white";
					to = sdf.format(stepTime2);

				}
			} else if ("7".equals(step)) {
				name = "校对";
				Step7 step7 = step7Dao.getUnique(
						"from Step7 o where o.project.id=" + projectid);
				if (currentStep >= 7) {
					long intervalMilli = stepTime2.getTime()
							- stepTime.getTime(); // 相差的天数
					desc = (int) (intervalMilli / (24 * 60 * 60 * 1000) + 1)
							+ "天";
					from = sdf.format(stepTime);
					to = sdf.format(stepTime2);
					stepTime = stepTime2;
					if (currentStep > 7) {
						stepTime2 = step7.getConfirmTime();
					}
				} else if (currentStep == 6) {
					desc = "正在进行";
					ganttColor = "red";
					from = sdf.format(stepTime2);
					to = sdf.format(stepTime2);

				} else if (currentStep < 6) {
					desc = "未进行";
					from = sdf.format(stepTime2);
					ganttColor = "white";
					to = sdf.format(stepTime2);

				}
			} else if ("8".equals(step)) {
				name = "审核";
				Step8 step8 = step8Dao.getUnique(
						"from Step8 o where o.project.id=" + projectid);
				if (currentStep >= 8) {
					long intervalMilli = stepTime2.getTime()
							- stepTime.getTime(); // 相差的天数
					desc = (int) (intervalMilli / (24 * 60 * 60 * 1000) + 1)
							+ "天";
					from = sdf.format(stepTime);
					to = sdf.format(stepTime2);
					stepTime = stepTime2;
					if (currentStep > 8) {
						stepTime2 = step8.getConfirmTime();
					}
				} else if (currentStep == 7) {
					desc = "正在进行";
					ganttColor = "red";
					from = sdf.format(stepTime2);
					to = sdf.format(stepTime2);

				} else if (currentStep < 7) {
					desc = "未进行";
					from = sdf.format(stepTime2);
					ganttColor = "white";
					to = sdf.format(stepTime2);

				}
			} else if ("9".equals(step)) {
				name = "审定";
				Step9 step9 = step9Dao.getUnique(
						"from Step9 o where o.project.id=" + projectid);
				if (currentStep >= 9) {
					long intervalMilli = stepTime2.getTime()
							- stepTime.getTime(); // 相差的天数
					desc = (int) (intervalMilli / (24 * 60 * 60 * 1000) + 1)
							+ "天";
					from = sdf.format(stepTime);
					to = sdf.format(stepTime2);
					stepTime = stepTime2;
					if (currentStep > 9) {
						stepTime2 = step9.getConfirmTime();
					}
				} else if (currentStep == 8) {
					desc = "正在进行";
					ganttColor = "red";
					from = sdf.format(stepTime2);
					to = sdf.format(stepTime2);

				} else if (currentStep < 8) {
					desc = "未进行";
					from = sdf.format(stepTime2);
					ganttColor = "white";
					to = sdf.format(stepTime2);

				}
			} else if ("10".equals(step)) {
				name = "征求意见";
				Step10 step10 = step10Dao.getUnique(
						"from Step10 o where o.project.id=" + projectid);
				if (currentStep >= 10) {
					long intervalMilli = stepTime2.getTime()
							- stepTime.getTime(); // 相差的天数
					desc = (int) (intervalMilli / (24 * 60 * 60 * 1000) + 1)
							+ "天";
					from = sdf.format(stepTime);
					to = sdf.format(stepTime2);
					stepTime = stepTime2;
					if (currentStep > 10) {
						stepTime2 = step10.getConfirmTime();
					}
				} else if (currentStep == 9) {
					desc = "正在进行";
					ganttColor = "red";
					from = sdf.format(stepTime2);
					to = sdf.format(stepTime2);

				} else if (currentStep < 9) {
					desc = "未进行";
					from = sdf.format(stepTime2);
					ganttColor = "white";
					to = sdf.format(stepTime2);

				}
			} else if ("11".equals(step)) {
				name = "成果文件编制";
				Step11 step11 = step11Dao.getUnique(
						"from Step11 o where o.project.id=" + projectid);
				if (currentStep >= 11) {
					long intervalMilli = stepTime2.getTime()
							- stepTime.getTime(); // 相差的天数
					desc = (int) (intervalMilli / (24 * 60 * 60 * 1000) + 1)
							+ "天";
					from = sdf.format(stepTime);
					to = sdf.format(stepTime2);
					stepTime = stepTime2;
					if (currentStep > 11) {
						stepTime2 = step11.getConfirmTime();
					}
				} else if (currentStep == 10) {
					desc = "正在进行";
					ganttColor = "red";
					from = sdf.format(stepTime2);
					to = sdf.format(stepTime2);

				} else if (currentStep < 10) {
					desc = "未进行";
					from = sdf.format(stepTime2);
					ganttColor = "white";
					to = sdf.format(stepTime2);

				}
			} else if ("12".equals(step)) {
				name = "成果文件签订并签发";
				Step12 step12 = step12Dao.getUnique(
						"from Step12 o where o.project.id=" + projectid);
				if (currentStep >= 12) {
					long intervalMilli = stepTime2.getTime()
							- stepTime.getTime(); // 相差的天数
					desc = (int) (intervalMilli / (24 * 60 * 60 * 1000) + 1)
							+ "天";
					from = sdf.format(stepTime);
					to = sdf.format(stepTime2);
					stepTime = stepTime2;
					if (currentStep > 12) {
						stepTime2 = step12.getConfirmTime();
					}
				} else if (currentStep == 11) {
					desc = "正在进行";
					ganttColor = "red";
					from = sdf.format(stepTime2);
					to = sdf.format(stepTime2);

				} else if (currentStep < 11) {
					desc = "未进行";
					from = sdf.format(stepTime2);
					ganttColor = "white";
					to = sdf.format(stepTime2);

				}
			} else if ("13".equals(step)) {
				name = "回访记录";
				Step13 step13 = step13Dao.getUnique(
						"from Step13 o where o.project.id=" + projectid);
				if (currentStep >= 13) {
					long intervalMilli = stepTime2.getTime()
							- stepTime.getTime(); // 相差的天数
					desc = (int) (intervalMilli / (24 * 60 * 60 * 1000) + 1)
							+ "天";
					from = sdf.format(stepTime);
					to = sdf.format(stepTime2);
					stepTime = stepTime2;
					if (currentStep > 13) {
						stepTime2 = step13.getConfirmTime();
					}
				} else if (currentStep == 12) {
					desc = "正在进行";
					ganttColor = "red";
					from = sdf.format(stepTime2);
					to = sdf.format(stepTime2);

				} else if (currentStep < 12) {
					desc = "未进行";
					from = sdf.format(stepTime2);
					ganttColor = "white";
					to = sdf.format(stepTime2);

				}
			} else if ("14".equals(step)) {
				name = "项目总结";
				Step14 step14 = step14Dao.getUnique(
						"from Step14 o where o.project.id=" + projectid);
				if (currentStep >= 14) {
					long intervalMilli = stepTime2.getTime()
							- stepTime.getTime(); // 相差的天数
					desc = (int) (intervalMilli / (24 * 60 * 60 * 1000) + 1)
							+ "天";
					from = sdf.format(stepTime);
					to = sdf.format(stepTime2);
					stepTime = stepTime2;
					if (currentStep > 14) {
						stepTime2 = step14.getConfirmTime();
					}
				} else if (currentStep == 13) {
					desc = "正在进行";
					ganttColor = "red";
					from = sdf.format(stepTime2);
					to = sdf.format(stepTime2);

				} else if (currentStep < 13) {
					desc = "未进行";
					from = sdf.format(stepTime2);
					ganttColor = "white";
					to = sdf.format(stepTime2);

				}
			} else if ("15".equals(step)) {
				name = "资料归档";
				Step15 step15 = step15Dao.getUnique(
						"from Step15 o where o.project.id=" + projectid);
				if (currentStep >= 15) {
					long intervalMilli = stepTime2.getTime()
							- stepTime.getTime(); // 相差的天数
					desc = (int) (intervalMilli / (24 * 60 * 60 * 1000) + 1)
							+ "天";
					from = sdf.format(stepTime);
					to = sdf.format(stepTime2);
					stepTime = stepTime2;
					if (currentStep > 15) {
						stepTime2 = step15.getConfirmTime();
					}
				} else if (currentStep == 14) {
					desc = "正在进行";
					ganttColor = "red";
					from = sdf.format(stepTime2);
					to = sdf.format(stepTime2);

				} else if (currentStep < 14) {
					desc = "未进行";
					from = sdf.format(stepTime2);
					ganttColor = "white";
					to = sdf.format(stepTime2);

				}
			} else if ("16".equals(step)) {
				if (currentStep > 15) {
					long intervalMilli = stepTime2.getTime()
							- stepTime.getTime(); // 相差的天数
					desc = (int) ((intervalMilli + (24 * 60 * 60 - 1) * 1000)
							/ (24 * 60 * 60 * 1000) + 1) + "天";
					from = sdf.format(stepTime);
					to = sdf.format(stepTime2);
					stepTime = stepTime2;
				} else if (currentStep == 15) {
					desc = "正在进行";
					ganttColor = "red";
					from = sdf.format(stepTime2);
					to = sdf.format(stepTime2);

				} else if (currentStep < 15) {
					desc = "未进行";
					from = sdf.format(stepTime2);
					ganttColor = "white";
					to = sdf.format(stepTime2);

				}
			}
			JSONObject value = new JSONObject();
			value.put("start", from);
			value.put("end", to);
			value.put("name", desc);
			value.put("color", ganttColor);
			JSONArray values = new JSONArray();
			values.add(value);

			JSONObject source = new JSONObject();
			source.put("name", stepName);
			source.put("series", values);
			sourcedata.add(source);
			stepName = name;
		}
		return sourcedata.toString();
	}

	public void saveToStep(Project project) {
		projectDao.save(project);
		Step1 step1 = new Step1();
		step1.setProject(project);
		step1Dao.save(step1);
		Step2 step2 = new Step2();
		step2.setProject(project);
		step2Dao.save(step2);
		Step3 step3 = new Step3();
		step3.setProject(project);
		step3Dao.save(step3);
		Step4 step4 = new Step4();
		step4.setProject(project);
		step4Dao.save(step4);
		Step5 step5 = new Step5();
		step5.setProject(project);
		step5Dao.save(step5);
		Step6 step6 = new Step6();
		step6.setProject(project);
		step6Dao.save(step6);
		Step7 step7 = new Step7();
		step7.setProject(project);
		step7Dao.save(step7);
		Step8 step8 = new Step8();
		step8.setProject(project);
		step8Dao.save(step8);
		Step9 step9 = new Step9();
		step9.setProject(project);
		step9Dao.save(step9);
		Step10 step10 = new Step10();
		step10.setProject(project);
		step10Dao.save(step10);
		Step11 step11 = new Step11();
		step11.setProject(project);
		step11Dao.save(step11);
		Step12 step12 = new Step12();
		step12.setProject(project);
		step12Dao.save(step12);
		Requisition req = new Requisition();
		req.setProject(project);
		requisitionService.save(req);
		Step13 step13 = new Step13();
		step13.setProject(project);
		step13Dao.save(step13);
		Step14 step14 = new Step14();
		step14.setProject(project);
		step14Dao.save(step14);
		Step15 step15 = new Step15();
		step15.setProject(project);
		step15Dao.save(step15);
	}

	// ====================== 基本 C R U D 方法 ===========================
	public Project get(int id) {
		return projectDao.get(id);
	}

	public void save(Project entity) {
		projectDao.save(entity);
	}

	public void update(Project entity) {
		projectDao.update(entity);
	}

	public void delete(int id) {
		projectDao.delete(id);
	}

	public List<Project> getAll() {
		return projectDao.getAll();
	}

	public String checkNO(String pno) {
		StringBuilder hql = new StringBuilder("from Project o where o.no = ? ");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(pno);
		List<Project> list = projectDao.getList(hql.toString(), paramList);
		if (list.size() > 0) {
			return "0";
		} else {
			return "1";
		}

	}
	
	public Project getByinfoId(Integer  projectInfoId) {
		StringBuilder hql = new StringBuilder();
		hql.append(
				"from Project bean where bean.projectInfo.id = ?");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(projectInfoId);
		Project Project = projectDao.getUnique(hql.toString(), paramList);
		return Project;
	}

}
