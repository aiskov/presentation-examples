package p3.infra;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.util.ReflectionUtils.invokeMethod;

@Slf4j
@Component
public class ImitationOfReallyImportantWorkBeanPostProcessor implements BeanPostProcessor {
    private final Map<String, Class<?>> typeMap = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> type = bean.getClass();

        boolean found = false;

        for (Method method : type.getDeclaredMethods()) {
            if (found = method.isAnnotationPresent(CannotLiveWithoutThisUselessAnnotation.class)) break;
        }

        if (found) this.typeMap.put(beanName, type);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> type = this.typeMap.get(beanName);

        if (type == null) return bean;

        if (type.getInterfaces().length == 0) throw new IllegalStateException("CGI not supported LOOOOZZER!");

        return Proxy.newProxyInstance(type.getClassLoader(), type.getInterfaces(), (proxy, method, args) -> {
            log.info("Here it might be really important work, but here is I. ");
            return invokeMethod(method, bean, args);
        });
    }
}
