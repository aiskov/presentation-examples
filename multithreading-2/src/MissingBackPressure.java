import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import static etc.Utils.echo;
import static etc.Utils.inCyclicDaemonThreads;
import static etc.Utils.sleep;

public class MissingBackPressure {
    public static void main(String... args) {
        Queue<long[]> data = new ConcurrentLinkedQueue<>();

        inCyclicDaemonThreads(10, () -> {
            echo("More!");
            data.add(new long[100_000]);
        });

        sleep(1);
        while (true) {
            echo("Oh it's to much work - %d", data.size());
            if (data.poll() == null) break;
        }
        echo("I have nothing to do!");
    }
}
