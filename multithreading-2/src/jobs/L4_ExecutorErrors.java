package jobs;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Stream;

import static etc.Utils.echo;
import static etc.Utils.sleep;

public class L4_ExecutorErrors {
    static ExecutorService executor = Executors.newSingleThreadExecutor();

    public static void main(String... args) {
        Future<Integer> task1 = executor.submit(() -> {
            sleep(3);
            echo("Ready to join 1!");
            return 1;
        });

        Future<Integer> task2 = executor.submit(() -> {
            sleep(3);
            throw new IllegalStateException("I cannot do that!");
        });

        int sum = Stream.of(task1, task2).mapToInt(future -> {
            try {
                return future.get();
            } catch (Exception exp) {
                throw new RuntimeException(exp);
            }
        }).sum();
        echo("Calculation done %d", sum);

        executor.shutdown();
    }
}
