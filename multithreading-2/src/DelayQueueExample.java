import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

import static etc.Utils.echo;
import static etc.Utils.sleep;

public class DelayQueueExample {
    public static void main(String... args) {
        DelayQueue<DelayedImp> data = new DelayQueue<>();

        data.put(new DelayedImp(1));
        data.put(new DelayedImp(2));
        data.put(new DelayedImp(3));

        for (int i = 0; i < 3;) {
            echo("Try to poll");

            if (data.poll() == null) {
                echo("Nothing");
                sleep(1);
                continue;
            }

            echo("Obtained");
            i++;
        }


    }

    static class DelayedImp implements Delayed {
        int value;

        DelayedImp(int value) {
            this.value = value;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return value--;
        }

        @Override
        public int compareTo(Delayed o) {
            return 0;
        }
    }
}
