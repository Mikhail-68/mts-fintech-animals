package ru.mts.createAnimal;

import ru.mts.model.*;

import java.util.function.Supplier;

public enum AnimalType {
    CAT (Cat::new),
    DOG (Dog::new),
    SHARK (Shark::new),
    WOLF (Wolf::new);

    private final Supplier<Animal> supplier;
    AnimalType(Supplier<Animal> supplier) {
        this.supplier = supplier;
    }

    public Animal getAnimal() {
        return supplier.get();
    }
}
