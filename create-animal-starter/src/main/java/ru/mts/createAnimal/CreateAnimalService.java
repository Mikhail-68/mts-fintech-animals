package ru.mts.createAnimal;

import org.springframework.lang.NonNull;
import ru.mts.model.*;
import ru.mts.properties.AnimalsProperties;

import java.util.List;
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
        someAnimal = switch (id) {
            case 0 -> new Wolf();
            case 1 -> new Shark();
            case 2 -> new Dog();
            default -> new Cat();
        };
        Random random = new Random();
        List<String> namesList = getAnimalsProperties().getNames();
        someAnimal.setName(namesList.get(random.nextInt(namesList.size())));
        return someAnimal;
    }

    /**
     * Вывод в консоль животного
     * @param someAnimal - животное
     */
    default void printCreatedAnimal(Animal someAnimal) {
        System.out.println("Создано животное: " + someAnimal.getName());
    }

    AnimalsProperties getAnimalsProperties();
}
