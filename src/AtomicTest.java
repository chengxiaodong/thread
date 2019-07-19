/**
 * Created by 晓东 on 2019/7/19.
 * QQ:2774398338
 */


/**
 * 资源类
 */
 class Mydata2{
    volatile  int number=0;
    public void addPlusPlus(){
        //模拟number++
        int temp=number;
        temp=temp+1;
        number=temp;
    }
}
/**
 * @Author 陈晓东
 * @Description
 *          1、测试Volatile无法保证原子性操作
 *                 原因：（1）方法执行结束但是还未从本地内存刷新到主内存。此时（2）其它线程执行并且将+1后的结果保存到主存，
 *                 在这种情况下：（1）因为加了volatile,会加载主存中最新的number到本地内存，这样就覆盖了（1）内存原有+1后的结果
 *          2、volatile适用于一个线程写 多个线程 读的情况
 * @Date 2019/7/19 14:37
 * @Param
 * @return
 **/
public class AtomicTest {
    public static void main(String[] args){
        //创建资源对象
        Mydata2 myData=new Mydata2();
        //执行20个线程，每个线程调用add++ 1000次
        for (int i = 0; i < 20; i++) {
            new Thread(() ->{
                for (int j = 0; j <1000 ; j++) {
                    myData.addPlusPlus();
                }
            }).start();
        }
        //主线程等待线程池执行结束
        while (Thread.activeCount()>2){

        }
        //打印number结果
        System.out.println("线程："+Thread.currentThread().getName()+",number value:"+myData.number);
    }
}






















