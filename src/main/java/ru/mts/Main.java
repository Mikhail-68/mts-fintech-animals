package ru.mts;

import ru.mts.createAnimal.CreateAnimalService;
import ru.mts.createAnimal.CreateAnimalServiceImpl;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.cost = BigDecimal.valueOf(1200.12545);
        System.out.println(dog.getCost());

        CreateAnimalServiceImpl service = new CreateAnimalServiceImpl();
        service.create10Animal();
        service.create10Animal(4);
        new CreateAnimalService() { }.create10Animal();

    }
}