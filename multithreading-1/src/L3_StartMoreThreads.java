import static etc.Utils.echo;
import static etc.Utils.sleep;

@SuppressWarnings("ALL")
public class L3_StartMoreThreads {

    public static void main(String[] args) {
        new Thread(new MorrrrreThreads()).start();

        sleep(30);
        echo("Thread %s finished!", Thread.currentThread().getId());
    }

    static class MorrrrreThreads implements Runnable {

        @Override
        public void run() {
            echo("Hi from other thread %s!", Thread.currentThread().getId());
        }
    }
}
