import java.util.concurrent.TimeUnit;

public class Sync {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        new Thread(new WaitThreads(1)).start();
        new Thread(new WaitThreads(2)).start();
        new Thread(new WaitThreads(3)).start();
    }

    public static synchronized void test() {
        System.out.println("Reached!");

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class WaitThreads implements Runnable {

        final int id;

        WaitThreads(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            System.out.println("I want to test!");
            Sync.test();
        }
    }
}