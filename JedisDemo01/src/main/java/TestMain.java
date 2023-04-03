/**
 * @ClassName TestMain
 * @Description: TODO
 * @Author: itwang@qq.com
 */
public class TestMain {
    public static void main(String[] args) {
        Thread user01 = new Thread(new MyRun("游客", 5));
        Thread user02 = new Thread(new MyRun("普通用户", 10));
        Thread user03 = new Thread(new MyRun("VIP用户", 50));

        user01.start();
        user02.start();
        user03.start();
    }
}
