import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static etc.Utils.echo;
import static etc.Utils.inCyclicDaemonThreads;
import static etc.Utils.inOtherThreads;
import static etc.Utils.sleep;

public class RwLockControl {
    static ReadWriteLock lock = new ReentrantReadWriteLock();

    public static void main(String... args) {
        inCyclicDaemonThreads(2, () -> {
            lock.readLock().lock();
            echo("Read start");
            sleep(3);
            echo("Read end");
            lock.readLock().unlock();
        });

        inOtherThreads(10, () -> {
            lock.writeLock().lock();
            echo("Write locked");
            sleep(2);
            echo("Doing my business!");
            lock.writeLock().unlock();

            sleep(2);
        });
    }
}
