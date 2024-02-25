package ru.mts.repository;

import ru.mts.model.Animal;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

public interface AnimalsRepository {
    /**
     * Поиск животных, которые родились в високосный год
     * @return массив, состоящий из имен животных
     */
    Map<String, LocalDate> findLeapYearNames();

    /**
     * Поиск животных, возраст которых старше age лет
     * @param age - возраст, старше которого нужно найти животных
     * @return массив животных
     */
    Map<Animal, Integer> findOlderAnimal(int age);

    /**
     * Поиск дублирующихся животных
     * @return массив животных
     */
    Map<String, Integer> findDuplicate();

    /**
     * Вывод дублирующихся животных в консоль
     */
    void printDuplicate();
}
