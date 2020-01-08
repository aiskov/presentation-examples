package p3.infra;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

import static org.springframework.util.ReflectionUtils.makeAccessible;
import static org.springframework.util.ReflectionUtils.setField;

@Slf4j
@Component
public class SomeSecretBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        // TODO: Look recursively
        for (Field field : bean.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(SomeSecret.class)) {
                makeAccessible(field);
                setField(field, bean, "I'm a strong secret".getBytes());
            }
        }

        return bean;
    }
}
