package ru.mts.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Base64;

public interface Animal {
    /**
     *
     * @return получение породы животного
     */
    String getBreed();

    /**
     *
     * @return получение имени животного
     */
    String getName();

    /**
     *
     * @return получение цены животного в магазине
     */
    BigDecimal getCost();

    /**
     *
     * @return получение характера животного
     */
    String getCharacter();

    /**
     *
     * @return получение дня рождения животного
     */
    LocalDate getBirthdate();

    String getSecretInformation();
}
