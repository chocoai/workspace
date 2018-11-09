package com.yhcrt.weihu.cms.action.directive;

import static com.yhcrt.weihu.cms.Constants.TPL_STYLE_LIST;
import static com.yhcrt.weihu.cms.Constants.TPL_SUFFIX;
import static com.yhcrt.weihu.common.web.Constants.UTF8;
import static com.yhcrt.weihu.common.web.freemarker.DirectiveUtils.OUT_LIST;
import static com.yhcrt.weihu.core.web.util.FrontUtils.PARAM_STYLE_LIST;
import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.yhcrt.weihu.cms.action.directive.abs.AbstractContentDirective;
import com.yhcrt.weihu.cms.entity.main.Content;
import com.yhcrt.weihu.common.web.freemarker.DirectiveUtils;
import com.yhcrt.weihu.common.web.freemarker.ParamsRequiredException;
import com.yhcrt.weihu.common.web.freemarker.DirectiveUtils.InvokeType;
import com.yhcrt.weihu.core.entity.CmsSite;
import com.yhcrt.weihu.core.web.util.FrontUtils;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 内容列表标签
 */
public class ContentListDirective extends AbstractContentDirective {
    /**
     * 模板名称
     */
    public static final String TPL_NAME = "content_list";

    /**
     * 输入参数，文章ID。允许多个文章ID，用","分开。排斥其他所有筛选参数。
     */
    public static final String PARAM_IDS = "ids";

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
	    throws TemplateException, IOException {
	// 获取站点
	CmsSite site = FrontUtils.getSite(env);
	// 获取内容列表，可以通过此处进行更改，获取自己数据库中的数据
	List<Content> list = getList(params, env);

	Map<String, TemplateModel> paramWrap = new HashMap<String, TemplateModel>(params);
	// OUT_LIST值为tag_list，在类DirectiveUtils中声明，将内容列表放入其中
	paramWrap.put(OUT_LIST, DEFAULT_WRAPPER.wrap(list));
	// 将params的值复制到variable中
	Map<String, TemplateModel> origMap = DirectiveUtils.addParamsToVariable(env, paramWrap);
	// 获取的是参数PARAM_TPL，是否调用模板以及调用的模板类型
	InvokeType type = DirectiveUtils.getInvokeType(params);
	// 获取传入参数，列表样式，根据不同的参数获取不同的样式列表
	String listStyle = DirectiveUtils.getString(PARAM_STYLE_LIST, params);
	if (InvokeType.sysDefined == type) {
	    if (StringUtils.isBlank(listStyle)) {
		throw new ParamsRequiredException(PARAM_STYLE_LIST);
	    }
	    // 列表样式模板
	    env.include(TPL_STYLE_LIST + listStyle + TPL_SUFFIX, UTF8, true);
	} else if (InvokeType.userDefined == type) {
	    if (StringUtils.isBlank(listStyle)) {
		throw new ParamsRequiredException(PARAM_STYLE_LIST);
	    }
	    FrontUtils.includeTpl(TPL_STYLE_LIST, site, env);
	} else if (InvokeType.custom == type) {
	    FrontUtils.includeTpl(TPL_NAME, site, params, env);
	} else if (InvokeType.body == type) {
	    body.render(env.getOut());
	} else {
	    throw new RuntimeException("invoke type not handled: " + type);
	}
	DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
    }

    @SuppressWarnings("unchecked")
    protected List<Content> getList(Map<String, TemplateModel> params, Environment env) throws TemplateException {
	Integer[] ids = DirectiveUtils.getIntArray(PARAM_IDS, params);
	if (ids != null) {
	    // 根据内容ID数组获取文章列表
	    return contentMng.getListByIdsForTag(ids, getOrderBy(params));
	} else {
	    return (List<Content>) super.getData(params, env);
	}
    }

    @Override
    protected boolean isPage() {
	return false;
    }
}
