public class IWannaWaitSync {

    public static void main(String[] args) throws Exception {
        System.out.println("Hello World!");

        String s = "Should it work?";
        synchronized (s) {
            s.wait(10_000);
        }
    }
}
