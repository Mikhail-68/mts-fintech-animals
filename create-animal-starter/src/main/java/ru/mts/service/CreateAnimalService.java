package ru.mts.service;

import ru.mts.entity.Animal;
import ru.mts.exception.NegativeNumberException;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

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

        Map<String, List<Animal>> animalsMap = new ConcurrentHashMap<>();
        int i = 0;
        while (i < n) {
            Animal animal = createRandomAnimal();
            List<Animal> animalList = animalsMap.getOrDefault(animal.getClass().getSimpleName(), new CopyOnWriteArrayList<>());
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
     * @throws NegativeNumberException при отрицательном размере списка
     */
    List<Animal> createListRandomAnimals(int n);

    /**
     * Создание случайного животного
     *
     * @return случайное животное
     */
    Animal createRandomAnimal();

    /**
     * Вывод в консоль животного
     *
     * @param someAnimal животное
     */
    default void printCreatedAnimal(Animal someAnimal) {
        System.out.println("Создано животное: " + someAnimal.getName());
    }

}