package zjd.project.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * \* User: zjd
 * \* Date: 2018/9/30
 * \* Time: 15:46
 * \* Description:JDK动态代理只能对实现了接口的类生成代理，而不能针对类
 * \
 */
public class JDKProxy implements InvocationHandler{

    private Person person;

    public Object getInstance(Person personProxy){
        this.person = personProxy;
        Class<?> clazz = person.getClass();
        return Proxy.newProxyInstance(clazz.getClassLoader(),clazz.getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("我是中介：我要给你找工作，现在已经拿到你的需求");
        System.out.println("开始物色");
        method.invoke(this.person,args);

        System.out.println("如果合适的话，就准备面试");
        return  null;
    }
}