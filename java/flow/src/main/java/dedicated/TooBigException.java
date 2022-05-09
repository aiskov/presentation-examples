package dedicated;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
class TooBigException extends RuntimeException {
    private final Integer value;
    private final Integer max;
}
