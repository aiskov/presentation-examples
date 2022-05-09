package either_context;

import io.vavr.control.Either;

import static either_context.ErrorTypes.TOO_BIG;
import static either_context.ErrorTypes.TOO_SMALL;
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
                    Either<ErrorContext, Void> result = controller.doImportantWork(input[0] + "", input[1]);
                    if (result.isRight()) {
                        output = "Success";
                    } else {
                        ErrorContext type = result.getLeft();
                        if (type.getType() == TOO_BIG) {
                            output = "Value is to big";
                            continue;
                        }

                        if (type.getType() == TOO_SMALL) {
                            output = "Value is to small";
                            continue;
                        }

                        throw new IllegalStateException("Unknown type of error");
                    }


                } catch (Exception e) {
                    output = errorHandler(e);
                }
            }
        }

        long finished = currentTimeMillis() - start;
        out.println("Finished in: " + finished);

        // To ensure that optimization will not remove unused assignments
        out.println(output);
    }

    static String errorHandler(Exception exp) {
        if (exp instanceof NullPointerException) {
            return "Null is not acceptable";
        }

        return "Something else";
    }
}
