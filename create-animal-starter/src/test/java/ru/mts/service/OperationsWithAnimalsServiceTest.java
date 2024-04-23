package ru.mts.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.mts.exception.IllegalArraySizeException;
import ru.mts.exception.NegativeNumberException;
import ru.mts.model.Animal;
import ru.mts.model.Cat;
import ru.mts.model.Dog;
import ru.mts.service.impl.OperationsWithAnimalsServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SpringBootTest
@Disabled
public class OperationsWithAnimalsServiceTest {
    @Autowired
    private CreateAnimalService createAnimalService;

    private OperationsWithAnimalsService operationsWithAnimalsService;

    @BeforeEach
    public void beforeEach() {
        operationsWithAnimalsService = new OperationsWithAnimalsServiceImpl();
    }

    @Nested
    class FindLeapYearTest {
        @Test
        @DisplayName("Проверяем возврат пустой map при неинициализированном animals")
        public void animalsIsNull() {
            Assertions.assertTrue(operationsWithAnimalsService.findLeapYearNames(null).isEmpty());
        }

        @Test
        @DisplayName("Проверка, что нет NPE при животных = null")
        public void eachAnimalIsNullTest() {
            List<Animal> animals = new ArrayList<>();
            animals.add(null);
            Assertions.assertTrue(operationsWithAnimalsService.findLeapYearNames(animals).isEmpty());
        }

        @Test
        @DisplayName("Проверка возврата пустой map при не нахождении животных родившихся в високосный год")
        public void animalsNotContainsIsLeapYearTest() {
            List<Animal> animals = List.of(new Dog("", "name", LocalDate.ofYearDay(2021, 1)));
            Map<String, LocalDate> crntMap = operationsWithAnimalsService.findLeapYearNames(animals);
            Assertions.assertTrue(crntMap.isEmpty());
        }

        @Test
        @DisplayName("Проверка найденных животных в високосный год")
        public void animalIsLeapYearsTest() {
            Animal animal = new Dog("", "name", LocalDate.ofYearDay(2020, 1));
            List<Animal> animals = List.of(animal);

            Map<String, LocalDate> crntMap = operationsWithAnimalsService.findLeapYearNames(animals);
            Set<Map.Entry<String, LocalDate>> entrySet = crntMap.entrySet();

            Assertions.assertEquals(1, crntMap.size());
            Assertions.assertTrue(entrySet.contains(Map.entry("Dog name", animal.getBirthdate())));
        }
    }

    @Nested
    class FindOlderAnimalTest {
        @Test
        @DisplayName("Проверка метода findOlderName, что при пустом массиве animals он возвращает emptyMap")
        public void animalTest() {
            List<Animal> animals = new ArrayList<>();
            Map<Animal, Integer> crntAnimals = operationsWithAnimalsService.findOlderAnimal(animals, 0);
            Assertions.assertTrue(crntAnimals.isEmpty());
        }

        @Test
        @DisplayName("Проверка выброса исключения при отрицательном параметре age")
        public void checkThrowNegativeNumberException() {
            List<Animal> animals = new ArrayList<>();
            Assertions.assertThrows(NegativeNumberException.class,
                    () -> operationsWithAnimalsService.findOlderAnimal(animals, -1));
        }

        @Test
        @DisplayName("Проверка что нет NPE при присутствующих животных = null")
        public void eachAnimalIsNullTest() {
            List<Animal> animals = new ArrayList<>();
            animals.add(null);
            Assertions.assertTrue(operationsWithAnimalsService.findOlderAnimal(animals, 0).isEmpty());
        }

        @ParameterizedTest(name = "age: {arguments}")
        @DisplayName("Проверка возврата самого старшего животного при отсутствующих подходящих вариантов и возврат подходящих вариантов")
        @ValueSource(ints = {1, 1000})
        public void returnAnimalsTest(int age) {
            Animal animal = new Cat("", "", LocalDate.ofYearDay(2020, 1));
            List<Animal> animals = List.of(animal);
            Map<Animal, Integer> actualAnimals = operationsWithAnimalsService.findOlderAnimal(animals, age);
            Assertions.assertEquals(Period.between(animal.getBirthdate(), LocalDate.now()).getYears(), actualAnimals.get(animal));
        }
    }

    @Nested
    class FindDuplicateTest {
        @Test
        @DisplayName("Проверка возврата emptyMap при пустом массиве animals")
        public void animalsIsNullTest() {
            Map<String, List<Animal>> duplicate = operationsWithAnimalsService.findDuplicate(new ArrayList<>());
            Assertions.assertTrue(duplicate.isEmpty());
        }

        @Test
        @DisplayName("Проверка отсутствия NPE при содержании null элемента")
        public void findDuplicateWhereEachAnimalIsNull() {
            List<Animal> animals = new ArrayList<>();
            animals.add(null);
            Map<String, List<Animal>> actualMap = operationsWithAnimalsService.findDuplicate(animals);
            Assertions.assertTrue(actualMap.isEmpty());
        }

        @Test
        @DisplayName("Проверка findDuplicate при множестве одинаковых животных")
        public void findDuplicateAmongTheSame() {
            Animal animal = new Cat("breed", "Barsik", LocalDate.ofYearDay(1, 1));
            List<Animal> animals = List.of(animal, animal, animal);

            Map<String, List<Animal>> actualMap = operationsWithAnimalsService.findDuplicate(animals);
            Assertions.assertEquals(1, actualMap.size());
            Assertions.assertEquals(1, actualMap.get("Cat").size());
        }

        @Test
        @DisplayName("Проверка возвращения пустой коллекции при списке без дупликатов")
        public void checkReturnEmptyMapWhenAnimalsWithNotContainsDuplicate() {
            Animal animal = new Cat("breed", "Barsik", LocalDate.ofYearDay(1, 1));
            Animal animal2 = new Cat("breed", "Umka", LocalDate.ofYearDay(1, 1));
            Animal animal3 = new Cat("breed", "Murzik", LocalDate.ofYearDay(1, 1));
            List<Animal> animals = List.of(animal, animal2, animal3);

            Map<String, List<Animal>> actualMap = operationsWithAnimalsService.findDuplicate(animals);

            Assertions.assertTrue(actualMap.isEmpty());
        }
    }

    @Nested
    class FindAverageAge {
        @Test
        @DisplayName("Проверка, что нет NPE при массиве animals равном null")
        public void animalsIsNull() {
            Assertions.assertEquals(0, operationsWithAnimalsService.findAverageAge(null));
        }

        @Test
        @DisplayName("Проверка, что нет NPE при элементе массива равном null")
        public void eachAnimalIsNull() {
            List<Animal> animals = new ArrayList<>();
            animals.add(null);
            animals.add(null);
            Assertions.assertEquals(0, operationsWithAnimalsService.findAverageAge(animals));
        }

        @Test
        @DisplayName("Проверка правильности рассчета среднего значения")
        public void checkCorrectCalculate() {
            List<Animal> animals = List.of(
                    new Cat("", "", LocalDate.now().minusYears(10)),
                    new Cat("", "", LocalDate.now().minusYears(5))
            );
            double actualResult = operationsWithAnimalsService.findAverageAge(animals);
            Assertions.assertEquals(7.5, actualResult);
        }

        @Test
        @DisplayName("Проверка правильности рассчета среднего значения при существующих null животных")
        public void checkCorrectCalculateWithNullAnimal() {
            List<Animal> animals = new ArrayList<>();
            animals.add(new Cat("", "", LocalDate.now().minusYears(10)));
            animals.add(new Cat("", "", LocalDate.now().minusYears(5)));
            animals.add(null);
            Assertions.assertEquals(7.5, operationsWithAnimalsService.findAverageAge(animals));
        }
    }

    @Nested
    class FindOldAndExpensive {
        @Test
        @DisplayName("Проверка, что нет NPE при массиве animals равном null")
        public void animalsIsNull() {
            Assertions.assertTrue(operationsWithAnimalsService.findOldAndExpensive(null).isEmpty());
        }

        @Test
        @DisplayName("Проверка отбора и сортировки возвращаемых данных")
        public void checkSortAndFilter() {
            List<Animal> actualAnimals = List.of(
                    new Cat("", LocalDate.ofYearDay(1, 1), BigDecimal.valueOf(0)),
                    new Cat("", LocalDate.now(), BigDecimal.valueOf(10)),
                    new Cat("", LocalDate.ofYearDay(1, 5), BigDecimal.valueOf(10)),
                    new Cat("", LocalDate.ofYearDay(1, 4), BigDecimal.valueOf(10)),
                    new Cat("", LocalDate.ofYearDay(1, 6), BigDecimal.valueOf(10)),
                    new Cat("", LocalDate.now().minusYears(5), BigDecimal.valueOf(10)),
                    new Cat("", LocalDate.now().minusYears(6), BigDecimal.valueOf(10))
            );
            List<Animal> expectedAnimals = List.of(
                    new Cat("", LocalDate.ofYearDay(1, 4), BigDecimal.valueOf(10)),
                    new Cat("", LocalDate.ofYearDay(1, 5), BigDecimal.valueOf(10)),
                    new Cat("", LocalDate.ofYearDay(1, 6), BigDecimal.valueOf(10)),
                    new Cat("", LocalDate.now().minusYears(6), BigDecimal.valueOf(10))
            );
            Assertions.assertEquals(expectedAnimals, operationsWithAnimalsService.findOldAndExpensive(actualAnimals));
        }
    }

    @Nested
    class FindMinConstAnimals {
        @Test
        @DisplayName("Проверка, что есть NPE при массиве animals равном null")
        public void animalsIsNull() {
            Assertions.assertThrows(NullPointerException.class,
                    () -> operationsWithAnimalsService.findMinConstAnimals(null));
        }

        @Test
        @DisplayName("Проверка, что при недостаточном размере массиве выбрасывается ошибка IllegalArraySizeException")
        public void checkThrowIllegalArraySizeException() {
            List<Animal> animals = Mockito.mock(List.class);

            Mockito.when(animals.size()).thenReturn(2);
            Assertions.assertThrows(IllegalArraySizeException.class,
                    () -> operationsWithAnimalsService.findMinConstAnimals(animals));

            Mockito.when(animals.size()).thenReturn(3);
            Assertions.assertDoesNotThrow(() -> operationsWithAnimalsService.findMinConstAnimals(animals));
        }

        @Test
        @DisplayName("Проверка, что нет NPE при элементе массива равном null")
        public void eachAnimalIsNull() throws IllegalArraySizeException {
            List<Animal> animals = new ArrayList<>();
            animals.add(null);
            animals.add(null);
            animals.add(null);
            Assertions.assertTrue(operationsWithAnimalsService.findMinConstAnimals(animals).isEmpty());
        }

        @Test
        @DisplayName("Проверка ограничения размера получаемой коллекции")
        public void checkSizeList() throws IllegalArraySizeException {
            Dog dog = new Dog("", "", LocalDate.now());
            dog.setCost(BigDecimal.valueOf(1000));
            List<Animal> animals = List.of(dog, dog, dog, dog, dog);
            Assertions.assertEquals(3, operationsWithAnimalsService.findMinConstAnimals(animals).size());
        }

        @Test
        @DisplayName("Проверка отбора и сортировки возвращаемых данных")
        public void checkSortFilter() throws IllegalArraySizeException {
            List<Animal> animals = List.of(
                    new Cat("C", LocalDate.now(), BigDecimal.valueOf(1)),
                    new Cat("B", LocalDate.now(), BigDecimal.valueOf(0)),
                    new Cat("A", LocalDate.now(), BigDecimal.valueOf(3)),
                    new Cat("", LocalDate.now(), BigDecimal.valueOf(10)),
                    new Cat("", LocalDate.now(), BigDecimal.valueOf(10)),
                    new Cat("", LocalDate.now(), BigDecimal.valueOf(10))
            );
            List<String> expectedResult = List.of("C", "B", "A");
            Assertions.assertEquals(expectedResult, operationsWithAnimalsService.findMinConstAnimals(animals));
        }
    }
}
