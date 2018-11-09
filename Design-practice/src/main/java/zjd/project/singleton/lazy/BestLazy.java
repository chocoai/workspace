package zjd.project.singleton.lazy;

/**
 * \* User: zjd
 * \* Date: 2018/9/26
 * \* Time: 9:27
 * \* Description:  懒汉式单例
 特点：在外部类被调用的时候内部类才会被加载
 内部类一定是要在方法调用之前初始化
 巧妙地避免了线程安全问题
 这种形式兼顾饿汉式的内存浪费，也兼顾synchronized性能问题
 完美地屏蔽了这两个缺点
 史上最牛B的单例模式的实现方式
 */
public class BestLazy {

    private boolean initialize = false;

    //默认使用BestLazy的时候，会先初始化内部类
    //如果没使用的话，内部类是不加载的
    private BestLazy(){
        synchronized (BestLazy.class){
            if(initialize==false){
                initialize = !initialize;
            }else {
                throw new RuntimeException("单例已被侵犯！");
            }
        }
    }

    //每一个关键字都不是多余的
    //static 是为了使单例的空间共享
    //保证这个方法不会被重写，重载
    public static final BestLazy getInstance(){
        //在返回结果以前，一定会先加载内部类
        return LazyHolder.lazy;
    }

    //默认不加载;调用的时候加载
    private static class LazyHolder{
        private static final BestLazy lazy = new BestLazy();
    }

}