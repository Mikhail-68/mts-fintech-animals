package ru.mts.config;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.EnableScheduling;
import ru.mts.createAnimal.CreateAnimalServiceImpl;
import ru.mts.properties.AnimalsProperties;
import ru.mts.repository.AnimalsRepositoryImpl;

@Configuration
//@ConditionalOnClass(Greeter.class)
@EnableConfigurationProperties(AnimalsProperties.class)
@EnableScheduling
public class ConfigurationApp {
    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    @ConditionalOnMissingBean
    public CreateAnimalServiceImpl createAnimalServiceImpl() {
        return new CreateAnimalServiceImpl();
    }

    @Bean
    @ConditionalOnMissingBean
    public AnimalsRepositoryImpl animalsRepositoryImpl(CreateAnimalServiceImpl createAnimalService) {
        AnimalsRepositoryImpl animalsRepository = new AnimalsRepositoryImpl(createAnimalService);
        animalsRepository.init();
        return animalsRepository;
    }
}
