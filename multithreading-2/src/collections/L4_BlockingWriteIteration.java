package collections;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static etc.Utils.echo;
import static etc.Utils.inOtherThread;
import static etc.Utils.sleep;

public class L4_BlockingWriteIteration {
    public static void main(String... args) {
        BlockingQueue<Integer> data = new LinkedBlockingQueue<>();

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
