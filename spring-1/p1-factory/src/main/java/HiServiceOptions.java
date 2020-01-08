import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class HiServiceOptions {
    private final String value;
    private List<Integer> numbers;

    public HiServiceOptions(String value) {
        log.info("{} service constructor.", this.getClass().getCanonicalName());
        this.value = value;
    }

    public void setNumbers(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public void say() {
        log.info("Hi! {} - {}", value, numbers.stream().map(Object::toString).collect(Collectors.joining(", ")));
    }
}
