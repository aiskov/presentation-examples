import functors.Operand;
import functors.Tuple;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

public class WorkCopy {

    public static void main(String[] args) {
        Object data;
        try {
            data = mainRequest();
            // Some code
        } catch (IllegalStateException exp) {
            try {
                data = getFromCache();
            } catch (IllegalStateException e) {
                data = "<div>Oh nooooooo, </div>";
            }
        }

        Optional.of("").map(x -> x + "----");

        ///
        String mData = Try.tryToDo(WorkCopy::mainRequest)
                .map(String::length)
                .restore(WorkCopy::getFromCache)
                .map(Object::toString)
                .restore(() -> "")
                .toOptional()
                .orElse(null);
    }

    static class Try<T> {
        final T payload;
        final boolean failed;

        Try(T payload, boolean failed) {
            this.payload = payload;
            this.failed = failed;
        }

        static <T> Try<T> failed() {
            return new Try<>(null, true);
        }

        static <T> Try<T> tryToDo(Supplier<T> action) {
            try {
                return new Try<>(action.get(), false);
            } catch (RuntimeException exp) {
                return new Try<>(null, true);
            }
        }

        <O> Try<O> map(Function<T, O> function) {
            return failed ? failed() : tryToDo(() -> function.apply(this.payload));
        }

        Try<T> restore(Supplier<T> action) {
            return failed ? tryToDo(action) : this;
        }

        Optional<T> toOptional() {
            return Optional.ofNullable(payload);
        }
    }

    static String mainRequest() {
        throw new IllegalStateException();
        // return null;
    }

    static Integer getFromCache() {
        throw new IllegalStateException();
    }

    static class User {
        Order order;
    }

    static class Order {
        Invoice invoice;
    }

    static class Invoice {
        BigDecimal amount;
    }
}
