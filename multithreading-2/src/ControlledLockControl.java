import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static etc.Utils.echo;
import static etc.Utils.inOtherThreads;
import static etc.Utils.sleep;
import static java.util.concurrent.TimeUnit.SECONDS;

public class ControlledLockControl {
    static Lock value = new ReentrantLock(true);

    public static void main(String... args) {
        inOtherThreads(10, () -> {
            try {
                if (value.tryLock(3, SECONDS)) {
                    sleep(1);
                    echo("Doing my business!");
                    value.unlock();
                } else {
                    echo("Timeout!");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
