import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static etc.Utils.echo;
import static etc.Utils.inOtherThreads;
import static etc.Utils.sleep;

public class LockControl {
    static Lock value = new ReentrantLock(false);

    public static void main(String... args) {
        inOtherThreads(10, () -> {
            value.lock();
            sleep(1);
            echo("Doing my business!");
            value.unlock();
        });
    }
}
