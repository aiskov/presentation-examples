package either_context_clear;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
abstract class ErrorContext {
    private final ErrorTypes type;
}
