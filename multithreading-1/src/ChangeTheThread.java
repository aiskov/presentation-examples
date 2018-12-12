import java.util.concurrent.TimeUnit;

public class ChangeTheThread {

    public static void main(String[] args) throws Exception {
        System.out.println("Hello World!");

        Thread thread = new Thread(new MorrrrreThreads());
        thread.start();


        thread.setName("!!!");
        thread.setPriority(1);
        thread.setDaemon(true);

        thread.getId();
        thread.getState();
        thread.getStackTrace();
        thread.getThreadGroup(); // ThreadGroup test = new ThreadGroup("Test"); new Thread(test ,new MyRunnable());

        thread.suspend();
        thread.resume();
        thread.stop();

        TimeUnit.SECONDS.sleep(30);
    }

    static class MorrrrreThreads implements Runnable {

        @Override
        public void run() {
            System.out.println("Hello World! From other thread");
        }
    }
}
