import beans.HiService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main1 {
    public static void main(String... args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");
        context.getBean(HiService.class).say();
    }
}
