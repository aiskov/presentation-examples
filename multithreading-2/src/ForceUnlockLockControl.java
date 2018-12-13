import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static etc.Utils.echo;
import static etc.Utils.inOtherThreads;
import static etc.Utils.sleep;
import static java.util.concurrent.TimeUnit.SECONDS;

public class ForceUnlockLockControl {
    static Lock lock = new ReentrantLock();

    public static void main(String... args) {
        inOtherThreads(10, () -> {
            try {
                if (lock.tryLock(3, SECONDS)) {
                    sleep(1);
                    echo("Doing my business!");
                    lock.unlock();
                } else {
                    echo("Timeout!");
                    lock.unlock();
                    echo("Forced!");

                    lock.lock();
                    sleep(1);
                    echo("Done");
                    lock.unlock();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
