package ru.mts;

import org.springframework.stereotype.Component;
import ru.mts.repository.AnimalsRepositoryImpl;

import java.util.Arrays;

@Component
public class Example {
    public static AnimalsRepositoryImpl repository;

    public Example(AnimalsRepositoryImpl repository ){
        Example.repository = repository;

        System.out.println("repo init constr");
        System.out.println(Arrays.toString(repository.getAnimals()));
    }

}
