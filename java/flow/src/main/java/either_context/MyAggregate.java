package either_context;

import io.vavr.control.Either;

class MyAggregate {
    Either<ErrorContext, Void> doImportantWork(Integer value) {
        if (value == null)
            throw new NullPointerException();

        if (value < 0)
            return Either.left(new TooSmallErrorContext(value, 0));

        if (value > 10)
            return Either.left(new TooBigErrorContext(value, 10));

        return Either.right(null);
    }
}
