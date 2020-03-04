import java.util.concurrent.TimeUnit;

import static etc.Utils.echo;
import static etc.Utils.sleep;

@SuppressWarnings("ALL")
public class L7_SyncWhatRight {
    public static void main(String[] args) {
        new Thread(new L7_SyncWhat.WaitThreads(1)).start();
        new Thread(new L7_SyncWhat.WaitThreads(2)).start();
        new Thread(new L7_SyncWhat.WaitThreads(3)).start();
    }

    public static synchronized void want(int id) {
        echo("[%s] I want to test!", id);
    }

    public static synchronized void test(int id) {
        echo("[%s] Reached!", id);
        sleep(5);
    }

    static class WaitThreads implements Runnable {
        final int id;

        WaitThreads(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            want(this.id);
            test(this.id);
        }
    }
}
