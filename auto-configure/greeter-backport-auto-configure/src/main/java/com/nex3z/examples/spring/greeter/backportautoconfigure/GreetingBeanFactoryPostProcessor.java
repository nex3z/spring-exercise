package com.nex3z.examples.spring.greeter.backportautoconfigure;

import com.nex3z.examples.spring.greeter.GreetingApplicationRunner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.util.ClassUtils;

@Slf4j
public class GreetingBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        boolean hasClass = ClassUtils.isPresent("com.nex3z.examples.spring.greeter.GreetingApplicationRunner",
                GreetingBeanFactoryPostProcessor.class.getClassLoader());
        if (!hasClass) {
            log.info("postProcessBeanFactory(): class GreetingApplicationRunner not found");
            return;
        }
        if (beanFactory.containsBeanDefinition("greetingApplicationRunner")) {
            log.info("postProcessBeanFactory(): bean definition greetingApplicationRunner exists");
            return;
        }

        register(beanFactory);
    }

    private void register(ConfigurableListableBeanFactory factory) {
        if (factory instanceof BeanDefinitionRegistry) {
            GenericBeanDefinition definition = new GenericBeanDefinition();
            definition.setBeanClass(GreetingApplicationRunner.class);
            log.info("register(): registering bean definition");
            ((BeanDefinitionRegistry) factory).registerBeanDefinition("greetingApplicationRunner", definition);
        } else {
            log.info("register(): registering singleton");
            factory.registerSingleton("greetingApplicationRunner", new GreetingApplicationRunner());
        }
    }
}
