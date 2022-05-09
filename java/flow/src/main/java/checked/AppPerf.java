package checked;

import static java.lang.System.currentTimeMillis;
import static java.lang.System.out;

public class AppPerf {
    public static void main(String... args) {
        MyController controller = new MyController();

        String output = null;

        Integer[][] inputs = {
                {123, null},
                {123, -12},
                {123, 10},
                {123, 15},
                {0, 10}
        };

        int executions = 1_000_000_000 / inputs.length;

        long start = currentTimeMillis();
        for (int i = 0; i < executions; i++) {
            for (Integer[] input : inputs) {
                try {
                    controller.doImportantWork(input[0] + "", input[1]);
                    output = "Success";
                } catch (Exception e) {
                    output = errorHandler(e);
                }
            }
        }

        out.println("Finished in: " + (currentTimeMillis() - start));

        // To ensure that optimization will not remove unused assignments
        out.println(output);
    }

    static String errorHandler(Exception exp) {
        if (exp instanceof NullPointerException) {
            return "Null is not acceptable";
        }
        if (exp instanceof TooBigException) {
            return "Value is to big";
        }
        if (exp instanceof TooSmallException) {
            return "Value is to small";
        }

        return "Something else";
    }
}
