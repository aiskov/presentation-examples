package checked;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
class TooSmallException extends Exception {
    private final Integer value;
    private final Integer min;
}
