package either_context_clear;

import io.vavr.control.Either;

import static either_context_clear.MissingParamErrorContext.requireParam;

class MyAggregate {
    Either<ErrorContext, Void> doImportantWork(Integer value) {
        return requireParam(value, "value")
                .flatMap(this::validate);
    }

    Either<ErrorContext, Void> validate(Integer value) {
        if (value < 0)
            return Either.left(new TooSmallErrorContext(value, 0));

        if (value > 10)
            return Either.left(new TooBigErrorContext(value, 10));

        return Either.right(null);
    }
}
