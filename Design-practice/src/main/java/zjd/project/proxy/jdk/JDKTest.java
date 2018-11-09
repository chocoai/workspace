package zjd.project.proxy.jdk;

import zjd.project.proxy.cglib.ZhengJiaDong;

/**
 * \* User: zjd
 * \* Date: 2018/9/30
 * \* Time: 15:52
 * \* Description:
 * \
 */
public class JDKTest {

    public static void main(String[] args) {
        Person demo = (Person)new JDKProxy().getInstance(new ZhengJiaDong());
        demo.findJob();
    }
}