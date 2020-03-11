package jobs;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static etc.Utils.echo;
import static etc.Utils.inCyclicDaemonThreads;
import static etc.Utils.sleep;

public class L8_BackPressureSaveMemory {
    public static void main(String... args) {
        BlockingQueue<long[]> data = new ArrayBlockingQueue<>(10);

        inCyclicDaemonThreads(3, () -> {
            if (data.offer(new long[100_000])) {
                echo("Size after add - %d", data.size());
            } else {
                echo("Unable to insert, queue is full!");
                sleep(1);
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
