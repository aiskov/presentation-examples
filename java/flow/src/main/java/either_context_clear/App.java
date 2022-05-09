package either_context_clear;

import io.vavr.control.Either;
import io.vavr.control.Try;

import java.io.ByteArrayInputStream;

import static either_context_clear.ErrorTypes.AGGREGATE_NOT_FOUND;
import static either_context_clear.ErrorTypes.MISSING_PARAM;
import static either_context_clear.ErrorTypes.TOO_BIG;
import static either_context_clear.ErrorTypes.TOO_SMALL;
import static java.lang.System.out;

public class App {
    public static void main(String... args) {
        MyController controller = new MyController();

        Integer[][] integers = {
                {123, null}, // Null pointer
                {123, -12},
                {123, 10},
                {123, 15},
                {0, 10} // Illegal state
        };

        for (Integer[] input : integers) {
            try {
                Either<ErrorContext, Void> result = controller.doImportantWork(input[0] + "", input[1]);
                if (result.isRight()) {
                    out.println("Success");
                } else {
                    ErrorContext type = result.getLeft();
                    if (type.getType() == TOO_BIG) {
                        out.println("Value is to big");
                        continue;
                    }

                    if (type.getType() == TOO_SMALL) {
                        out.println("Value is to small");
                        continue;
                    }

                    if (type.getType() == MISSING_PARAM) {
                        out.println("Null is not acceptable");
                        continue;
                    }

                    if (type.getType() == AGGREGATE_NOT_FOUND) {
                        out.println("Aggregate nto found");
                        continue;
                    }

                    out.println("Unknown type of error");
                }
            } catch (Exception e) {
                out.println("Unknown type of error");
            }
        }
    }
}
