package zjd.project.proxy.cglib;

/**
 * \* User: zjd
 * \* Date: 2018/9/30
 * \* Time: 15:42
 * \* Description:
 * \
 */
public class CglibTest {

    public static void main(String[] args) {
        try {
            ZhengJiaDong zjd = (ZhengJiaDong) new CglibProxy().getInstance(ZhengJiaDong.class);
            zjd.findJob();
            System.out.println("--------------------------------");
            // System.out.println(obj.getClass());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}