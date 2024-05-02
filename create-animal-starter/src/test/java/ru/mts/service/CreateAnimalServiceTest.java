package ru.mts.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.mts.exception.NegativeNumberException;
import ru.mts.model.Animal;
import ru.mts.properties.AnimalsProperties;

import java.util.List;
import java.util.Map;

@SpringBootTest
@ActiveProfiles("test")
@Disabled
public class CreateAnimalServiceTest {
    @Autowired
    CreateAnimalService createAnimalService;
    @Autowired
    AnimalsProperties animalsProperties;

    @Nested
    class createMapRandomAnimalsTest {
        @ParameterizedTest
        @DisplayName("Проверка возвращаемого количества элементов")
        @ValueSource(ints = {0, 3, 10})
        public void checkCountElementsReturn(int expectedN) {
            Map<String, List<Animal>> mapAnimals = createAnimalService.createMapRandomAnimals(expectedN);
            long actualN = mapAnimals.values().stream()
                    .flatMap(List::stream)
                    .count();
            Assertions.assertEquals(expectedN, actualN);
        }

        @Test
        @DisplayName("Проверка выброса исключения при параметре меньше нуля")
        public void checkThrowNegativeNumberException() {
            Assertions.assertThrows(NegativeNumberException.class,
                    () -> createAnimalService.createMapRandomAnimals(-1));
        }

        @Test
        @DisplayName("Проверка сопоставления животного и ключа map")
        public void checkComparisonAnimalAndKeyMap() {
            Map<String, List<Animal>> mapAnimals = createAnimalService.createMapRandomAnimals(10);
            for (Map.Entry<String, List<Animal>> entry : mapAnimals.entrySet()) {
                String key = entry.getKey();
                for (Animal animal : entry.getValue()) {
                    Assertions.assertEquals(key, animal.getClass().getSimpleName());
                }
            }
        }
    }

    @Nested
    class createListRandomAnimalsTest {
        @ParameterizedTest
        @DisplayName("Проверка возвращаемого количества элементов")
        @ValueSource(ints = {0, 3, 10})
        public void checkCountElementsReturn(int expectedN) {
            List<Animal> listRandomAnimals = createAnimalService.createListRandomAnimals(expectedN);
            Assertions.assertEquals(expectedN, listRandomAnimals.size());
        }

        @Test
        @DisplayName("Проверка выброса исключения при параметре меньше нуля")
        public void checkThrowNegativeNumberException() {
            Assertions.assertThrows(NegativeNumberException.class,
                    () -> createAnimalService.createListRandomAnimals(-1));
        }
    }

    @Nested
    class createRandomAnimalsTest {
        @Test
        @DisplayName("Проверка, что имена создаваемых животных берутся из property")
        public void checkTakeNameFromPropertyFile() {
            for (int i = 0; i < 10; i++) {
                Animal randomAnimal = createAnimalService.createRandomAnimal();
                boolean propertyContainsAnimalName = animalsProperties.getNames().contains(randomAnimal.getName());
                Assertions.assertTrue(propertyContainsAnimalName);
            }
        }
    }
}
