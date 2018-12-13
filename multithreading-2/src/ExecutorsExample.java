import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static etc.Utils.echo;
import static etc.Utils.sleep;

public class ExecutorsExample {
    static Executor executor = Executors.newSingleThreadExecutor();

    public static void main(String... args) {
        executor.execute(() -> {
            sleep(1);
            echo("Done!");
        });

        sleep(2);
        echo("Finished");
    }
}
