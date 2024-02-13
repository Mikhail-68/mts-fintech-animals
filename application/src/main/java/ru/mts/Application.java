package ru.mts;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.mts.config.ConfigurationApp;
import ru.mts.repository.AnimalsRepositoryImpl;

import java.util.Arrays;

public class Application {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(ConfigurationApp.class);

        System.out.println(Arrays.deepToString(context.getBeanDefinitionNames()));

        AnimalsRepositoryImpl repo = (AnimalsRepositoryImpl) context.getBean("animalsRepositoryImpl");
        System.out.println(Arrays.deepToString(repo.getAnimals()));

        System.out.println(repo.findDuplicate());
        System.out.println(Arrays.deepToString(repo.findLeapYearNames()));
        System.out.println(Arrays.deepToString(repo.findOlderAnimal(10)));

    }
}