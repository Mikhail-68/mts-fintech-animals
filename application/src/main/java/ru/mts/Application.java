package ru.mts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@EnableScheduling
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);

//        ApplicationContext context = new AnnotationConfigApplicationContext(ConfigurationApp.class);
//
//        System.out.println(Arrays.deepToString(context.getBeanDefinitionNames()));
//
//
//        AnimalsRepositoryImpl repo = (AnimalsRepositoryImpl) context.getBean("animalsRepositoryImpl");
//        System.out.println(Arrays.deepToString(repo.getAnimals()));
//
//        System.out.println(repo.findDuplicate());
//        System.out.println(Arrays.deepToString(repo.findLeapYearNames()));
//        System.out.println(Arrays.deepToString(repo.findOlderAnimal(10)));

    }
}