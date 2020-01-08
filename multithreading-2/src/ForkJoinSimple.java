import java.util.concurrent.ForkJoinTask;
import java.util.stream.Stream;

import static etc.Utils.echo;
import static etc.Utils.sleep;

public class ForkJoinSimple {
    public static void main(String... args) {
        ForkJoinTask<Integer> task1 = ForkJoinTask.adapt(() -> {
            sleep(3);
            echo("Ready to join 1!");
            return 1;
        }).fork();

        ForkJoinTask<Integer> task2 = ForkJoinTask.adapt(() -> {
            sleep(2);
            echo("Ready to join! 2");
            return 2;
        }).fork();
        
        int sum = Stream.of(task1, task2).mapToInt(ForkJoinTask::join).sum();
        echo("Calculation done %d", sum);
    }
}
