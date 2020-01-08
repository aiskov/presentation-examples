import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static etc.Utils.echo;
import static etc.Utils.sleep;

public class ExecutorsExample {
    static Executor executor = Executors.newFixedThreadPool(2);//newSingleThreadExecutor();

    public static void main(String... args) {
        executor.execute(() -> {
            sleep(1);
            echo("Done!");
        });
        executor.execute(() -> {
            sleep(1);
            echo("Done! 1");
        });

        sleep(2);
        echo("Finished");
    }
}
