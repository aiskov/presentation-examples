import static etc.Utils.echo;

@SuppressWarnings("ALL")
public class L8_SyncSingleton {
    public static void main(String[] args) {
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
            echo("[%s] I want to test!", this.id);
            Foo.instance(this.id).test(this.id);
        }
    }

    static class Foo {
        private static Foo INSTANCE = null;

        public Foo(int id) {
            echo("[%s] Created!", id);
        }

        public static Foo instance(int id) {
            if (INSTANCE == null) {
                synchronized (Foo.class) {
                    if (INSTANCE == null) {
                        INSTANCE = new Foo(id);
                    }
                }
            }

            return INSTANCE;
        }

        public void test(int id) {
            echo("[%s] Reached!", id);
        }
    }
}
