package monads;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

public class Try<T> implements Monad<T> {
    private final T value;
    private final Exception[] errors;

    private Try(T value, Exception[] error) {
        this.value = value;
        this.errors = error;
    }

    public static <T> Try<T> of(T value) {
        return new Try<>(value, new Exception[0]);
    }

    public static <T> Try<T> toDo(Supplier<T> fun) {
        try {
            return new Try<>(fun.get(), null);
        } catch (Exception exp) {
            return new Try<>(null, new Exception[] {exp});
        }
    }

    public boolean isFailed() {
        return this.errors.length > 0;
    }

    @Override
    public <R> Try<R> map(Function<T, R> fun) {
        if (this.isFailed()) return new Try<>(null, this.errors);

        try {
            return new Try<>(fun.apply(this.value), null);
        } catch (Exception exp) {
            return new Try<>(null, new Exception[] {exp});
        }
    }

    public Try<T> tryToRestore(Supplier<T> fun) {
        if (! this.isFailed()) return this;

        try {
            return new Try<>(fun.get(), null);
        } catch (Exception exp) {
            Exception[] errors = Arrays.copyOf(this.errors, this.errors.length);
            errors[this.errors.length] = exp;

            return new Try<>(null, errors);
        }
    }

    public T orElseGet(Supplier<T> fun) {
        if (! this.isFailed()) return this.value;
        return fun.get();
    }

    public Optional<T> toOptional() {
        return Optional.ofNullable(this.value);
    }
}
