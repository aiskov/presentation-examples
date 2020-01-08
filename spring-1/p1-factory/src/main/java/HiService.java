import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HiService {
    public HiService() {
        log.info("{} service constructor.", this.getClass().getCanonicalName());
    }

    public void say() {
        log.info("Hi!");
    }
}
