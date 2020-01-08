package functors;

import java.util.function.Function;

public interface Functor<T> {
    <R> Functor<R> map(Function<T, R> fun);
}