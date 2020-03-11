package jobs;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static etc.Utils.echo;
import static etc.Utils.sleep;

public class L6_ExecutorsMustDie {
    static ExecutorService executor = Executors.newSingleThreadExecutor();

    public static void main(String... args) {
        executor.execute(() -> {
            sleep(1);
            echo("Done!");
        });

        executor.shutdown();
    }
}
