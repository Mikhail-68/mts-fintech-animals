package ru.mts;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import ru.mts.createAnimal.CreateAnimalService;
import ru.mts.createAnimal.CreateAnimalServiceImpl;
import ru.mts.model.Animal;
import ru.mts.model.Cat;
import ru.mts.model.Dog;
import ru.mts.properties.AnimalsProperties;
import ru.mts.repository.AnimalsRepositoryImpl;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Map;
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
        animalsRepository = new AnimalsRepositoryImpl(createAnimalService);
    }

    @Test
    @DisplayName("Проверка, что init метод инициализирует массив животных")
    public void getAnimalsReturnNotNullAfterInitMethod() {
        animalsRepository.init();
        Assertions.assertFalse(animalsRepository.getAnimals().isEmpty());
    }

    @Test
    @DisplayName("Проверка, что при возвращении null init метод работает корректно")
    public void doesNotThrowExceptionWhenCreateAnimalReturnNull() {
        when(animalsProperties.getNames()).thenReturn(List.of("Barsik"));
        when(createAnimalService.createRandomAnimal()).thenReturn(null);
        Assertions.assertDoesNotThrow(() -> animalsRepository.init());
    }

    @Nested
    class FindLeapYearTest {
        @Test
        @DisplayName("Проверяем возврат пустой map при неинициализированном animals")
        public void animalsIsNull() {
            Assertions.assertTrue(animalsRepository.findLeapYearNames().isEmpty());
        }
        @Test
        @DisplayName("Проверка возврата пустой map при не нахождении животных родившихся в високосный год")
        public void animalsNotContainsIsLeapYearTest() {
            Animal dog = new Dog("", "name", LocalDate.ofYearDay(2021, 1));
            when(createAnimalService.createRandomAnimal()).thenReturn(dog);

            animalsRepository.init();
            Map<String, LocalDate> crntMap = animalsRepository.findLeapYearNames();

            Assertions.assertTrue(crntMap.isEmpty());
        }
        @Test
        @DisplayName("Проверка найденных животных в високосный год")
        public void animalIsLeapYearsTest() {
            Animal dog = new Dog("", "name", LocalDate.ofYearDay(2020, 1));
            when(createAnimalService.createRandomAnimal()).thenReturn(dog);
            animalsRepository.init();

            Map<String, LocalDate> crntMap = animalsRepository.findLeapYearNames();
            Set<Map.Entry<String, LocalDate>> entrySet = crntMap.entrySet();

            Assertions.assertEquals(1, crntMap.size());
            Assertions.assertTrue(entrySet.contains(Map.entry("Dog name", dog.getBirthdate())));
        }
    }

    @Nested
    class FindOlderAnimalTest{
        @Test
        @DisplayName("Проверка метода findOlderName, что при пустом массиве animals он возвращает emptyMap")
        public void animalTest() {
            when(createAnimalService.createRandomAnimal()).thenReturn(new Cat("", "", LocalDate.ofYearDay(2020, 1)));
            Map<Animal, Integer> crntAnimals = animalsRepository.findOlderAnimal(0);
            Assertions.assertTrue(crntAnimals.isEmpty());
        }
        @ParameterizedTest(name = "age: {arguments}")
        @DisplayName("Проверка возврата самого старшего животного при отсутствующих подходящих вариантов и возврат подходящих вариантов")
        @ValueSource(ints = {1, 1000})
        public void returnAnimalsTest(int age) {
            Animal animal = new Cat("", "", LocalDate.ofYearDay(2020, 1));
            when(createAnimalService.createRandomAnimal()).thenReturn(animal);
            animalsRepository.init();
            Map<Animal, Integer> crntAnimals = animalsRepository.findOlderAnimal(age);
            Assertions.assertEquals(Period.between(animal.getBirthdate(), LocalDate.now()).getYears(), crntAnimals.get(animal));
        }
    }

    @Nested
    class findDuplicateTest {
        @Test
        @DisplayName("Проверка возврата emptyMap при пустом массиве animals")
        public void animalsIsNullTest() {
            Map<String, Integer> duplicate = animalsRepository.findDuplicate();
            Assertions.assertTrue(duplicate.isEmpty());
        }
        @Test
        @DisplayName("Проверка findDuplicate при множестве одинаковых животных")
        public void findDuplicateAmongTheSame() {
            when(createAnimalService.createRandomAnimal()).thenReturn(new Cat("breed", "Barsik", LocalDate.ofYearDay(1, 1)));

            animalsRepository.init();
            Map<String, Integer> expectedMap = animalsRepository.findDuplicate();

            Assertions.assertEquals(1, expectedMap.size());
            Assertions.assertEquals(10, expectedMap.get("Cat"));
        }
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
