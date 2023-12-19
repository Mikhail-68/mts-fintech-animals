package ru.mts.createAnimal;

import ru.mts.*;

import java.util.Random;

public interface CreateAnimalService {
    default void create10Animal() {
        int i = 0;
        while (i < 10) {
            printCreatedAnimal(createRandomAnimal());
            i++;
        }
    }

    default AbstractAnimal createRandomAnimal() {
        int countAnimals = 4;
        AbstractAnimal someAnimal;
        int id = new Random().nextInt(countAnimals);
        switch (id) {
            case 0: someAnimal = new Wolf();
                someAnimal.setName("wolf");
                break;
            case 1: someAnimal = new Shark();
                someAnimal.setName("shark");
                break;
            case 2: someAnimal = new Dog();
                someAnimal.setName("dog");
                break;
            default: someAnimal = new Cat();
                someAnimal.setName("cat");
        }
        return someAnimal;
    }

    default void printCreatedAnimal(AbstractAnimal someAnimal) {
        System.out.println("Создано животное: " + someAnimal.getName());
    }
}
