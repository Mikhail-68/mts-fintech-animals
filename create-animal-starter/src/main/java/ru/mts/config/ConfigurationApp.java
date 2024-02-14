package ru.mts.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.EnableScheduling;
import ru.mts.createAnimal.CreateAnimalServiceImpl;
import ru.mts.properties.AnimalsProperties;
import ru.mts.repository.AnimalsRepositoryImpl;

import java.util.Random;

@Configuration
//@ConditionalOnClass(Greeter.class)
//@EnableConfigurationProperties(AnimalsProperties.class)
@EnableScheduling
public class ConfigurationApp {
    @Autowired
    private AnimalsProperties animalsProperties;

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
        animalsRepository.init(animalsProperties.names.get(
                new Random().nextInt(animalsProperties.names.size())
        ));
        return animalsRepository;
    }
}
