package ru.mts.repository;

import ru.mts.model.Animal;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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
    Map<String, List<Animal>> findDuplicate();

    /**
     *
     * @return Средний возраст всех животных
     */
    double findAverageAge();

    /**
     * Находит животных, возраст которых больше 5 лет и стоимость которых больше средней стоимости всех животных
     * @return Отсортированный по дате рождения список
     */
    List<Animal> findOldAndExpensive();

    /**
     * Ищет трех животных с самой низкой ценой
     * @return Список имен, отсортированный в обратном алфавитном порядке
     */
    List<String> findMinConstAnimals();
}
