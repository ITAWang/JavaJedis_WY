/**
 * @ClassName MyRun
 * @Description: TODO
 * @Author: itwang@qq.com
 */
public class MyRun implements Runnable{
    private Service sc = null;

    public MyRun(String level,int num) {
        sc = new Service(level,num);
    }

    public void run() {
        while (true){
            sc.service();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
