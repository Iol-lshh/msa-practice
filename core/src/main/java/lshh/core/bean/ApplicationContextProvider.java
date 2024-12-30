package lshh.core.bean;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextProvider implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        applicationContext = ctx;
    }

    private static ApplicationContext applicationContext() {
        if(applicationContext == null) {
            throw new NullPointerException("applicationContext is null");
        }
        return applicationContext;
    }

    public static Object findBean(String beanName) {
        return applicationContext().getBean(beanName);
    }
    public static Object findBean(String beanName, Class<?> clazz) {
        return applicationContext().getBean(beanName, clazz);
    }
}

