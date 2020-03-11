package jobs;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.Stream;

import static etc.Utils.echo;
import static etc.Utils.sleep;

public class L2_ForJoinExecutor {
    static ForkJoinPool pool = ForkJoinPool.commonPool();

    public static void main(String... args) {
        ForkJoinTask<Integer> task1 = pool.submit(() -> {
            sleep(3);
            echo("Ready to join 1!");
            return 1;
        });

        ForkJoinTask<Integer> task2 = pool.submit(() -> {
            sleep(2);
            echo("Ready to join! 2");
            return 2;
        });


        int sum = Stream.of(task1, task2).mapToInt(ForkJoinTask::join).sum();
        echo("Calculation done %d", sum);
    }
}
