package com.smart.auto;

import java.io.File;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.smart.util.FileUtil;
import com.smart.util.Property;
import com.smart.util.StringUtil;

/**
 * 根据实体类自动生成 dao daoImpl service
 * 
 * @author 充满智慧的威哥
 */
public class GeneratorTool {

    private final Log log = LogFactory.getLog(getClass());

    private String baseDao;

    private String baseDaoImpl;

    private String entityPath; // 实体类路径

    private String daoPath; // dao层路径

    private String daoImplPath; // daoImpl层路径

    private String servicePath; // service层路径

    private String daoTemplatePath; // 生成dao 模版路径

    private String daoImplTemplatePath; // 生成dao.impl 模版路径

    private String serviceTemplatePath; // 生成service 模版路径

    private String encoding = "UTF-8";

    // 初始化参数 从配置文件中得到
    public void init() {
	String propertyPath = "src/com/smart/auto/config.properties"; // 使用默认路径
	init(propertyPath);
    }

    public void init(String propertyPath) {
	Map<String, String> properties = Property.getProperties(propertyPath);

	/** base包 **/
	baseDao = properties.get("baseDao");
	baseDaoImpl = properties.get("baseDaoImpl");

	/** model位置 **/
	entityPath = properties.get("entityPath");

	/** 各个包的位置 **/
	daoPath = properties.get("daoPath");
	daoImplPath = properties.get("daoImplPath");
	servicePath = properties.get("servicePath");

	/** 模板文件的位置 **/
	daoTemplatePath = properties.get("daoTemplatePath");
	daoImplTemplatePath = properties.get("daoImplTemplatePath");
	serviceTemplatePath = properties.get("serviceTemplatePath");
    }

    /**
     * 自动生成dao
     * 
     * @throws Exception
     */
    public void autoDao() {
	// 得到所有实体类文件
	File entityDir = new File(entityPath);
	String entity = getImportPath(entityPath);
	File[] entities = entityDir.listFiles();

	//
	String daoImport = getImportPath(daoPath);
	String daoContent = FileUtil.readTxt(daoTemplatePath, encoding);

	String content = ""; // 生成文件的内容
	String filePath = ""; // 生成文件的位置

	for (File file : entities) {
	    // 过滤掉配置文件
	    if (!file.getName().endsWith("java")) {
		continue;
	    }

	    String entityName = getEntityName(file);
	    String daoName = entityName + "Dao";
	    String model = entity + "." + entityName;

	    // 主键类型
	    String idType = getIdType(model);

	    // 生成dao
	    content = daoContent.replaceAll("#package#", daoImport);
	    content = content.replaceAll("#baseDao#", baseDao);
	    content = content.replaceAll("#model#", model);
	    content = content.replaceAll("#daoName#", daoName);
	    content = content.replaceAll("#entityName#", entityName);
	    content = content.replaceAll("#idType#", idType);
	    filePath = daoPath + "/" + entityName + "Dao.java";

	    // 如果存在就不创建
	    if (!new File(filePath).exists()) {
		FileUtil.write(filePath, content, encoding);
	    }

	}
	log.info("=== 自动生成 dao 完成 ===");
    }

    /**
     * 自动生成daoImpl
     * 
     * @throws Exception
     */
    public void autoDaoImpl() {
	// 得到所有实体类文件
	File entityDir = new File(entityPath);
	String entity = getImportPath(entityPath);
	File[] entities = entityDir.listFiles();

	//
	String daoImport = getImportPath(daoPath);
	String daoImplImport = getImportPath(daoImplPath);

	String daoImplContent = FileUtil.readTxt(daoImplTemplatePath, encoding);

	String content = ""; // 生成文件的内容
	String filePath = ""; // 生成文件的位置

	for (File file : entities) {
	    // 过滤掉配置文件
	    if (!file.getName().endsWith("java")) {
		continue;
	    }

	    String entityName = getEntityName(file);
	    String daoName = entityName + "Dao";
	    String daoImplName = entityName + "DaoImpl";
	    String model = entity + "." + entityName;
	    String dao = daoImport + "." + daoName;

	    // 主键类型
	    String idType = getIdType(model);

	    // 生成daoImpl
	    content = daoImplContent.replaceAll("#package#", daoImplImport);
	    content = content.replaceAll("#baseDaoImpl#", baseDaoImpl);
	    content = content.replaceAll("#dao#", dao);
	    content = content.replaceAll("#model#", model);
	    content = content.replaceAll("#daoImplName#", daoImplName);
	    content = content.replaceAll("#entityName#", entityName);
	    content = content.replaceAll("#idType#", idType);
	    content = content.replaceAll("#daoName#", daoName);

	    filePath = daoImplPath + "/" + entityName + "DaoImpl.java";
	    // 如果存在就不创建
	    if (!new File(filePath).exists()) {
		FileUtil.write(filePath, content, encoding);
	    }

	}
	log.info("=== 自动生成 daoImpl 完成 ===");
    }

    /**
     * 自动生成service
     * 
     * @throws Exception
     */
    public void autoService() {
	// 得到所有实体类文件
	File entityDir = new File(entityPath);
	String entity = getImportPath(entityPath);
	File[] entities = entityDir.listFiles();

	//
	String daoImport = getImportPath(daoPath);
	String serviceImport = getImportPath(servicePath);
	String serviceContent = FileUtil.readTxt(serviceTemplatePath, encoding);

	String content = ""; // 生成文件的类容
	String filePath = ""; // 生成文件的位置

	for (File file : entities) {
	    // 过滤掉配置文件
	    if (!file.getName().endsWith("java")) {
		continue;
	    }

	    String entityName = getEntityName(file);
	    String daoName = entityName + "Dao";
	    String daoEntity = StringUtil.firstCharLowerCase(daoName);
	    String serviceName = entityName + "Service";
	    String model = entity + "." + entityName;
	    String dao = daoImport + "." + daoName;

	    // 主键类型
	    String idType = getIdType(model);
	    if ("Integer".equals(idType)) {
		idType = "int";
	    } else if ("Long".equals(idType)) {
		idType = "long";
	    } else if ("Double".equals(idType)) {
		idType = "double";
	    }

	    // 生成service
	    content = serviceContent.replaceAll("#package#", serviceImport);
	    content = content.replaceAll("#dao#", dao);
	    content = content.replaceAll("#model#", model);
	    content = content.replaceAll("#serviceName#", serviceName);
	    content = content.replaceAll("#daoName#", daoName);
	    content = content.replaceAll("#daoEntity#", daoEntity);
	    content = content.replaceAll("#entityName#", entityName);
	    content = content.replaceAll("#idType#", idType);

	    filePath = servicePath + "/" + entityName + "Service.java";

	    // 如果存在就不创建
	    if (!new File(filePath).exists()) {
		FileUtil.write(filePath, content, encoding);
	    }

	}
	log.info("=== 自动生成 service 成功 ===");
    }

    /**
     * 得到主键类型
     * 
     * @param model
     *            位置 com.sw.User
     * @return
     * @throws Exception
     */
    private String getIdType(String model) {
	try {
	    Class<?> clz = Class.forName(model);
	    return clz.getDeclaredFields()[0].getType().getSimpleName();
	} catch (SecurityException e) {
	    e.printStackTrace();
	} catch (ClassNotFoundException e) {
	    e.printStackTrace();
	}
	return null;
    }

    /**
     * 根据包的路径得到package XXX中的XXX
     * 
     * @param path
     * @return
     */
    private String getImportPath(String path) {
	StringBuffer result = new StringBuffer();
	String[] strings = path.split("/");
	for (int i = 1, size = strings.length; i < size; i++) {
	    result.append(".").append(strings[i]);
	}
	return result.substring(1);
    }

    /**
     * 得到实体名称
     * 
     * @param entity
     * @return
     */
    private String getEntityName(File entity) {
	return entity.getName().substring(0, entity.getName().indexOf("."));
    }

    public static void main(String[] args) {
	GeneratorTool mg = new GeneratorTool();
	mg.init();
	mg.autoDao();
	mg.autoDaoImpl();
	mg.autoService();
    }
}
