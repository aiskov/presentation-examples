package either_context_clear;

import lombok.Getter;

import static either_context_clear.ErrorTypes.AGGREGATE_NOT_FOUND;

@Getter
class AggregateNotFoundErrorContext extends ErrorContext {
    private final String name;
    private final String id;

    AggregateNotFoundErrorContext(String name, String id) {
        super(AGGREGATE_NOT_FOUND);

        this.name = name;
        this.id = id;
    }
}
