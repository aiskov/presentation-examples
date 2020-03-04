@SuppressWarnings("ALL")
public class L2_IWannaWait {
    public static void main(String[] args) throws Exception {
        Thread.currentThread().wait(10_000);
    }
}
