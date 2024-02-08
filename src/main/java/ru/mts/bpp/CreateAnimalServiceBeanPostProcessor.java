package ru.mts.bpp;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;
import ru.mts.createAnimal.AnimalType;
import ru.mts.createAnimal.CreateAnimalServiceImpl;

import java.util.Random;

@Configuration
public class CreateAnimalServiceBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if(bean.getClass() == CreateAnimalServiceImpl.class) {
            CreateAnimalServiceImpl animalService = (CreateAnimalServiceImpl) bean;
            AnimalType[] animalTypes = AnimalType.values();
            animalService.setAnimalType(animalTypes[new Random().nextInt(0, animalTypes.length)]);
        }
        return bean;
    }
}
