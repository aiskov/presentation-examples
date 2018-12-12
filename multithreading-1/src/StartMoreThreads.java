import java.util.concurrent.TimeUnit;

public class StartMoreThreads {

    public static void main(String[] args) throws Exception {
        System.out.println("Hello World!");

        new Thread(new MorrrrreThreads()).start();

        TimeUnit.SECONDS.sleep(30);
    }

    static class MorrrrreThreads implements Runnable {

        @Override
        public void run() {
            System.out.println("Hello World! From other thread");
        }
    }
}
