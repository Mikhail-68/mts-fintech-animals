package ru.mts.createAnimal;

import ru.mts.Animal;
import ru.mts.Dog;

public class CreateAnimalServiceImpl implements CreateAnimalService {
    public Animal[] createAnimals(int count) {
        Animal[] animals = new Animal[count];
        for(int i = 0; i < count; i++) {
            animals[i] = createRandomAnimal();
        }
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
