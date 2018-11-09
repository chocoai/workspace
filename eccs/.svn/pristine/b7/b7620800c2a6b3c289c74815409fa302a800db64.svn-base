package com.smart.util;

import java.util.ArrayList;
import java.util.List;

import com.smart.model.ProceStepDef;
import com.smart.model.Step3Worker;
import com.smart.model.Step3WorkerTemplate;

/**
 * 
 * @author 作者 : 陈伟
 * @date ：2017年2月13日 下午12:00:17
 * @Description:{Step3WorkerTemplate 模板装配}
 * @version 1.0
 */
public class TemplateUtil {

	public static Step3WorkerTemplate Step3WorkerToTemplate(
			ProceStepDef proceStepDef, Step3Worker step3Worker) {
		return new Step3WorkerTemplate(proceStepDef, step3Worker);
	}

	public static List<Step3WorkerTemplate> Step3WorkerTemplateList(
			List<ProceStepDef> psdList, List<Step3Worker> swList) {
		List<Step3WorkerTemplate> swtList = new ArrayList<Step3WorkerTemplate>();
		for (int i = 0; i < psdList.size(); i++) {
			Step3WorkerTemplate swt = null;
			if (swList.size() > 0) {
				if(swList.size() == psdList.size()){
					swt = new Step3WorkerTemplate(psdList.get(i), swList.get(i));
				}
				if(swList.size()<psdList.size() && i<swList.size()){
					swt = new Step3WorkerTemplate(psdList.get(i), swList.get(i));
				}
			} else {
				swt = new Step3WorkerTemplate(psdList.get(i),
						new Step3Worker());
			}
			swtList.add(swt);
		}
		return swtList;
	}

}
