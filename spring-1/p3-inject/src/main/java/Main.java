import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import p3.Service;

@Slf4j
public class Main {
    public static void main(String... args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");

        log.info("Run application:");

        Service service = applicationContext.getBean(Service.class);
        service.doSomeWork();
    }
}