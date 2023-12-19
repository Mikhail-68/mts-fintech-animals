package ru.mts.createAnimal;

import ru.mts.Dog;

public class CreateAnimalServiceImpl implements CreateAnimalService {
    public void create10Animal(int count) {
        for(int i = 0; i < count; i++) {
            printCreatedAnimal(createRandomAnimal());
        }
    }

    @Override
    public void create10Animal() {
        int i = 10;
        do {
            printCreatedAnimal(createRandomAnimal());
            i--;
        } while (i > 0);
    }
}
