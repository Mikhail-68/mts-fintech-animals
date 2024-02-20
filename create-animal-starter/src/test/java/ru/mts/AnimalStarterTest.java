package ru.mts;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import ru.mts.createAnimal.CreateAnimalService;
import ru.mts.createAnimal.CreateAnimalServiceImpl;
import ru.mts.model.Animal;
import ru.mts.model.Cat;
import ru.mts.properties.AnimalsProperties;
import ru.mts.repository.AnimalsRepositoryImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AnimalStarterTest {
    @Autowired
    private CreateAnimalService createAnimalService;
    @MockBean
    private AnimalsProperties animalsProperties;

    private AnimalsRepositoryImpl animalsRepository;

    @BeforeEach
    public void beforeEach() {
        animalsRepository = new AnimalsRepositoryImpl(createAnimalService, animalsProperties);
    }

    @Test
    @DisplayName("Проверка, что init метод инициализирует массив животных")
    public void getAnimalsReturnNotNullAfterInitMethod() {
        animalsRepository.init();
        Assertions.assertNotNull(animalsRepository.getAnimals());
    }

    @Test
    @DisplayName("Проверка, что при возвращении null init метод работает корректно")
    public void doesNotThrowExceptionWhenCreateAnimalReturnNull() {
        when(animalsProperties.getNames()).thenReturn(List.of("Barsik"));
        when(createAnimalService.createRandomAnimal()).thenReturn(null);
        Assertions.assertDoesNotThrow(() -> animalsRepository.init());
    }

    @Test
    @DisplayName("Проверка findDuplicate при множестве одинаковых животных")
    public void findDuplicateAmongTheSame() {
        when(createAnimalService.createRandomAnimal()).thenReturn(new Cat("breed", "Barsik", LocalDate.ofYearDay(1, 1)));

        animalsRepository.init();
        Set<Animal> expectedSet = animalsRepository.findDuplicate();

        Assertions.assertEquals(1, expectedSet.size());
        Assertions.assertTrue(expectedSet.contains(new Cat("breed", "Barsik", LocalDate.ofYearDay(1, 1))));
    }

    @Test
    @DisplayName("Проверка метода findOlderName, что при отсутствии подходящих животных он возвращает Null")
    public void findOlderAnimalTest() {
        when(createAnimalService.createRandomAnimal()).thenReturn(new Cat("", "", LocalDate.ofYearDay(1, 1)));
        Animal[] expectedAnimals = animalsRepository.findOlderAnimal(0);
        Assertions.assertNull(expectedAnimals);
    }

    @TestConfiguration
    public static class Conf {
        // Не знал какой бин можно подменить, поэтому замокал сервис вручную))
        @Bean
        public CreateAnimalService createAnimalService() {
            return mock(CreateAnimalServiceImpl.class);
        }
    }
}
