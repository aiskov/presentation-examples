package p3;

import lombok.extern.slf4j.Slf4j;
import p3.infra.CannotLiveWithoutThisUselessAnnotation;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SelfCallService implements Service {

    @Override
    @CannotLiveWithoutThisUselessAnnotation
    public void doSomeWork() {
        log.info("I do the work");
        butByOtherMethod();
    }

    @Override
    @CannotLiveWithoutThisUselessAnnotation
    public void butByOtherMethod() {
        log.info("No! I do the work.");
    }
}
