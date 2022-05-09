package dedicated;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
class TooSmallException extends RuntimeException {
    private final Integer value;
    private final Integer min;
}
