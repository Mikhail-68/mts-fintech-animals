package ru.mts.repository;

import ru.mts.model.Animal;

import java.util.Set;

public interface AnimalsRepository {
    /**
     * Поиск животных, которые родились в високосный год
     * @return массив, состоящий из имен животных
     */
    String[] findLeapYearNames();

    /**
     * Поиск животных, возраст которых старше age лет
     * @param age - возраст, старше которого нужно найти животных
     * @return массив животных
     */
    Animal[] findOlderAnimal(int age);

    /**
     * Поиск дублирующихся животных
     * @return массив животных
     */
    Set<Animal> findDuplicate();

    /**
     * Вывод дублирующихся животных в консоль
     */
    void printDuplicate();
}
