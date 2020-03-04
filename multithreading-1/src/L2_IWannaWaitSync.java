@SuppressWarnings("ALL")
public class L2_IWannaWaitSync {
    public static void main(String[] args) throws Exception {
        Thread thread = Thread.currentThread();
        synchronized (thread) {
            thread.wait(10_000);
        }
    }
}
