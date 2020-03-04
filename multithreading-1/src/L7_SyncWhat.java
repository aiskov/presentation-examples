import static etc.Utils.echo;
import static etc.Utils.sleep;

@SuppressWarnings("ALL")
public class L7_SyncWhat {
    public static void main(String[] args) {
        new Thread(new WaitThreads(1)).start();
        new Thread(new WaitThreads(2)).start();
        new Thread(new WaitThreads(3)).start();
    }

    public static void want(int id) {
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
            L7_SyncWhat.want(this.id);
            L7_SyncWhat.test(this.id);
        }
    }
}
