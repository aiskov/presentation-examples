package either;

import io.vavr.control.Either;
import lombok.NonNull;

import static either.ErrorTypes.TOO_BIG;
import static either.ErrorTypes.TOO_SMALL;

class MyAggregate {
    Either<ErrorTypes, Void> doImportantWork(@NonNull Integer value) {
        if (value < 0)
            return Either.left(TOO_SMALL);

        if (value > 10)
            return Either.left(TOO_BIG);

        return Either.right(null);
    }
}
