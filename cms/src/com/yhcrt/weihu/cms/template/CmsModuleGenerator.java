package com.yhcrt.weihu.cms.template;


public class CmsModuleGenerator {
	private static String packName = "com.yhcrt.weihu.cms.template";
	private static String fileName = "cms.properties";

	public static void main(String[] args) {
		new ModuleGenerator(packName, fileName).generate();
	}
}
