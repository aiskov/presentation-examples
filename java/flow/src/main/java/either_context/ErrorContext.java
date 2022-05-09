package either_context;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
abstract class ErrorContext {
    private final ErrorTypes type;
}
