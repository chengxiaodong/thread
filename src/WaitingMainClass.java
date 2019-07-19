/**
 * Created by 晓东 on 2019/7/18.
 * QQ:2774398338
 */
public class WaitingMainClass {
    private static Object lock=new Object();
    private static  boolean flag=false;
    public static void main(String[] args){
        Thread threadA = new Thread(new MyTask1(), "A");
        Thread threadB = new Thread(new MyTask2(), "B");
        threadA.start();
        threadB.start();
    }
    static class MyTask1 implements Runnable{

        @Override
        public void run() {
            System.out.println("线程："+Thread.currentThread().getName()+",进入...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock){
                System.out.println("线程："+Thread.currentThread().getName()+",运行完成.");
                flag=true;
                lock.notifyAll();
                System.out.println("线程："+Thread.currentThread().getName()+",notifyAll()后，仍未释放锁...");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class MyTask2 implements Runnable {

        @Override
        public void run() {
            System.out.println("线程：" + Thread.currentThread().getName() + ",申请对象锁...");
            synchronized (lock){
                while (!flag) {//mystack1未执行
                        System.out.println("线程：" + Thread.currentThread().getName() + ",等待MyTask1执行完成...");
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("线程：" + Thread.currentThread().getName() + ",运行完成.");
            }
        }
    }
}
