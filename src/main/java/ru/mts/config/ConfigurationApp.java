package ru.mts.config;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.mts.createAnimal.CreateAnimalServiceImpl;

@Configuration
@ComponentScan("ru.mts")
public class ConfigurationApp {
    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public CreateAnimalServiceImpl createAnimalServiceImplBean() {
        return new CreateAnimalServiceImpl();
    }
}
