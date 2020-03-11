package collections;

import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

import static etc.Utils.echo;
import static etc.Utils.inOtherThread;
import static etc.Utils.sleep;

public class L7_SkipListIteration {
    public static void main(String... args) {
        Set<Integer> data = new ConcurrentSkipListSet<>();

        data.add(1);
        data.add(2);
        data.add(3);

        inOtherThread(() -> {
            sleep(1);

            echo("Changing...");

            data.remove(2);
            data.add(4);

            echo("Changes applied");
        });

        echo("Get iterator");
        for (Integer i : data) {
            echo("Read %d", i);
            sleep(2);
        }
    }
}
