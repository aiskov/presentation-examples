import java.util.concurrent.TimeUnit;

public class WaitAndNotifyThread {

    static String target = "TARGET";

    public static void main(String[] args) throws Exception {
        System.out.println("Hello World!");

        new Thread(new WaitThreads(1)).start();
        new Thread(new WaitThreads(2)).start();
        new Thread(new WaitThreads(3)).start();

        TimeUnit.SECONDS.sleep(1);

        synchronized (target) {
            System.out.println("Let's unblock one");
            target.notify();

            TimeUnit.SECONDS.sleep(1);

            System.out.println("Let's unblock all");
            target.notifyAll();
        }
    }

    static class WaitThreads implements Runnable {

        final int id;

        WaitThreads(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            try {
                synchronized (target) {
                    target.wait();
                }
            } catch (Exception exp) {
                throw new RuntimeException(exp);
            }
            System.out.println("Waited " + id);
        }
    }
}
