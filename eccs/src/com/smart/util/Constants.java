package com.smart.util;

public class Constants {

	public static final String[] STEP_NAME = new String[] { "咨询任务书", "（接收）资料登记",
			"编制咨询方案", "整理资料清单", "现场勘查", "底稿编制", "校对", "审核", "审定", "征求意见及反馈",
			"成果文件编制", "成果文件审核", "回访记录", "项目总结", "资料归档" };

	public static final Integer BACKLOG_DEFAULT = 1;// 待办事项初始状态

	public static final Integer BACKLOG_READ = 2;// 待办事项已读状态

	public static final Integer BACKLOG_SEND = 3;// 待办事项已转交处理状态

	public static final Integer BACKLOG_OVER = 4;// 待办事项已完成状态

	/**
	 * 用户类型
	 */
	public static interface UserType {
		/**
		 * 编制人
		 */
		public static final String WRITE = "write";
		
		/**
		 * 归档人
		 */
		public static final String NEW = "new";

		/**
		 * 审核人
		 */
		public static final String VALIDATE = "validate";

	}

	/**
	 * 部门根节点ID
	 */
	public static final String DEPT_ROOT_NODE_ID = "001";

	public static interface OperateType {

		public static final String SUBMIT = "提交";

		public static final String TRANSFER = "转交";

		public static final String SAVE = "保存";

		public static final String APPROVAL = "提交审批";

		public static final String TERMINATE = "终止项目";

		public static final String REPULS = "打回";

	}

	public static interface StepCode {
		/**
		 * 项目立项
		 */
		public static final String START_UP_PROJECT = "-5";

		/**
		 * 投标管理-人员分配
		 */
		public static final String BID_DISPATCH = "-99";

		/**
		 * 投标策划
		 */
		public static final String BID_PLAN = "-4";
		
		/**
		 * 报名评估
		 */
		public static final String BID_APPLY = "-401";
		
		/**
		 * 报名情况
		 */
		public static final String BID_APPLY_CASE = "-4011";
		
		/**
		 * 招标评估
		 */
		public static final String BID_CALL = "-402";
		
		/**
		 * 保证金申请
		 */
		public static final String BID_BOND = "-403";
		
		/**
		 * 保证金审核
		 */
		public static final String BID_BOND_CHECK = "-4031";
		
		/**
		 * 投标文件提交
		 */
		public static final String BID_FILE = "-404";
		
		/**
		 * 投标文件审核
		 */
		public static final String BID_FILE_CHECK = "-4041";
		
		/**
		 * 开标中标情况
		 */
		public static final String BID_INFO = "-405";
		
		/**
		 * 项目移交
		 */
		public static final String BID_TRANSFER = "-406";
		
		/**
		 * 投标总结
		 */
		public static final String BID_SUMMARY = "-3";

		/**
		 * 合同评审
		 */
		public static final String CONTRACT_REVIEW = "-2";

		/**
		 * 合同登记
		 */
		public static final String CONTRACT_REGISTRATION = "-1";

		/**
		 * 项目管理-人员分配
		 */
		public static final String PM_DISPATCH = "0";

		/**
		 * 咨询任务书
		 */
		public static final String STEP1 = "1";

		/**
		 * 资料登记
		 */
		public static final String STEP2 = "2";

		/**
		 * 编制咨询方案
		 */
		public static final String STEP3 = "3";

		/**
		 * 整理资料清单
		 */
		public static final String STEP4 = "4";

		/**
		 * 现场勘察
		 */
		public static final String STEP5 = "5";

		/**
		 * 底稿编制
		 */
		public static final String STEP6 = "6";

		/**
		 * 校对
		 */
		public static final String STEP7 = "7";

		/**
		 * 审核
		 */
		public static final String STEP8 = "8";

		/**
		 * 审定
		 */
		public static final String STEP9 = "9";

		/**
		 * 征求意见及反馈
		 */
		public static final String STEP10 = "10";

		/**
		 * 成果文件编制
		 */
		public static final String STEP11 = "11";

		/**
		 * 成果文件签订签发
		 */
		public static final String STEP12 = "12";
		
		/**
		 * 请款跟踪
		 */
		public static final String REQUEST_FUNDS = "17";

		/**
		 * 回访记录
		 */
		public static final String STEP13 = "13";

		/**
		 * 项目总结
		 */
		public static final String STEP14 = "14";

		/**
		 * 资料归档
		 */
		public static final String STEP15 = "15";

		/**
		 * 终止项目
		 */
		public static final String STOPED = "99";

		/**
		 * 已归档
		 */
		public static final String ARCHIVED = "16";

	}

	public static interface ProcessState {
		/**
		 * 正在进行
		 */
		public static final String IN_PROGRESS = "正在进行中";

		/**
		 * 已完成
		 */
		public static final String FINISHED = "已完成";

		/**
		 * 已归档
		 */
		public static final String ARCHIVED = "已归档";

		/**
		 * 已终止
		 */
		public static final String STOPED = "已终止";
	}

	public static interface ProcessType {
		/**
		 * 流程类型:0表示先经营管理后项目实施
		 */
		public static final String IN_ORDER = "0";

		/**
		 * 流程类型:1表示经营管理和项目实施可同时独立进行
		 */
		public static final String INDEPENDENTLY = "1";
	}

	/**
	 * 项目承接类型
	 */
	public static interface ReceiveType {
		/**
		 * 项目承接类型：0表示直接委托,项目流向: 项目立项 --> 合同管理-->进入项目流程(step1~step15)
		 */
		public static final Integer ENTRUST = 0;

		/**
		 * 项目承接类型：1表示招投标,项目流向：项目立项 --> 投标管理 --> 合同管理-->进入项目流程(step1~step15)
		 */
		public static final Integer BID = 1;
	}

}
