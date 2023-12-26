package ru.mts;

import java.time.LocalDate;
import java.util.Objects;

public class Wolf extends Predator {
    public Wolf() {
    }

    public Wolf(String breed, String name, LocalDate birthdate) {
        this.breed = breed;
        this.name = name;
        this.birthdate = birthdate;
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
