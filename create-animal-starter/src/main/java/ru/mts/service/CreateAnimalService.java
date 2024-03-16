package ru.mts.service;

import ru.mts.exception.NegativeNumberException;
import ru.mts.model.*;
import ru.mts.properties.AnimalsProperties;

import java.util.*;

public interface CreateAnimalService {

    /**
     * Создание Map из n случайных животных
     *
     * @param n количество животных
     * @return массив из n случайных животных
     * @throws NegativeNumberException если параметр n меньше нуля
     */
    default Map<String, List<Animal>> createMapRandomAnimals(int n) {
        if (n < 0) throw new NegativeNumberException(n);

        Map<String, List<Animal>> animalsMap = new HashMap<>();
        int i = 0;
        while (i < n) {
            Animal animal = createRandomAnimal();
            List<Animal> animalList = animalsMap.getOrDefault(animal.getClass().getSimpleName(), new ArrayList<>());
            animalList.add(animal);
            animalsMap.put(animal.getClass().getTypeName(), animalList);
            i++;
        }
        return animalsMap;
    }

    /**
     * Создание списка из n случайных животных
     *
     * @param n размер списка
     * @return список рандомных животных
     */
    default List<Animal> createListRandomAnimals(int n) {
        if (n < 0) throw new NegativeNumberException(n);
        List<Animal> animals = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            animals.add(createRandomAnimal());
        }
        return animals;
    }

    /**
     * Создание случайного животного
     *
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
     *
     * @param someAnimal животное
     */
    default void printCreatedAnimal(Animal someAnimal) {
        System.out.println("Создано животное: " + someAnimal.getName());
    }

    AnimalsProperties getAnimalsProperties();
}
