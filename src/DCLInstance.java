/**
 * @Author:陈晓东
 * @Date:2019/7/19 16:26
 * @Email:2775398338@qq.com
 * @Description:
 *      实现多线程时的单例模式
 */

/**
 * 单例类  //模拟logger日志类
 */

class Logger{
    //加volatile 禁止重排序
    private volatile static Object logger;
    Logger(){
        System.out.println("Logger构造对象...");
    }
    //DCL （Double Check Lock）在锁的前后都检查    +    volatile (禁止重排序)  ===》万无一失
    public static Object getInstance(){
        //如果为空  创建一个logger实例
        if(logger==null){
            synchronized (Logger.class){
                //可能在等待锁的过程中logger变量被其它线程初始化了，需要再次判断
                if (logger==null){
                    logger=new Logger();
                }
            }
        }
        //如果不为空 直接返回实例
        return logger;
    }

    public void log(){
        System.out.println("log");
    }
}

/**
 *
 */
public class DCLInstance {
    public static void main(String[] args){
        //创建100个线程 获得Logger的单例对象
        for (int i = 0; i < 10000; i++) {
            new Thread(()->{
                Logger.getInstance();
            }).start();
        }
    }
}
