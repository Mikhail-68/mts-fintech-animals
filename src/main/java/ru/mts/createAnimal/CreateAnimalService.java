package ru.mts.createAnimal;

import ru.mts.model.*;

import java.util.Random;

public interface CreateAnimalService {

    /**
     * Создание 10 случайных животных
     * @return массив из 10 случайных животных
     */
    default Animal[] create10Animals() {
        Animal[] animals = new Animal[10];
        int i = 0;
        while (i < 10) {
            animals[i] = createRandomAnimal();
            i++;
        }
        return animals;
    }

    /**
     * Создание случайного животного
     * @return случайное животное
     */
    default Animal createRandomAnimal() {
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

    /**
     * Вывод в консоль животного
     * @param someAnimal - животное
     */
    default void printCreatedAnimal(Animal someAnimal) {
        System.out.println("Создано животное: " + someAnimal.getName());
    }
}
