package ru.mts;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.EnableScheduling;
import ru.mts.createAnimal.CreateAnimalService;
import ru.mts.createAnimal.CreateAnimalServiceImpl;
import ru.mts.properties.AnimalsProperties;

@SpringBootConfiguration
@EnableConfigurationProperties(AnimalsProperties.class)
@EnableScheduling
public class Starter {
//    @Bean
//    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
//    @ConditionalOnMissingBean
//    public CreateAnimalService createAnimalServiceImpl() {
//        return new CreateAnimalServiceImpl();
//    }
}
