package functors;

import java.util.function.Function;

public class Operand<T> implements Functor<T> {
    private final T value;

    private Operand(T value) { this.value = value; }

    public static <T> Operand<T> of(T value) {
        return new Operand<>(value);
    }

    public <R> Operand<R> map(Function<T, R> f) {
        return new Operand<>(f.apply(value));
    }
}