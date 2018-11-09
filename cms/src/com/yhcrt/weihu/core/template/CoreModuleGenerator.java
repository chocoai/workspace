package com.yhcrt.weihu.core.template;

import com.yhcrt.weihu.common.developer.ModuleGenerator;

public class CoreModuleGenerator {
	private static String packName = "com.yhcrt.weihu.core.template";
	private static String fileName = "cmscore.properties";

	public static void main(String[] args) {
		new ModuleGenerator(packName, fileName).generate();
	}
}
