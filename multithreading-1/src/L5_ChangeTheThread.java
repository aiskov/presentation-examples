import java.util.concurrent.TimeUnit;

@SuppressWarnings("ALL")
public class L5_ChangeTheThread {

    public static void main(String[] args) throws Exception {
        Thread thread = new Thread(() -> {});
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
}
