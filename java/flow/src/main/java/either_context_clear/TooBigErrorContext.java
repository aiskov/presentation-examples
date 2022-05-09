package either_context_clear;

import lombok.Getter;

import static either_context_clear.ErrorTypes.TOO_BIG;

@Getter
class TooBigErrorContext extends ErrorContext {
    private final Integer value;
    private final Integer max;

    TooBigErrorContext(Integer value, Integer max) {
        super(TOO_BIG);

        this.value = value;
        this.max = max;
    }
}
