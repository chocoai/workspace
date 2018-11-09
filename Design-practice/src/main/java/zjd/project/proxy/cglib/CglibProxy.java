package zjd.project.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * \* User: zjd
 * \* Date: 2018/9/30
 * \* Time: 15:36
 * \* Description:cglib代理只能代理实体类：针对类实现代理，继承方式
 * \
 */
public class CglibProxy implements MethodInterceptor {

    public Object getInstance(Class<?> clazz){
        Enhancer enhancer = new Enhancer();
        //要把哪个设置为即将生成的新类父类
        enhancer.setSuperclass(clazz);

        enhancer.setCallback(this);

        return  enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        //业务的增强

        System.out.println("我是中介：我要给你找工作，现在已经拿到你的需求");
        System.out.println("开始物色");

        methodProxy.invokeSuper(o,objects);

        System.out.println("如果合适的话，就准备面试");
        return null;
    }
}