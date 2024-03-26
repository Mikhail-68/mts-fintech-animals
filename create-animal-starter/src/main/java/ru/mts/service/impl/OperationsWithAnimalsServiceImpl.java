package ru.mts.service.impl;

import org.springframework.stereotype.Repository;
import ru.mts.exception.IllegalArraySizeException;
import ru.mts.exception.NegativeNumberException;
import ru.mts.model.Animal;
import ru.mts.service.OperationsWithAnimalsService;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@Repository
public class OperationsWithAnimalsServiceImpl implements OperationsWithAnimalsService {

    @Override
    public Map<String, LocalDate> findLeapYearNames(List<Animal> animals) {
        if (animals == null) return Collections.emptyMap();
        return animals.stream()
                .filter(animal -> Objects.nonNull(animal) && animal.getBirthdate().isLeapYear())
                .collect(Collectors.toConcurrentMap(
                        animal -> animal.getClass().getSimpleName() + " " + animal.getName(),
                        Animal::getBirthdate,
                        (x, y) -> y
                ));
    }

    @Override
    public Map<Animal, Integer> findOlderAnimal(List<Animal> animals, int age) {
        if (animals == null) return Collections.emptyMap();
        if (age < 0) throw new NegativeNumberException(age);

        Map<Animal, Integer> resMap = animals.stream()
                .filter(animal -> Objects.nonNull(animal) && LocalDate.now().compareTo(animal.getBirthdate().plusYears(age)) > 0)
                .collect(Collectors.toConcurrentMap(
                        animal -> animal,
                        animal -> Period.between(animal.getBirthdate(), LocalDate.now()).getYears(),
                        (x, y) -> y
                ));
        if (!resMap.isEmpty()) return resMap;

        return animals.stream()
                .filter(Objects::nonNull)
                .sorted(Comparator.comparing(Animal::getBirthdate).reversed())
                .limit(1)
                .collect(Collectors.toConcurrentMap(
                        animal -> animal,
                        animal -> Period.between(animal.getBirthdate(), LocalDate.now()).getYears(),
                        (x, y) -> y
                ));
    }

    @Override
    public Map<String, List<Animal>> findDuplicate(List<Animal> animals) {
        if (animals == null) return Collections.emptyMap();

        return animals.stream()
                .filter(Objects::nonNull)
                .filter(animal -> Collections.frequency(animals, animal) > 1)
                .distinct()
                .collect(Collectors.groupingByConcurrent(
                        animal -> animal.getClass().getSimpleName(),
                        Collectors.collectingAndThen(Collectors.toList(), CopyOnWriteArrayList::new)));
    }

    @Override
    public double findAverageAge(List<Animal> animals) {
        if (animals == null) return 0;
        return animals.stream()
                .filter(Objects::nonNull)
                .mapToInt(animal -> Period.between(animal.getBirthdate(), LocalDate.now()).getYears())
                .average().orElse(0);
    }

    @Override
    public List<Animal> findOldAndExpensive(List<Animal> animals) {
        if (animals == null) return Collections.emptyList();
        double averageCost = animals.stream()
                .filter(Objects::nonNull)
                .mapToDouble(animal -> animal.getCost().doubleValue())
                .average().orElse(0);
        return animals.stream()
                .filter(Objects::nonNull)
                .filter(animal -> Period.between(animal.getBirthdate(), LocalDate.now()).getYears() > 5)
                .filter(animal -> animal.getCost().doubleValue() > averageCost)
                .sorted(Comparator.comparing(Animal::getBirthdate))
                .collect(Collectors.collectingAndThen(Collectors.toList(), CopyOnWriteArrayList::new));
    }

    @Override
    public List<String> findMinConstAnimals(List<Animal> animals) throws IllegalArraySizeException {
        Objects.requireNonNull(animals);
        if (animals.size() < 3) throw new IllegalArraySizeException("Размер массива должен быть равен или больше 3");
        return animals.stream()
                .filter(Objects::nonNull)
                .sorted(Comparator.comparing(Animal::getCost))
                .limit(3)
                .map(Animal::getName)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.collectingAndThen(Collectors.toList(), CopyOnWriteArrayList::new));
    }
}
