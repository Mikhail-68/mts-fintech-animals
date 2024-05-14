package ru.mts.service;

import ru.mts.entity.Animal;
import ru.mts.exception.IllegalArraySizeException;
import ru.mts.exception.NegativeNumberException;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface OperationsWithAnimalsService {
    /**
     * Поиск животных, которые родились в високосный год
     *
     * @param animals список животных
     * @return массив, состоящий из имен животных
     */
    Map<String, LocalDate> findLeapYearNames(List<Animal> animals);

    /**
     * Поиск животных, возраст которых старше age лет
     *
     * @param animals список животных
     * @param age     возраст, старше которого нужно найти животных
     * @return массив животных
     * @throws NegativeNumberException если параметр age меньше нуля
     */
    Map<Animal, Integer> findOlderAnimal(List<Animal> animals, int age);

    /**
     * Поиск дублирующихся животных
     *
     * @param animals список животных
     * @return массив животных
     */
    Map<String, List<Animal>> findDuplicate(List<Animal> animals);

    /**
     * Вычисляет средний возраст всех животных в списке
     *
     * @param animals список животных
     * @return Средний возраст всех животных
     */
    double findAverageAge(List<Animal> animals);

    /**
     * Находит животных, возраст которых больше 5 лет и стоимость которых больше средней стоимости всех животных
     *
     * @param animals список животных
     * @return Отсортированный по дате рождения список
     */
    List<Animal> findOldAndExpensive(List<Animal> animals);

    /**
     * т трех животных с самой низкой ценой
     *
     * @param animals список животных
     * @return Список имен, отсортированный в обратном алфавитном порядке
     * @throws IllegalArraySizeException если размер списка меньше трех
     * @throws NullPointerException      при animals равном null
     */
    List<String> findMinConstAnimals(List<Animal> animals) throws IllegalArraySizeException;
}
