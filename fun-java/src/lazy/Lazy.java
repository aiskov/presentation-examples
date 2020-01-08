package lazy;

import monads.Monad;

import java.util.function.Function;
import java.util.function.Supplier;

public class Lazy<T> implements Monad<T> {
    private final Supplier<T> supplier;

    public Lazy(Supplier<T> supplier) {
        this.supplier = supplier;
    }

    @Override
    public <R> Lazy<R> map(Function<T, R> fun) {
        return new Lazy<>(() -> fun.apply(this.supplier.get()));
    }

    public T calculate() {
        return this.supplier.get();
    }
}
