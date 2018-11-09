package zjd.project.proxy.cglib;

import zjd.project.proxy.jdk.Person;

/**
 * \* User: zjd
 * \* Date: 2018/9/30
 * \* Time: 15:35
 * \* Description:
 * \
 */
public class ZhengJiaDong implements Person {

    public void findJob(){
        //找工作，标准：离家近、工资高、不加班
        System.out.println("找工作，标准：离家近、工资高、不加班");
    }
}