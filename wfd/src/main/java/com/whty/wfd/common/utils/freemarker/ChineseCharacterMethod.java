/**
 * 
 */
package com.whty.wfd.common.utils.freemarker;

import java.util.List;

import com.whty.wfd.common.utils.ChineseCharacterUtil;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

/**
 * @author zhangzheng
 * @date 2018年8月22日
 */
public class ChineseCharacterMethod implements TemplateMethodModelEx {

	/*
	 * (non-Javadoc)
	 * 
	 * @see freemarker.template.TemplateMethodModelEx#exec(java.util.List)
	 */
	@Override
	public Object exec(List args) throws TemplateModelException {
		String str = (String) args.get(0);
		String zimu = ChineseCharacterUtil.convertHanzi2Pinyin(str, false).substring(0, 1).toUpperCase();
		return zimu;
	}

}
