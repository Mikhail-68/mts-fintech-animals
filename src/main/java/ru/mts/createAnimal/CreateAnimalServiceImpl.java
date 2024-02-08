package ru.mts.createAnimal;

import ru.mts.*;

import java.time.LocalDate;

public class CreateAnimalServiceImpl implements CreateAnimalService {
    public Animal[] create10CustomAnimals() {
        Animal[] animals = new Animal[10];
        animals[0] = new Cat("breed1", "Cat1", LocalDate.ofYearDay(2000, 135));
        animals[1] = new Dog("breed2", "Dog1", LocalDate.ofYearDay(2004, 300));
        animals[2] = new Shark("breed1", "Shark1", LocalDate.ofYearDay(1999, 111));
        animals[3] = new Shark("breed1", "Shark1", LocalDate.ofYearDay(1999, 111));
        animals[4] = new Dog("breed1", "Dog1", LocalDate.ofYearDay(2000, 27));
        animals[5] = new Wolf("breed1", "Wolf1", LocalDate.ofYearDay(2000, 67));
        animals[6] = new Cat("breed1", "Cat1", LocalDate.ofYearDay(2000, 135));
        animals[7] = new Wolf("breed1", "Wolf1", LocalDate.ofYearDay(2000, 135));
        animals[8] = new Wolf("breed1", "Wolf1", LocalDate.ofYearDay(2000, 272));
        animals[9] = new Cat("breed1", "Cat1", LocalDate.ofYearDay(2016, 19));
        return animals;
    }

    @Override
    public Animal[] create10Animals() {
        Animal[] animals = new Animal[10];
        int i = 10;
        do {
            i--;
            animals[i] = createRandomAnimal();
        } while (i > 0);
        return animals;
    }
}
