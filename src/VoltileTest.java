/**
 * Created by 晓东 on 2019/7/19.
 * QQ:2774398338
 */

/**
 * 竞争资源类
 */
class MyData{
    //不加volatile
        //int number=0;
    //加volatile
    volatile  int number=0;
    public void addTo60(){
        this.number=60;
    }
}

/**
 * 1、测试添加Voltile解决可见性问题
 * 2、volatile适用于一个线程写 多个线程 读的情况
 */
public class VoltileTest {
    public static void main(String[] args) throws InterruptedException {
        //竞争资源
        MyData myData=new MyData();
        //在非主线程中改变资源的number属性
        new Thread( ()-> {
            try {
                Thread.sleep(5*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.addTo60();
            System.out.println("Thread :" + Thread.currentThread().getName() + "\t number value:" + myData.number);

        }
                ,"AA").start();
        //主线程中number未被通知刷新
        while (0==myData.number){
        }
        //主线程中number被通知刷新
        System.out.println("Thread :"+Thread.currentThread().getName()+"\t number value:"+myData.number);

    }
}
