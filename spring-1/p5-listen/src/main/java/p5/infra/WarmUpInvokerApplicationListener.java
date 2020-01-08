package p5.infra;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

import static java.util.Arrays.stream;
import static org.springframework.util.ReflectionUtils.findMethod;
import static org.springframework.util.ReflectionUtils.invokeMethod;

@Component
@RequiredArgsConstructor
public class WarmUpInvokerApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

    private final ConfigurableListableBeanFactory factory;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ApplicationContext applicationContext = event.getApplicationContext();

        stream(applicationContext.getBeanDefinitionNames())
                .forEach(beanName -> {
                    BeanDefinition definition = factory.getBeanDefinition(beanName);
                    if (! definition.isSingleton()) return;

                    String beanClassName = definition.getBeanClassName();
                    if (beanClassName == null) return;

                    try {
                        Class<?> type = Class.forName(beanClassName);

                        stream(type.getDeclaredMethods())
                                .filter(method -> method.isAnnotationPresent(WarmUp.class))
                                .forEach(method -> {
                                    Object bean = applicationContext.getBean(beanName);

                                    Method wrappedMethod = findMethod(bean.getClass(), method.getName(), method.getParameterTypes());
                                    if (wrappedMethod == null) throw new IllegalStateException("oh oh");

                                    invokeMethod(wrappedMethod, bean);
                                });
                    } catch (ClassNotFoundException e) {
                        throw new IllegalStateException(e);
                    }
                });
    }
}
