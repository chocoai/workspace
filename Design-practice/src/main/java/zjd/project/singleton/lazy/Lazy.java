package zjd.project.singleton.lazy;

/**
 * \* User: zjd
 * \* Date: 2018/9/26
 * \* Time: 8:59
 * \* Description:懒汉式单例
 * 在外部需要使用的时候才进行实例化
 * \
 */
public class Lazy {

    //静态块，公共内存区域
    private static Lazy lazy = null;

    /**
     * 没加锁的情况下，存在线程安全问题
     * @return
     */
    public static Lazy getInstance(){
        //调用方法之前，先判断
        //如果没有初始化，将其进行初始化,并且赋值
        //将该实例缓存好
        if(lazy==null){
            //两个线程都会进入这个if里面
            lazy = new Lazy();
        }
        //如果已经初始化，直接返回之前已经保存好的结果
        return lazy;
    }

    /**
     * 加锁之后，耗时比较长大约是没加锁1000倍左右
     * @return
     */
    public static synchronized Lazy getInstance2(){
        if(lazy == null){
            lazy = new Lazy();
        }
        return lazy;
    }

}