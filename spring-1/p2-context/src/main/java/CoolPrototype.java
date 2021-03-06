import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CoolPrototype {
    public CoolPrototype() {
        log.info("{} service constructor.", this.getClass().getCanonicalName());
    }

    public void say() {
        log.info("Hi!");
    }

    public void init() {
        log.info("Initialized");
    }

    public void destroy() {
        log.info("Destroyed");
    }
}
