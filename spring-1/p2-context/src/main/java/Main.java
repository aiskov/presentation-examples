import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Slf4j
public class Main {
    public static void main(String... args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");

        log.info("Run application:");

        applicationContext.getBean(CoolSingleton.class).say();
        applicationContext.getBean(CoolSingleton.class).say();

        applicationContext.getBean(CoolPrototype.class).say();
        applicationContext.getBean(CoolPrototype.class).say();

        applicationContext.close();
    }
}