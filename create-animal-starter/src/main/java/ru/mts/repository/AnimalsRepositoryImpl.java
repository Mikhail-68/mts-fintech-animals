package ru.mts.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.mts.createAnimal.CreateAnimalService;
import ru.mts.model.Animal;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class AnimalsRepositoryImpl implements AnimalsRepository {

    private final CreateAnimalService createAnimalService;
    private List<Animal> animals;

    @Autowired
    public AnimalsRepositoryImpl(CreateAnimalService createAnimalService) {
        this.createAnimalService = createAnimalService;
    }

    public List<Animal> getAnimals() {
        return new ArrayList<>(animals);
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }

    @PostConstruct
    public void init() {
        animals = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            animals.add(createAnimalService.createRandomAnimal());
        }
    }

    @Override
    public Map<String, LocalDate> findLeapYearNames() {
        if (animals == null) return Collections.emptyMap();
        return animals.stream()
                .filter(animal -> Objects.nonNull(animal) && animal.getBirthdate().isLeapYear())
                .collect(Collectors.toMap(
                        animal -> animal.getClass().getSimpleName() + " " + animal.getName(),
                        Animal::getBirthdate,
                        (x, y) -> y
                ));
    }

    @Override
    public Map<Animal, Integer> findOlderAnimal(int age) {
        if (animals == null) return Collections.emptyMap();

        Map<Animal, Integer> resMap = animals.stream()
                .filter(animal -> Objects.nonNull(animal) && LocalDate.now().compareTo(animal.getBirthdate().plusYears(age)) > 0)
                .collect(Collectors.toMap(
                        animal -> animal,
                        animal -> Period.between(animal.getBirthdate(), LocalDate.now()).getYears(),
                        (x, y) -> y
                ));
        if (!resMap.isEmpty()) return resMap;

        return animals.stream()
                .filter(Objects::nonNull)
                .sorted(Comparator.comparing(Animal::getBirthdate).reversed())
                .limit(1)
                .collect(Collectors.toMap(
                        animal -> animal,
                        animal -> Period.between(animal.getBirthdate(), LocalDate.now()).getYears(),
                        (x, y) -> y
                ));
    }

    @Override
    public Map<String, List<Animal>> findDuplicate() {
        if (animals == null) return Collections.emptyMap();

        return animals.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(animal -> animal.getClass().getSimpleName()));
    }

    @Override
    public double findAverageAge() {
        if (animals == null) return 0;
        return animals.stream()
                .filter(Objects::nonNull)
                .mapToInt(animal -> Period.between(animal.getBirthdate(), LocalDate.now()).getYears())
                .average().orElse(0);
    }

    @Override
    public List<Animal> findOldAndExpensive() {
        if(animals == null) return Collections.emptyList();
        double averageCost = animals.stream()
                .filter(Objects::nonNull)
                .mapToDouble(animal -> animal.getCost().doubleValue())
                .average().orElse(0);
        return animals.stream()
                .filter(Objects::nonNull)
                .filter(animal -> Period.between(animal.getBirthdate(), LocalDate.now()).getYears() > 5)
                .filter(animal -> animal.getCost().doubleValue() > averageCost)
                .sorted(Comparator.comparing(Animal::getBirthdate))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findMinConstAnimals() {
        if (animals == null) return Collections.emptyList();
        return animals.stream()
                .filter(Objects::nonNull)
                .sorted(Comparator.comparing(Animal::getCost))
                .limit(3)
                .map(Animal::getName)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }
}
