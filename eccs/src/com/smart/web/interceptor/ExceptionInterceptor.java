package com.smart.web.interceptor;

import java.io.IOException;
import java.sql.SQLException;

import org.hibernate.cache.CacheException;
import org.springframework.dao.DataAccessException;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class ExceptionInterceptor extends AbstractInterceptor {
    private static final long serialVersionUID = 1L;

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
	String result = "error1";
	try {
	    result = invocation.invoke();
	} catch (DataAccessException ex) {
	    ex.printStackTrace();
	    return "error1";
	    // throw new SystemException("数据库操作失败");
	} catch (NullPointerException ex) {
	    ex.printStackTrace();
	    return "error1";
	    // throw new SystemException("调用了未经初始化的对象或者是不存在的对象");
	} catch (IOException ex) {
	    ex.printStackTrace();
	    return "error1";
	    // throw new SystemException("IO异常");
	} catch (ClassNotFoundException ex) {
	    ex.printStackTrace();
	    return "error1";
	    // throw new SystemException("指定的类不存在");
	} catch (ArithmeticException ex) {
	    ex.printStackTrace();
	    return "error1";
	    // throw new SystemException("数学运算异常");
	} catch (ArrayIndexOutOfBoundsException ex) {
	    ex.printStackTrace();
	    return "error1";
	    // throw new SystemException("数组下标越界");
	} catch (IllegalArgumentException ex) {
	    ex.printStackTrace();
	    return "error1";
	    // throw new SystemException("方法的参数错误");
	} catch (CacheException ex) {
	    ex.printStackTrace();
	    return "error1";
	    // throw new SystemException("缓存存取时出现异常");
	} catch (ClassCastException ex) {
	    ex.printStackTrace();
	    return "error1";
	    // throw new SystemException("类型强制转换错误");
	} catch (SecurityException ex) {
	    ex.printStackTrace();
	    return "error1";
	    // throw new SystemException("违背安全原则异常");
	} catch (SQLException ex) {
	    ex.printStackTrace();
	    return "error1";
	    // throw new SystemException("操作数据库异常");

	} catch (NoSuchMethodError ex) {
	    ex.printStackTrace();
	    return "error1";
	    // throw new SystemException("方法末找到异常");
	} catch (InternalError ex) {
	    ex.printStackTrace();
	    return "error1";
	    // throw new SystemException("Java虚拟机发生了内部错误");
	} catch (Exception ex) {
	    ex.printStackTrace();
	    return "error1";
	    // throw new SystemException("程序内部错误操作失败");
	}
	return result;
    }
}
