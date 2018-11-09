package com.whty.wfd.common.excel;

/**
 * 描述:自定义的常量存储类，用于存储常量
 * 
 * @author: 黄凯
 * @version: v1.0
 * @company: Coship
 */
public abstract class ConstantParam {

	/**
	 * 获得项目的物理路径
	 */
	public static String APPLICATION_PATH = "application_path";

	/**
	 * 用户的默认积分
	 */
	public static Integer USER_DEFAULT_SCORE = 1000;

	/**
	 * 导出的excel脚本sheet名
	 */
	public static String EXCEL_DEFAULT_SHEET_NAME = "sheet1";

	/**
	 * 业务状态，3:表示审核通过
	 */
	public static String CASE_STATE = "3";

	/**
	 * 产品流程状态,0表示新增；
	 */
	public static String FLOW_STATE = "0";

	/**
	 * 产品预上线状态；
	 */
	public static String ONLINE_STATE = "1";

	/**
	 * 产品预下线状态；
	 */
	public static String DOWN_STATE = "0";
	/**
	 * 产品立即上线状态；
	 */
	public static String NOW_ONLINE_STATE = "2";
	/**
	 * 产品立即下线状态；
	 */
	public static String NOW_DOWN_STATE = "3";
	/**
	 * 免费
	 */
	public static String CHARGE_TYPE_FREE = "01";
	/**
	 * 按次
	 */
	public static String CHARGE_TYPE_TIMES = "02";
	/**
	 * 包月
	 */
	public static String CHARGE_TYPE_MONTH = "03";
	/**
	 * 按下载量
	 */
	public static String CHARGE_TYPE_COUNT = "04";
	/**
	 * 产品分类:便民
	 */
	public static String PRODUCT_TYPE_PM = "1";
	/**
	 * 产品分类：政务
	 */
	public static String PRODUCT_TYPE_ZW = "2";
	/**
	 * 访问方式：WEB
	 */
	public static String VISIT_WAY_WEB = "1";
	/**
	 * 访问方式：WAP
	 */
	public static String VISIT_WAY_WAP = "2";

	/**
	 * 业务上传文件存储路径
	 */
	public static String CASE_VISITWAY_PATH = "upload/seft_package/";

	/**
	 * 业务上传文件存储路径
	 */
	public static String SP_PATH = "upload/SpUploadFile/";
	/**
	 * 用户注销
	 */
	public static String LOGOUT = "logout";

	/**
	 * sme资源树类型,应用树
	 */
	public static String APP_TREE_TYPE = "1";

	/**
	 * cms资源树类型,内容树
	 */
	public static String CONTENT_TREE_TYPE = "2";
	public static String CASE_DOCUMETNTOOL_PATH = "upload/image/";

}
