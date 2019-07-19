/**
 * Created by 晓东 on 2019/7/18.
 * QQ:2774398338
 */
public class MainClass {
    private static Object lock=new Object();
    public static void main(String[] args){
        Thread threadA = new Thread(new MyTask(), "A");
        Thread threadB = new Thread(new MyTask(), "B");
        threadA.start();
        threadB.start();
    }
    static class MyTask implements Runnable{

        @Override
        public void run() {
            System.out.println("线程："+Thread.currentThread().getName()+",申请对象锁...");
            synchronized (lock){
                System.out.println("线程："+Thread.currentThread().getName()+",获得对象锁...");
                try {
                    Thread.sleep(60*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("线程："+Thread.currentThread().getName()+",运行完成.");
        }
    }
}
