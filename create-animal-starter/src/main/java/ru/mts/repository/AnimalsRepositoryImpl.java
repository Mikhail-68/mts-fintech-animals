package ru.mts.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.mts.createAnimal.CreateAnimalService;
import ru.mts.model.Animal;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

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

    @PostConstruct
    public void init() {
        animals = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            animals.add(createAnimalService.createRandomAnimal());
        }
    }

    @Override
    public Map<String, LocalDate> findLeapYearNames() {
        if (animals == null || animals.isEmpty()) return Collections.emptyMap();

        Map<String, LocalDate> resAnimals = new HashMap<>();

        for (Animal animal : animals) {
            if (animal == null) continue;
            if (animal.getBirthdate().isLeapYear()) {
                String typeAndName = animal.getClass().getSimpleName() + " " + animal.getName();
                resAnimals.put(typeAndName, animal.getBirthdate());
            }
        }
        return resAnimals;
    }

    @Override
    public Map<Animal, Integer> findOlderAnimal(int age) {
        if (animals == null) return Collections.emptyMap();

        Map<Animal, Integer> olderAnimals = new HashMap<>();
        Animal seniorAnimal = null;

        for (Animal animal : animals) {
            if (animal == null || animal.getBirthdate() == null) continue;

            if(seniorAnimal == null || animal.getBirthdate().isBefore(seniorAnimal.getBirthdate())) {
                seniorAnimal = animal;
            }

            if (LocalDate.now().compareTo(animal.getBirthdate().plusYears(age)) > 0) {
                olderAnimals.put(animal, Period.between(animal.getBirthdate(), LocalDate.now()).getYears());
            }
        }
        if(olderAnimals.isEmpty() && seniorAnimal != null) olderAnimals.put(seniorAnimal, Period.between(seniorAnimal.getBirthdate(), LocalDate.now()).getYears());
        return olderAnimals;
    }

    @Override
    public Map<String, Integer> findDuplicate() {
        if (animals == null) return Collections.emptyMap();

        Map<String, Integer> resMap = new HashMap<>();
        for (Animal animal : animals) {
            if(animal == null) continue;
            String typeName = animal.getClass().getSimpleName();
            resMap.put(typeName, resMap.getOrDefault(typeName, 0) + 1);
        }
        return resMap;
    }

    @Override
    public void printDuplicate() {
        Map<String, Integer> duplicateAnimals = findDuplicate();
        if (duplicateAnimals.isEmpty()) return;
        System.out.println(duplicateAnimals);
    }
}
