import java.util.concurrent.TimeUnit;

public class SyncSingleton {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        new Thread(new WaitThreads(1)).start();
        new Thread(new WaitThreads(2)).start();
        new Thread(new WaitThreads(3)).start();
    }

    static class WaitThreads implements Runnable {

        final int id;

        WaitThreads(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            System.out.println("I want to test!");
            Foo.instance().test();
        }
    }

    static class Foo {
        private static Foo INSTANCE = null;

        public static Foo instance() {
            if (INSTANCE == null) {
                synchronized (Foo.class) {
                    if (INSTANCE == null) {
                        INSTANCE = new Foo();
                    }
                }
            }

            return INSTANCE;
        }
        public void test() {
            System.out.println("Reached!");
        }
    }

}
