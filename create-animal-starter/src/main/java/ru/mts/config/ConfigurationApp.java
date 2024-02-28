package ru.mts.config;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.EnableScheduling;
import ru.mts.createAnimal.CreateAnimalService;
import ru.mts.createAnimal.CreateAnimalServiceImpl;
import ru.mts.properties.AnimalsProperties;

@Configuration
//@ConditionalOnClass(Greeter.class)
public class ConfigurationApp {

}
