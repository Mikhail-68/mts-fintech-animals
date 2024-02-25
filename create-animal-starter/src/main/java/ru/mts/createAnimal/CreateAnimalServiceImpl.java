package ru.mts.createAnimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mts.model.*;
import ru.mts.properties.AnimalsProperties;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CreateAnimalServiceImpl implements CreateAnimalService {

    private AnimalType animalType;
    private final AnimalsProperties animalsProperties;

    @Autowired
    public CreateAnimalServiceImpl(AnimalsProperties animalsProperties) {
        this.animalsProperties = animalsProperties;
    }

    public List<Animal> create10CustomAnimals() {
        List<Animal> animals = new ArrayList<>();
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
    public List<Animal> create10Animals() {
        List<Animal> animals = new ArrayList<>();
        int i = 10;
        do {
            i--;
            animals.add(createRandomAnimal());
        } while (i > 0);
        return animals;
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
