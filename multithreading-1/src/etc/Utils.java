package etc;

import static java.lang.String.format;
import static java.util.concurrent.TimeUnit.SECONDS;

public class Utils {
    public static void echo(String text, Object... args) {
        System.out.println(format(text, args));
    }

    public static void sleep(long sec) {
        try {
            SECONDS.sleep(sec);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
