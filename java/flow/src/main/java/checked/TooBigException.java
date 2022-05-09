package checked;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
class TooBigException extends Exception {
    private final Integer value;
    private final Integer max;
}
