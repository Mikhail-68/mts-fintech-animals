package ru.mts.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import ru.mts.properties.AnimalsProperties;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

public abstract class AbstractAnimal implements Animal {
//    @Value("${application.names}")
//    private List<String> names;
    @Autowired
    private AnimalsProperties animalsProperties;
    protected String breed;
    protected String name;
    protected BigDecimal cost;
    protected String character;
    protected LocalDate birthdate;

    public AbstractAnimal() {
        Random random = new Random();
        birthdate = LocalDate.ofYearDay(LocalDate.now().getYear() - random.nextInt(15), random.nextInt(365));
//        List<String> names = animalsProperties.getNames();
//        name = names.get(random.nextInt(names.size()));
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
        return cost.setScale(2, BigDecimal.ROUND_HALF_EVEN);
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
    public String toString() {
        return "AbstractAnimal{" +
                "breed='" + breed + '\'' +
                ", name='" + name + '\'' +
                ", birthdate=" + birthdate +
                '}';
    }
}
