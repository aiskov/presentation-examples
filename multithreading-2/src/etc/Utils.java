package etc;

import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.String.format;
import static java.util.concurrent.TimeUnit.SECONDS;

public class Utils {
    private static AtomicInteger ID = new AtomicInteger(1);

    public static void echo(String text) {
        System.out.println(Thread.currentThread().getName() + ": " + text);
    }

    public static void echo(String text, Object... args) {
        System.out.println(Thread.currentThread().getName() + ": " + format(text, args));
    }

    public static void sleep(long sec) {
        try {
            SECONDS.sleep(sec);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void inOtherThread(Runnable action) {
        new Thread(action).start();
    }

    public static void inOtherThreads(int qty, Runnable action) {
        String name = "T-" + ID.getAndIncrement() + "-";

        for (int i = 0; i < qty; i++) {
            new Thread(action, name + i).start();
        }
    }

    public static void inDaemonThread(Runnable action) {
        Thread thread = new Thread(action);
        thread.setDaemon(true);
        thread.start();
    }

    public static void inCyclicDaemonThread(Runnable action) {
        inDaemonThread(() -> {
            while (true) action.run();
        });
    }


    public static void inDaemonThreads(int qty, Runnable action) {
        String name = "D-" + ID.getAndIncrement() + "-";

        for (int i = 0; i < qty; i++) {
            Thread thread = new Thread(action, name + i);
            thread.setDaemon(true);
            thread.start();
        }
    }

    public static void inCyclicDaemonThreads(int qty, Runnable action) {
        inDaemonThreads(qty, () -> {
            while (true) action.run();
        });
    }
}
