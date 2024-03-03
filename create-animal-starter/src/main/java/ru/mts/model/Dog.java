package ru.mts.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Dog extends Pet {
    public Dog() {
        breed = "Dog Example";
    }

    public Dog(String breed, String name, LocalDate birthdate) {
        this.breed = breed;
        this.name = name;
        this.birthdate = birthdate;
    }

    public Dog(String name, LocalDate birthdate, BigDecimal cost) {
        this.name = name;
        this.birthdate = birthdate;
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractAnimal that = (AbstractAnimal) o;
        return Objects.equals(breed, that.breed) && Objects.equals(name, that.name) && Objects.equals(birthdate, that.birthdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(breed, name, birthdate);
    }
}
