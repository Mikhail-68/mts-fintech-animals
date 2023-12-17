package ru.mts.createAnimal;

import ru.mts.Dog;

public interface CreateAnimalService {
    default void create10Animal() {
        int i = 0;
        while (i < 10) {
            Dog dog = new Dog();
            printCreatedAnimal();
            i++;
        }
    }

    default void printCreatedAnimal() {
        System.out.println("Животное создано");
    }
}
