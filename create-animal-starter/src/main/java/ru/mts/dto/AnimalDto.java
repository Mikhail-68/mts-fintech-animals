package ru.mts.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import ru.mts.jsonSerialize.AnimalDtoDeserialize;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Base64;

@JsonDeserialize(keyUsing = AnimalDtoDeserialize.class)
public class AnimalDto {
    private String breed;
    private String name;
    private BigDecimal cost;
    private String character;
    private LocalDate birthdate;
    private String secretInformation;

    @JsonGetter("secretInformation")
    public String encryptSecretInformation() {
        return Base64.getEncoder().encodeToString(secretInformation.getBytes());
    }

    @JsonSetter("secretInformation")
    public void decryptSecretInformation(String secretInformation) {
        this.secretInformation = new String(Base64.getDecoder().decode(secretInformation));
    }

    public AnimalDto() {
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getSecretInformation() {
        return secretInformation;
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
}
