import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

@Slf4j
public class Main {
    public static void main(String... args) {
        BeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("beans.xml"));

        log.info("Run application:");

//        beanFactory.getBean(HiService.class).say();
//        beanFactory.getBean(HiServiceOptions.class).say();
        beanFactory.getBean(HiServiceWithInit.class).say();
    }
}