package zjd.project.singleton.lazy;

import java.util.concurrent.CountDownLatch;

/**
 * \* User: zjd
 * \* Date: 2018/9/26
 * \* Time: 10:09
 * \* Description:
 * \
 */
public class LazyTest {
    public static void main(String[] args) {
//        long start = System.currentTimeMillis();
//        for (int i = 0; i < 200000000;i ++) {
//            Object obj = Lazy.getInstance();
//        }
//        long end = System.currentTimeMillis();
//        System.out.println("总耗时：" + (end - start));

        /**
         * 测试懒汉式单例第一个线程安全问题
         */
        int count = 200;
        //发令枪，我就能想到运动员
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
                    Object obj = Lazy.getInstance();
                    System.out.println(System.currentTimeMillis() + ":" + obj);
                }
            }).start();
            latch.countDown();
        }
        long end = System.currentTimeMillis();
        System.out.println("总耗时：" + (end - start));

//        CountDownLatch 并不是这样子用,实际应用场景中不要学老师这样投机取巧

        // Color.INSTANCE.getInstance();
    }
}