package p5;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import p5.infra.CannotLiveWithoutThisUselessAnnotation;
import p5.infra.WarmUp;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class SelfCallService implements Service {

    @PostConstruct
    @CannotLiveWithoutThisUselessAnnotation
    public void init() {
        log.info("Post construct init.");
    }

    @WarmUp
    @CannotLiveWithoutThisUselessAnnotation
    public void initItRight() {
        log.info("Post construct it right.");
    }

    @Override
    @CannotLiveWithoutThisUselessAnnotation
    public void doSomeWork() {
        log.info("I do the work");
    }
}
