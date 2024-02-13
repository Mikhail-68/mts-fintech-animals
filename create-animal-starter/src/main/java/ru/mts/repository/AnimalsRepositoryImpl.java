package ru.mts.repository;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import ru.mts.createAnimal.CreateAnimalServiceImpl;
import ru.mts.model.Animal;

import java.time.LocalDate;
import java.util.*;

//@Repository
public class AnimalsRepositoryImpl implements AnimalsRepository {

    private final CreateAnimalServiceImpl createAnimalService;
    private Animal[] animals;

    public AnimalsRepositoryImpl(CreateAnimalServiceImpl createAnimalService) {
        this.createAnimalService = createAnimalService;
    }

    public Animal[] getAnimals() {
        return animals;
    }

//    @PostConstruct
    public void init() {
        animals = new Animal[10];
        for (int i = 0; i < 10; i++) {
            animals[i] = createAnimalService.getAnimal();
        }
    }

    @Override
    public String[] findLeapYearNames() {
        if (animals == null) return null;

        String[] names = new String[0];
        int crntInd = 0;

        for (Animal animal : animals) {
            if (animal == null) continue;
            if (animal.getBirthdate().isLeapYear()) {
                names = Arrays.copyOf(names, crntInd + 1);
                names[crntInd++] = animal.getName();
            }
        }
        return names.length > 0 ? names : null;
    }

    @Override
    public Animal[] findOlderAnimal(int age) {
        if (animals == null) return null;

        Animal[] olderAnimals = new Animal[0];
        int crntInd = 0;

        for (int i = 0; i < animals.length; i++) {
            if (animals[i] == null || animals[i].getBirthdate() == null) continue;
            if (LocalDate.now().compareTo(animals[i].getBirthdate().plusYears(age)) > 0) {
                olderAnimals = Arrays.copyOf(olderAnimals, crntInd + 1);
                olderAnimals[crntInd++] = animals[i];
            }
        }
        return olderAnimals.length > 0 ? olderAnimals : null;
    }

    @Override
    public Set<Animal> findDuplicate() {
        Set<Animal> animalSet = new HashSet<>();
        Set<Animal> crntSet = new HashSet<>();
        for (Animal animal : animals) {
            if(crntSet.contains(animal)) {
                animalSet.add(animal);
            } else {
                crntSet.add(animal);
            }
        }
        return animalSet.isEmpty() ? Collections.emptySet() : animalSet;
    }

    @Override
    public void printDuplicate() {
        Set<Animal> duplicateAnimals = findDuplicate();

        if (duplicateAnimals.isEmpty()) return;

        for (Animal animal : duplicateAnimals) {
            System.out.println("Duplicate: name=" + animal.getName() + " birthdate=" + animal.getBirthdate());
        }
    }
}
