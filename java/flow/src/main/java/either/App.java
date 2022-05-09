package either;

import io.vavr.control.Either;

import static either.ErrorTypes.TOO_BIG;
import static either.ErrorTypes.TOO_SMALL;
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
                Either<ErrorTypes, Void> result = controller.doImportantWork(input[0] + "", input[1]);
                if (result.isRight())
                    out.println("Success");
                else {
                    ErrorTypes type = result.getLeft();
                    if (type == TOO_BIG) {
                        out.println("Value is to big");
                        continue;
                    }

                    if (type == TOO_SMALL) {
                        out.println("Value is to small");
                        continue;
                    }

                    throw new IllegalStateException("Unknown type of error");
                }


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

        out.println("Something else");
    }
}
