package ru.mts.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.mts.jsonSerialize.AbstractAnimalSerialize;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Base64;
import java.util.Random;


@JsonSerialize(keyUsing= AbstractAnimalSerialize.class)
public abstract class AbstractAnimal implements Animal {
    protected String breed;
    protected String name;
    protected BigDecimal cost;
    protected String character;
    protected LocalDate birthdate;
    protected String secretInformation;

    public AbstractAnimal() {
        Random random = new Random();
        birthdate = LocalDate.ofYearDay(
                LocalDate.now().getYear() - random.nextInt(15), random.nextInt(360));
        cost = BigDecimal.valueOf(random.nextInt(1000, 10000));
    }

    @JsonGetter("secretInformation")
    public String encryptSecretInformation() {
        return Base64.getEncoder().encodeToString(secretInformation.getBytes());
    }

    @JsonSetter("secretInformation")
    public void decryptSecretInformation(String secretInformation) {
        this.secretInformation = new String(Base64.getDecoder().decode(secretInformation));
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
        if(cost == null) return BigDecimal.ZERO;
        return cost.setScale(2, RoundingMode.HALF_EVEN);
    }

    @Override
    public String getCharacter() {
        return character;
    }

    @Override
    public LocalDate getBirthdate() {
        if(birthdate == null) return LocalDate.now();
        return birthdate;
    }

    @Override
    public String getSecretInformation() {
        return secretInformation;
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

    public void setSecretInformation(String secretInformation) {
        this.secretInformation = secretInformation;
    }

    @Override
    public String toString() {
        return "AbstractAnimal{" +
                "breed='" + breed + '\'' +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", character='" + character + '\'' +
                ", birthdate=" + birthdate +
                ", secretInformation='" + secretInformation + '\'' +
                '}';
    }

    public String toStringJson() {
        return "AbstractAnimal{" +
                "breed='" + breed + '\'' +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", character='" + character + '\'' +
                ", birthdate=" + birthdate +
                ", secretInformation='" + encryptSecretInformation() + '\'' +
                '}';
    }
}
