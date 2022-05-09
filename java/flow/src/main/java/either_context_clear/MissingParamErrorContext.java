package either_context_clear;

import io.vavr.control.Either;
import lombok.Getter;

import static either_context_clear.ErrorTypes.MISSING_PARAM;

@Getter
class MissingParamErrorContext extends ErrorContext {
    private final String name;

    MissingParamErrorContext(String name) {
        super(MISSING_PARAM);

        this.name = name;
    }

    static <T> Either<ErrorContext, T> requireParam(T param, String name) {
        return param == null ? Either.left(new MissingParamErrorContext(name)) : Either.right(param);
    }
}
