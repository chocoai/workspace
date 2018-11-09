package com.yhcrt.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.Assert;

/**
 * 反射工具类
 * 
 */
public class ReflectionUtil {

    private static Log log = LogFactory.getLog(ReflectionUtil.class);

    private ReflectionUtil() {
    }

    /**
     * 直接调用对象方法 对于基本数据类型不能直接从args中判断（都被装箱成对象），所以必须手动传入
     * 
     * @param target
     * @param methodName
     * @param args
     * @return
     */
    public static Object invokeMethod(final Object target, final String methodName, final Class[] paramTypes,
	    final Object... args) {
	if (CollectionUtil.notEmpty(paramTypes) && paramTypes.length != args.length) {
	    log.error("参数类型与参数个数不相等：" + methodName);
	    return null;
	}

	try {
	    Method method = target.getClass().getMethod(methodName, paramTypes);
	    return method.invoke(target, args);
	} catch (NoSuchMethodException e) {
	    StringBuilder msg = new StringBuilder();
	    for (Class c : paramTypes) {
		msg.append(", ").append(c.getName());
	    }
	    log.error("无此方法：" + methodName + "(" + (msg.length() > 0 ? msg.substring(2) : msg) + ")");
	} catch (SecurityException e) {
	    log.error("权限不够：" + methodName);
	} catch (Exception e) {
	    log.error("方法调用异常：" + e.getMessage());
	}
	return null;
    }

    /**
     * 直接调用对象方法
     * 
     * @param target
     * @param methodName
     * @param args
     * @return
     */
    public static Object invokeMethod(final Object target, final String methodName, final Object... args) {
	int size = args.length;
	Class[] paramTypes = new Class[size];
	for (int i = 0; i < size; i++) {
	    Class type = args[i].getClass();
	    paramTypes[i] = type;
	}
	return invokeMethod(target, methodName, paramTypes, args);
    }

    /**
     * 直接读取对象属性,无视private/protected修饰符,不经过getter函数
     * 
     * @param target
     * @param fieldName
     * @return
     */
    public static Object getFieldValue(final Object target, final String fieldName) {
	Field field = getDeclaredField(target, fieldName);

	if (!field.isAccessible()) {
	    field.setAccessible(true);
	}

	Object result = null;
	try {
	    result = field.get(target);
	} catch (IllegalAccessException e) {
	    log.error("不可能抛出的异常" + e.getMessage());
	}
	return result;
    }

    /**
     * 直接设置对象属性,无视private/protected修饰符,不经过setter函数
     * 
     * @param target
     * @param fieldName
     * @param value
     */
    public static void setFieldValue(final Object target, final String fieldName, final Object value) {
	
	Field field = getDeclaredField(target, fieldName);
	if (!field.isAccessible()) {
	    field.setAccessible(true);
	}
	try {
	    field.set(target, value);
	} catch (IllegalAccessException e) {
	    log.error("不可能抛出的异常: " + e.getMessage());
	}
    }

    /**
     * 循环向上转型,获取对象的DeclaredField
     * 
     * @param target
     * @param fieldName
     * @return
     */
    public static Field getDeclaredField(final Object target, final String fieldName) {
	try {
	    return getDeclaredField(target.getClass(), fieldName);
	} catch (NoSuchFieldException e) {
	    log.error("无此属性: " + fieldName);
	}
	return null;
    }

    /**
     * 循环向上转型,获取类的DeclaredField
     * 
     * @param clazz
     * @param fieldName
     * @return
     * @throws NoSuchFieldException
     */
    @SuppressWarnings("unchecked")
    public static Field getDeclaredField(final Class clazz, final String fieldName) throws NoSuchFieldException {
	for (Class superClass = clazz; superClass != Object.class; superClass = superClass.getSuperclass()) {
	    try {
		return superClass.getDeclaredField(fieldName);
	    } catch (NoSuchFieldException e) {
		// Field不在当前类定义,继续向上转型
	    }
	}
	throw new NoSuchFieldException("No such field: " + clazz.getName() + '.' + fieldName);
    }

    /**
     * 提取集合中的对象的属性,组合成List.
     * 
     * @param collection
     *            来源集合.
     * @param propertityName
     *            要提取的属性名.
     */
    @SuppressWarnings("unchecked")
    public static List fetchElementPropertyToList(final Collection collection, final String propertyName) {
	List list = new ArrayList();
	try {
	    for (Object obj : collection) {
		list.add(PropertyUtils.getProperty(obj, propertyName));
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return list;
    }

    /**
     * 提取集合中的对象的属性,组合成由分割符分隔的字符串.
     * 
     * @param collection
     *            来源集合.
     * @param propertityName
     *            要提取的属性名.
     * @param separator
     *            分隔符.
     */
    @SuppressWarnings("unchecked")
    public static String fetchElementPropertyToString(final Collection collection, final String propertyName,
	    final String separator) {
	List list = fetchElementPropertyToList(collection, propertyName);
	return StringUtils.join(list, separator);
    }

    /**
     * bean值的拷贝,把obj2中的值拷贝到obj1中
     * 
     * @param obj1
     *            新目标
     * @param obj2
     *            源目标
     * @param except
     *            不需要覆盖的属性 格式 abc,def,ghi
     */
    public static void bean2Bean(final Object obj1, final Object obj2, String except) {
	Field[] fieldArr = obj1.getClass().getDeclaredFields();
	for (Field field : fieldArr) {
	    String fieldName = field.getName();
	    Object value = getFieldValue(obj2, fieldName);
	    // 过滤属性
	    if (!StringUtils.isBlank(except) && except.indexOf(fieldName) > -1) {
		continue;
	    }
	    if (null != value) {
		setFieldValue(obj1, fieldName, value);
	    }
	}
    }

    public static void bean2Bean(final Object obj1, final Object obj2) {
	bean2Bean(obj1, obj2, null);
    }

    /**
     * map转换成bean，只转换基本属性，对象型不转换
     * 
     * @param <T>
     * @param map
     * @param bean
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static <T> T map2Bean(Map map, Class<T> bean) {
	T obj = null;
	try {
	    obj = bean.newInstance();
	} catch (InstantiationException e) {
	    e.printStackTrace();
	} catch (IllegalAccessException e) {
	    e.printStackTrace();
	}

	Field[] fieldArr = bean.getDeclaredFields();
	for (Field field : fieldArr) {
	    String key = field.getName();
	    Object value = map.get(key);
	    if (value == null) {
		continue;
	    }
	    String typeStr = field.getType().toString();
	    String type = typeStr.substring(typeStr.lastIndexOf(".") + 1);
	    if ("Integer".equals(type)) {
		value = Integer.parseInt((value.toString()));
	    } else if ("Long".equals(type)) {
		value = Long.parseLong((value.toString()));
	    } else if ("Double".equals(type)) {
		value = Double.parseDouble((value.toString()));
	    } else if ("String".equals(type)) {
		value = value.toString();
	    } else {
		continue;
	    }
	    setFieldValue(obj, key, value);
	}
	return obj;
    }

}