package zjd.project.singleton.register;

import java.util.concurrent.CountDownLatch;

/**
 * \* User: zjd
 * \* Date: 2018/9/26
 * \* Time: 10:10
 * \* Description:
 * \
 */
public class RegisterTest {

    public static void main(String[] args) {
        int count = 200;
        final CountDownLatch latch = new CountDownLatch(count);
        long start = System.currentTimeMillis();
        for (int i = 0; i < count;i ++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Object obj = Register.getBean("RegisterPojo");
                    System.out.println(System.currentTimeMillis()+":"+obj);
                }
            }).start();
            latch.countDown();
        }
        long end = System.currentTimeMillis();
        System.out.println("总耗时：" + (end - start));
    }
}