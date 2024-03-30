package ru.mts.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mts.exception.NegativeNumberException;
import ru.mts.model.*;
import ru.mts.properties.AnimalsProperties;
import ru.mts.service.AnimalType;
import ru.mts.service.CreateAnimalService;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class CreateAnimalServiceImpl implements CreateAnimalService {

    private AnimalType animalType;

    private final AnimalsProperties animalsProperties;

    @Autowired
    public CreateAnimalServiceImpl(AnimalsProperties animalsProperties) {
        this.animalsProperties = animalsProperties;
    }

    public List<Animal> create10CustomAnimals() {
        List<Animal> animals = new CopyOnWriteArrayList<>();
        animals.add(new Cat("breed1", "Cat1", LocalDate.ofYearDay(2000, 135)));
        animals.add(new Dog("breed2", "Dog1", LocalDate.ofYearDay(2004, 300)));
        animals.add(new Shark("breed1", "Shark1", LocalDate.ofYearDay(1999, 111)));
        animals.add(new Shark("breed1", "Shark1", LocalDate.ofYearDay(1999, 111)));
        animals.add(new Dog("breed1", "Dog1", LocalDate.ofYearDay(2000, 27)));
        animals.add(new Wolf("breed1", "Wolf1", LocalDate.ofYearDay(2000, 67)));
        animals.add(new Cat("breed1", "Cat1", LocalDate.ofYearDay(2000, 135)));
        animals.add(new Wolf("breed1", "Wolf1", LocalDate.ofYearDay(2000, 135)));
        animals.add(new Wolf("breed1", "Wolf1", LocalDate.ofYearDay(2000, 272)));
        animals.add(new Cat("breed1", "Cat1", LocalDate.ofYearDay(2016, 19)));
        return animals;
    }

    @Override
    public Map<String, List<Animal>> createMapRandomAnimals(int n) {
        if (n < 0) throw new NegativeNumberException(n);
        if(n == 0) return Collections.emptyMap();
        Map<String, List<Animal>> animalsMap = new ConcurrentHashMap<>();
        int i = n;
        do {
            i--;
            Animal animal = createRandomAnimal();
            List<Animal> animalList = animalsMap.getOrDefault(animal.getClass().getSimpleName(), new CopyOnWriteArrayList<>());
            animalList.add(animal);
            animalsMap.put(animal.getClass().getSimpleName(), animalList);
        } while (i > 0);
        return animalsMap;
    }

    @Override
    public AnimalsProperties getAnimalsProperties() {
        return animalsProperties;
    }

    public void setAnimalType(AnimalType animalType) {
        this.animalType = animalType;
    }

    public Animal getAnimal() {
        return animalType.getAnimal();
    }

}
