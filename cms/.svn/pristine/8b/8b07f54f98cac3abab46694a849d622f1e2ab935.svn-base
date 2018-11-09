package com.yhcrt.weihu.cms.action.directive.abs;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.yhcrt.weihu.cms.manager.assist.CmsBaoLiaoMng;
import com.yhcrt.weihu.common.web.freemarker.DirectiveUtils;

import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 标签基类
 * 
 */
public abstract class AbstractCmsBaoLiaoDirective implements
		TemplateDirectiveModel {

	/**
	 * 输入参数，站点ID。
	 */
	public static final String PARAM_SITE_ID = "siteId";
	/**
	 * 输入参数，站点ID。
	 */
	public static final String PARAM_USER_ID = "userId";
	/**
	 * 输入参数，是否审核。
	 */
	public static final String PARAM_CHECKED = "checked";
	/**
	 * 输入参数，是否推荐。
	 */
	public static final String PARAM_RECOMMEND = "recommend";
	/**
	 * 输入参数，标题。可以为null。
	 */
	public static final String PARAM_TITLE = "title";
	/**
	 * 输入报料状态参数，state。可以为null。
	 */
	public static final String PARAM_STATE = "state";
	/**
	 * 输入参数，排列顺序。1：按 评论多、时间降序；0：按时间降序。默认降序。
	 */
	public static final String PARAM_ORDER_BY = "orderBy";

	protected Integer getSiteId(Map<String, TemplateModel> params)
			throws TemplateException {
		return DirectiveUtils.getInt(PARAM_SITE_ID, params);
	}
	protected Integer getUserId(Map<String, TemplateModel> params)
			throws TemplateException {
		return DirectiveUtils.getInt(PARAM_USER_ID, params);
	}
	protected Boolean getChecked(Map<String, TemplateModel> params)
			throws TemplateException {
		Boolean checked = DirectiveUtils.getBool(PARAM_CHECKED, params);
		return checked != null ? checked : false;
	}

	protected boolean getRecommend(Map<String, TemplateModel> params)
			throws TemplateException {
		Boolean recommend = DirectiveUtils.getBool(PARAM_RECOMMEND, params);
		return recommend != null ? recommend : false;
	}

	protected String getTitle(Map<String, TemplateModel> params)
			throws TemplateException {
		return DirectiveUtils.getString(PARAM_TITLE, params);
	}
	protected Integer getState(Map<String, TemplateModel> params)
			throws TemplateException {
		return DirectiveUtils.getInt(PARAM_STATE, params);
	}
	protected boolean getDesc(Map<String, TemplateModel> params)
			throws TemplateException {
		Integer orderBy = DirectiveUtils.getInt(PARAM_ORDER_BY, params);
		if (orderBy == null || orderBy == 0) {
			return true;
		} else {
			return false;
		}
	}
	@Autowired
	protected CmsBaoLiaoMng cmsBaoLiaoMng;

}
