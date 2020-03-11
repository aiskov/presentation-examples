package locks;

import java.util.concurrent.Phaser;

import static etc.Utils.echo;
import static etc.Utils.inOtherThreads;

public class L6_Phaser {
    static Phaser phaser = new Phaser(2);

    public static void main(String... args) {
        inOtherThreads(7, () -> {
            phaser.arriveAndAwaitAdvance();
            echo("Doing my business!");
        });
    }
}
