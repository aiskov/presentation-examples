import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static etc.Utils.echo;
import static etc.Utils.inCyclicDaemonThreads;
import static etc.Utils.sleep;
import static java.util.concurrent.TimeUnit.SECONDS;

public class BackPressureWait {
    public static void main(String... args) {
        BlockingQueue<long[]> data = new ArrayBlockingQueue<>(10);

        inCyclicDaemonThreads(10, () -> {
            echo("More!");
            try {
                data.offer(new long[100_000], Long.MAX_VALUE, SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        sleep(1);
        while (true) {
            sleep(1);
            echo("Oh it's to much work - %d", data.size());
            if (data.poll() == null) break;
        }
        echo("I have nothing to do!");
    }
}
