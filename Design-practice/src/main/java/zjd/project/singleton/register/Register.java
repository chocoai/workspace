package zjd.project.singleton.register;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * \* User: zjd
 * \* Date: 2018/9/26
 * \* Time: 9:52
 * \* Description:注册式单例
 * \Spring中的做法，就是用这种注册式单例
 */
public class Register {

    private Register(){}

    //线程安全
    private static Map<String,Object> ioc = new ConcurrentHashMap<>();

    public static Object getBean(String className){
        if (className==null) {
            className = Register.class.getName();
        }
        synchronized (Register.class){
            if(!ioc.containsKey(className)){
                try {
                    ioc.put(className,Class.forName(className).newInstance());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return ioc.get(className);
    }
}