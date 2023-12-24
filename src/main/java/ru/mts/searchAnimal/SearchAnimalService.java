package ru.mts.searchAnimal;

import ru.mts.Animal;

public interface SearchAnimalService {
    /**
     * Поиск животных, которые родились в високосный год
     * @param animals - массив животных
     * @return массив, состоящий из имен животных
     */
    String[] findLeapYearNames(Animal[] animals);

    /**
     * Поиск животных, возраст которых старше N лет
     * @param animals - массив животных
     * @param N - возраст, старше которого нужно найти животных
     * @return массив животных
     */
    Animal[] findOlderAnimal(Animal[] animals, int N);

    /**
     * Поиск дублирующихся животных и вывод их в консоль
     * @param animals - массив животных
     */
    void findDuplicate(Animal[] animals);
}
