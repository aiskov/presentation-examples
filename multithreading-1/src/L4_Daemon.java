import static etc.Utils.echo;
import static etc.Utils.sleep;

@SuppressWarnings("ALL")
public class L4_Daemon {

    public static void main(String[] args) {
        new Thread(new MorrrrreThreads()).start();

        sleep(15);
    }

    static class MorrrrreThreads implements Runnable {

        @Override
        public void run() {
            while (true) {
                echo("Hello World! From other thread");
                sleep(5);
            }
        }
    }
}
