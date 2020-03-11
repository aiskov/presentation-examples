package atomic;

import static etc.Utils.echo;
import static etc.Utils.inOtherThreads;

public class L2_VolatileIdGenerator {
    static volatile int value = 1;

    public static void main(String... args) {
        inOtherThreads(10, () -> {
            int last = -1;
            for (int i = 0; i < 10_000; i++) {
                last = value++;
            }

            echo("Last id is %d", last);
        });
    }
}
