package either_context_clear;

import lombok.Getter;

import static either_context_clear.ErrorTypes.TOO_SMALL;

@Getter
class TooSmallErrorContext extends ErrorContext {
    private final Integer value;
    private final Integer min;

    TooSmallErrorContext(Integer value, Integer min) {
        super(TOO_SMALL);

        this.value = value;
        this.min = min;
    }
}
