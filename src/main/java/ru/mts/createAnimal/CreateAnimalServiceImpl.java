package ru.mts.createAnimal;

import ru.mts.Dog;

public class CreateAnimalServiceImpl implements CreateAnimalService {
    public void create10Animal(int count) {
        for(int i = 0; i < count; i++) {
            Dog dog = new Dog();
            printCreatedAnimal();
        }
    }

    @Override
    public void create10Animal() {
        int i = 10;
        do {
            Dog dog = new Dog();
            printCreatedAnimal();
            i--;
        } while (i > 0);
    }
}
