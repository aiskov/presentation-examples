import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static etc.Utils.echo;
import static etc.Utils.sleep;
import static java.util.concurrent.TimeUnit.SECONDS;

public class DoItLater {
    static ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

    public static void main(String... args) {
        executor.schedule(() -> {
            sleep(1);
            echo("Done!");
        }, 5, SECONDS);

        executor.shutdown();
    }
}
