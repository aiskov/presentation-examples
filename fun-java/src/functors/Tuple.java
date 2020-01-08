package functors;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Tuple<T> implements Functor<T> {
    private final List<T> values;

    private Tuple(List<T> values) {
        this.values = values;
    }

    public static <T> Tuple<T> of(List<T> values) {
        return new Tuple<>(values);
    }

    @Override
    public <R> Tuple<R> map(Function<T, R> fun) {
        List<R> result = new ArrayList<>(this.values.size());

        for (T value : this.values) {
            result.add(fun.apply(value));
        }

        return new Tuple<>(result);
    }
}
