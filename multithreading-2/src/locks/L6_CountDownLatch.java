package locks;

import java.util.concurrent.CountDownLatch;

import static etc.Utils.echo;
import static etc.Utils.inOtherThreads;
import static etc.Utils.sleep;

public class L6_CountDownLatch {
    static CountDownLatch countDownLatch = new CountDownLatch(5);

    public static void main(String... args) {
        inOtherThreads(7, () -> {
            try {
                echo("Calculating...");
                countDownLatch.countDown();

                if (countDownLatch.getCount() != 0) echo("Wait for minimal results achieved");
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            echo("Doing my business!");
        });

        sleep(10);

        inOtherThreads(13, () -> {
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            sleep(1);
            echo("Doing my business!");
        });
    }
}
