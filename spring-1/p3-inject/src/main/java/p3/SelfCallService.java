package p3;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import p3.infra.SomeSecret;

@Slf4j
@Component
public class SelfCallService implements Service {
    @SomeSecret
    private byte[] secret;

    @Override
    public void doSomeWork() {
        log.info("I do the work, my secret is '{}'", new String(secret));
    }
}
