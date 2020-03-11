package locks;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import static etc.Utils.echo;
import static etc.Utils.inOtherThreads;
import static etc.Utils.sleep;

public class L5_CyclicBarrier {
    static CyclicBarrier barrier = new CyclicBarrier(5, () -> echo("Barrier action"));

    public static void main(String... args) {
        inOtherThreads(7, () -> {
            try {
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }

            echo("Doing my business!");
        });

        sleep(10);

        inOtherThreads(13, () -> {
            try {
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }

            sleep(1);
            echo("Doing my business!");
        });
    }
}
