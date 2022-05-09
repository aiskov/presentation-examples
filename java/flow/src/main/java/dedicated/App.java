package dedicated;

import static java.lang.System.out;

public class App {
    public static void main(String... args) {
        MyController controller = new MyController();

        Integer[][] inputs = {
                {123, null},
                {123, -12},
                {123, 10},
                {123, 15},
                {0, 10}
        };

        for (Integer[] input : inputs) {
            try {
                controller.doImportantWork(input[0] + "", input[1]);
                out.println("Success");
            } catch (Exception e) {
                errorHandler(e);
            }
        }
    }

    static void errorHandler(Exception exp) {
        if (exp instanceof NullPointerException) {
            out.println("Null is not acceptable");
            return;
        }
        if (exp instanceof TooBigException) {
            out.println("Value is to big");
            return;
        }
        if (exp instanceof TooSmallException) {
            out.println("Value is to small");
            return;
        }

        out.println("Something else");
    }
}
