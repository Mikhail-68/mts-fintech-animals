package ru.mts;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Random;

public abstract class AbstractAnimal implements Animal {
    protected String breed;
    protected String name;
    protected BigDecimal cost;
    protected String character;
    protected LocalDate birthdate;

    public AbstractAnimal() {
        Random random = new Random();
        birthdate = LocalDate.ofYearDay(LocalDate.now().getYear() - random.nextInt(15), random.nextInt(365));
    }

    @Override
    public String getBreed() {
        return breed;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public BigDecimal getCost() {
        return cost.setScale(2, RoundingMode.HALF_EVEN);
    }

    @Override
    public String getCharacter() {
        return character;
    }

    @Override
    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public void setBirthdate(LocalDate birthdate) {
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
    public String toString() {
        return "AbstractAnimal{" +
                "breed='" + breed + '\'' +
                ", name='" + name + '\'' +
                ", birthdate=" + birthdate +
                '}';
    }
}
