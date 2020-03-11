package atomic;

import java.util.concurrent.atomic.AtomicInteger;

import static etc.Utils.echo;
import static etc.Utils.inOtherThreads;

public class L3_AtomicIdGenerator {
    static AtomicInteger value = new AtomicInteger(1);

    public static void main(String... args) {
        inOtherThreads(10, () -> {
            int last = -1;
            for (int i = 0; i < 10_000; i++) {
                last = value.getAndIncrement();
            }

            echo("Last id is %d", last);
        });
    }
}
