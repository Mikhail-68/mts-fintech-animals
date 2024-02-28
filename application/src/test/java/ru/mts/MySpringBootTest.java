package ru.mts;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.mts.createAnimal.CreateAnimalServiceImpl;
import ru.mts.model.Animal;
import ru.mts.properties.AnimalsProperties;

import java.util.List;
import java.util.Map;

@SpringBootTest(classes = Application.class)
@ActiveProfiles("test")
public class MySpringBootTest {
    @Autowired
    private CreateAnimalServiceImpl createAnimalService;
    @Autowired
    private AnimalsProperties animalsProperties;

    @Test
    @DisplayName("Проверка, что метод возвращает животное с именем из properies")
    public void createRandomAnimalTest() {
        Animal animal = createAnimalService.createRandomAnimal();
        Assertions.assertNotNull(animal);
        Assertions.assertTrue(animalsProperties.getNames().contains(animal.getName()));
    }

    @Test
    @DisplayName("Проверка длины возвращаемого массива")
    public void checkCountCreate10AnimalsTest() {
        int size = 0;
        Map<String, List<Animal>> animals = createAnimalService.create10Animals();
        for(var elem : animals.values()) {
            size += elem.size();
        }
        Assertions.assertEquals(10, size);
    }

    @Test
    @DisplayName("Проверка того, что при создании рандомных животных их имена берутся из properties")
    public void checkNamesCreate10AnimalsTest() {
        Map<String, List<Animal>> animals = createAnimalService.create10Animals();
        for (var elemList : animals.values()) {
            for(var animal : elemList) {
                Assertions.assertTrue(animalsProperties.getNames().contains(animal.getName()));
            }
        }
    }

    @Test
    @DisplayName("Проверка создания исключения при входящем null параметре")
    public void checkPrintCreatedAnimalWhenAnimalIsNullTest() {
        Assertions.assertThrows(NullPointerException.class, () -> createAnimalService.printCreatedAnimal(null));
    }
}
