package ru.mts;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import ru.mts.model.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.stream.Stream;

@Deprecated
@Disabled
class MyTest {

    @Nested
    class EqualsTest {
        @Nested
        class CatTest {
            @Test
            @DisplayName("Проверка двух одинаковых животных")
            public void isEquals() {
                Animal cat = new Cat("breed", "name", LocalDate.ofYearDay(2000, 1));
                Animal cat2 = new Cat("breed", "name", LocalDate.ofYearDay(2000, 1));
                Assertions.assertEquals(cat, cat2);
            }

            @Test
            @DisplayName("Проверка животного на null")
            public void isNotEqualsWithNull() {
                Animal cat = new Cat("breed", "name", LocalDate.ofYearDay(2000, 1));
                Assertions.assertNotEquals(cat, null);
            }

            @Test
            @DisplayName("Проверка животного с разницей в поле breed")
            public void isNotEqualsDiffBreed() {
                Animal cat = new Cat("breed", "name", LocalDate.ofYearDay(2000, 1));
                Animal cat2 = new Cat(null, "name", LocalDate.ofYearDay(2000, 1));
                Assertions.assertNotEquals(cat, cat2);
            }

            @Test
            @DisplayName("Проверка животного с разницей в поле name")
            public void isNotEqualsDiffName() {
                Animal cat = new Cat("breed", "name", LocalDate.ofYearDay(2000, 1));
                Animal cat2 = new Cat("breed", "name2", LocalDate.ofYearDay(2000, 1));
                Assertions.assertNotEquals(cat, cat2);
            }

            @Test
            @DisplayName("Проверка животного с разницей в поле даты")
            public void isNotEqualsDiffLocalDate() {
                Animal cat = new Cat("breed", "name", LocalDate.ofYearDay(2000, 1));
                Animal cat2 = new Cat("breed", "name", LocalDate.ofYearDay(2000, 2));
                Assertions.assertNotEquals(cat, cat2);
            }
        }

        @Nested
        class DogTest {
            @Test
            @DisplayName("Проверка двух одинаковых животных")
            public void isEquals() {
                Animal dog = new Dog("breed", "name", LocalDate.ofYearDay(2000, 1));
                Animal dog2 = new Dog("breed", "name", LocalDate.ofYearDay(2000, 1));
                Assertions.assertEquals(dog, dog2);
            }

            @Test
            @DisplayName("Проверка животного на null")
            public void isNotEqualsWithNull() {
                Animal dog = new Dog("breed", "name", LocalDate.ofYearDay(2000, 1));
                Assertions.assertNotEquals(dog, null);
            }

            @Test
            @DisplayName("Проверка животного с разницей в поле breed")
            public void isNotEqualsDiffBreed() {
                Animal dog = new Dog("breed", "name", LocalDate.ofYearDay(2000, 1));
                Animal dog2 = new Dog("breed2", "name", LocalDate.ofYearDay(2000, 1));
                Assertions.assertNotEquals(dog, dog2);
            }

            @Test
            @DisplayName("Проверка животного с разницей в поле name")
            public void isNotEqualsDiffName() {
                Animal dog = new Dog("breed", "name", LocalDate.ofYearDay(2000, 1));
                Animal dog2 = new Dog("breed", "name2", LocalDate.ofYearDay(2000, 1));
                Assertions.assertNotEquals(dog, dog2);
            }

            @Test
            @DisplayName("Проверка животного с разницей в поле даты")
            public void isNotEqualsDiffLocalDate() {
                Animal dog = new Dog("breed", "name", LocalDate.ofYearDay(2000, 1));
                Animal dog2 = new Dog("breed", "name", LocalDate.ofYearDay(2000, 2));
                Assertions.assertNotEquals(dog, dog2);
            }
        }

        @Nested
        class SharkTest {
            @Test
            @DisplayName("Проверка двух одинаковых животных")
            public void isEquals() {
                Animal shark = new Shark("breed", "name", LocalDate.ofYearDay(2000, 1));
                Animal shark2 = new Shark("breed", "name", LocalDate.ofYearDay(2000, 1));
                Assertions.assertEquals(shark, shark2);
            }

            @Test
            @DisplayName("Проверка животного на null")
            public void isNotEqualsWithNull() {
                Animal shark = new Shark("breed", "name", LocalDate.ofYearDay(2000, 1));
                Assertions.assertNotEquals(shark, null);
            }

            @Test
            @DisplayName("Проверка животного с разницей в поле breed")
            public void isNotEqualsDiffBreed() {
                Animal shark = new Shark("breed", "name", LocalDate.ofYearDay(2000, 1));
                Animal shark2 = new Shark("breed2", "name", LocalDate.ofYearDay(2000, 1));
                Assertions.assertNotEquals(shark, shark2);
            }

            @Test
            @DisplayName("Проверка животного с разницей в поле name")
            public void isNotEqualsDiffName() {
                Animal shark = new Shark("breed", "name", LocalDate.ofYearDay(2000, 1));
                Animal shark2 = new Shark("breed", "name2", LocalDate.ofYearDay(2000, 1));
                Assertions.assertNotEquals(shark, shark2);
            }

            @Test
            @DisplayName("Проверка животного с разницей в поле даты")
            public void isNotEqualsDiffLocalDate() {
                Animal shark = new Shark("breed", "name", LocalDate.ofYearDay(2000, 1));
                Animal shark2 = new Shark("breed", "name", LocalDate.ofYearDay(2000, 2));
                Assertions.assertNotEquals(shark, shark2);
            }
        }

        @Nested
        class WolfTest {
            @Test
            @DisplayName("Проверка двух одинаковых животных")
            public void isEquals() {
                Animal wolf = new Wolf("breed", "name", LocalDate.ofYearDay(2000, 1));
                Animal wolf2 = new Wolf("breed", "name", LocalDate.ofYearDay(2000, 1));
                Assertions.assertEquals(wolf, wolf2);
            }

            @Test
            @DisplayName("Проверка животного на null")
            public void isNotEqualsWithNull() {
                Animal wolf = new Wolf("breed", "name", LocalDate.ofYearDay(2000, 1));
                Assertions.assertNotEquals(wolf, null);
            }

            @Test
            @DisplayName("Проверка животного с разницей в поле breed")
            public void isNotEqualsDiffBreed() {
                Animal wolf = new Wolf("breed", "name", LocalDate.ofYearDay(2000, 1));
                Animal wolf2 = new Shark("breed2", "name", LocalDate.ofYearDay(2000, 1));
                Assertions.assertNotEquals(wolf, wolf2);
            }

            @Test
            @DisplayName("Проверка животного с разницей в поле name")
            public void isNotEqualsDiffName() {
                Animal wolf = new Wolf("breed", "name", LocalDate.ofYearDay(2000, 1));
                Animal wolf2 = new Shark("breed", "name2", LocalDate.ofYearDay(2000, 1));
                Assertions.assertNotEquals(wolf, wolf2);
            }

            @Test
            @DisplayName("Проверка животного с разницей в поле даты")
            public void isNotEqualsDiffLocalDate() {
                Animal wolf = new Wolf("breed", "name", LocalDate.ofYearDay(2000, 1));
                Animal wolf2 = new Shark("breed", "name", LocalDate.ofYearDay(2000, 2));
                Assertions.assertNotEquals(wolf, wolf2);
            }
        }

        @Test
        @DisplayName("Проверка Cat и Dog с одинаковыми параметрами")
        public void isNotEqualsCatAndDog() {
            Animal cat = new Cat("breed", "name", LocalDate.ofYearDay(2000, 1));
            Animal dog = new Dog("breed", "name", LocalDate.ofYearDay(2000, 1));
            Assertions.assertNotEquals(cat, dog);
        }

        @Test
        @DisplayName("Проверка Shark и Dog с одинаковыми параметрами")
        public void isNotEqualsDogAndShark() {
            Animal shark = new Shark("breed", "name", LocalDate.ofYearDay(2000, 1));
            Animal dog = new Dog("breed", "name", LocalDate.ofYearDay(2000, 1));
            Assertions.assertNotEquals(shark, dog);
        }

        @Test
        @DisplayName("Проверка Shark и Wolf с одинаковыми параметрами")
        public void isNotEqualsSharkAndWolf() {
            Animal shark = new Shark("breed", "name", LocalDate.ofYearDay(2000, 1));
            Animal wolf = new Wolf("breed", "name", LocalDate.ofYearDay(2000, 1));
            Assertions.assertNotEquals(shark, wolf);
        }
    }

    @Nested
    class SearchAnimalServiceTest {
        SearchAnimalService searchService = new SearchAnimalServiceImpl();

        @Nested
        class FindLeapYearNamesTest {

            @Test
            public void animalsIsNull() {
                Assertions.assertNull(searchService.findLeapYearNames(null));
            }

            @Test
            public void animalsLength0() {
                Animal[] animals = new Animal[0];
                Assertions.assertNull(searchService.findLeapYearNames(animals));
            }

            @Test
            public void notAnimalsLeapYear() {
                Animal[] animals = {
                        new Cat("", "", LocalDate.ofYearDay(2001, 1)),
                        new Cat("", "", LocalDate.ofYearDay(2003, 1)),
                        new Cat("", "", LocalDate.ofYearDay(2013, 1))
                };
                Assertions.assertNull(searchService.findLeapYearNames(animals));
            }

            @Test
            public void animalsLeapYear() {
                Animal[] animals = {
                        new Cat("", "cat", LocalDate.ofYearDay(2008, 1)),
                        new Dog("", "dog", LocalDate.ofYearDay(2016, 1)),
                        new Wolf("", "wolf", LocalDate.ofYearDay(2012, 1)),
                        new Wolf("", "wolfNotLeap", LocalDate.ofYearDay(2017, 1)),
                        new Shark("", "shark", LocalDate.ofYearDay(2024, 1))
                };
                String[] expected = {"cat", "dog", "wolf", "shark"};
                Assertions.assertArrayEquals(expected, searchService.findLeapYearNames(animals));
            }
        }

        @Nested
        class FindOlderAnimalTest {

            @Test
            @DisplayName("Поиск животных при массиве равном null")
            public void animalsIsNull() {
                Assertions.assertNull(searchService.findOlderAnimal(null, 0));
            }

            @Test
            @DisplayName("Поиск животных при массиве размера 0")
            public void animalsLength0() {
                Animal[] animals = new Animal[0];
                Assertions.assertNull(searchService.findOlderAnimal(animals, 0));
            }

            @ParameterizedTest
            @MethodSource("ru.mts.FindOlderArgumentsProvider#getArguments")
            @DisplayName("Параметризованный тест с тестами граничных значений")
            public void findOlder(Animal[] animals, int N, Animal[] expectedResult) {
                Assertions.assertArrayEquals(expectedResult, searchService.findOlderAnimal(animals, N));
                System.out.println(Arrays.toString(animals));
            }
        }

        @Nested
        class FindDuplicateTest {

            @Test
            @DisplayName("Поиск дубликатов при массиве равном null")
            public void animalsIsNull() {
                Assertions.assertNull(searchService.findDuplicate(null));
            }

            @Test
            @DisplayName("Поиск дубликатов при массиве размера 0")
            public void animalsLength0() {
                Animal[] animals = new Animal[0];
                Assertions.assertNull(searchService.findDuplicate(animals));
            }

            @Test
            @DisplayName("Проверка возвращаемого значения при отсутствии дубликатов")
            public void withoutDuplicateAnimals() {
                Animal[] animals = {
                        new Shark("", "", LocalDate.ofYearDay(2000, 1)),
                        new Dog("1", "", LocalDate.ofYearDay(2000, 1)),
                        new Dog("", "", LocalDate.ofYearDay(2000, 1)),
                        new Shark("1", "", LocalDate.ofYearDay(2000, 1)),
                        new Shark("2", "", LocalDate.ofYearDay(2000, 1))
                };
                Assertions.assertNull(searchService.findDuplicate(animals));
            }

            @Test
            @DisplayName("Проверка поиска с 2 и более дубликатами")
            public void withDuplicateAnimals() {
                Animal[] animals = {
                        new Shark("", "", LocalDate.ofYearDay(2000, 1)),
                        new Dog("1", "2", LocalDate.ofYearDay(2001, 1)),
                        new Dog("", "", LocalDate.ofYearDay(2001, 1)),
                        new Dog("1", "2", LocalDate.ofYearDay(2001, 1)),
                        new Shark("", "", LocalDate.ofYearDay(2000, 1)),
                        new Cat("", "", LocalDate.ofYearDay(2000, 1)),
                        new Shark("", "", LocalDate.ofYearDay(2000, 1))
                };
                Animal[] expected = {
                        new Shark("", "", LocalDate.ofYearDay(2000, 1)),
                        new Dog("1", "2", LocalDate.ofYearDay(2001, 1))
                };
                Assertions.assertArrayEquals(expected, searchService.findDuplicate(animals));
            }
        }
    }
}

class FindOlderArgumentsProvider {
    static Stream<Arguments> getArguments() {
        return Stream.of(
                Arguments.of(
                        new Animal[]{
                                new Cat("", "", LocalDate.ofYearDay(2000, 1)),
                                new Cat("", "", LocalDate.now().minusDays(1)),
                                new Cat("", "", LocalDate.now()),
                                new Cat("", "", null)
                        },
                        0,
                        new Animal[]{
                                new Cat("", "", LocalDate.ofYearDay(2000, 1)),
                                new Cat("", "", LocalDate.now().minusDays(1))
                        }
                ),
                Arguments.of(
                        new Animal[]{
                                new Dog("", "", LocalDate.ofYearDay(2000, 1)),
                                new Dog("", "", LocalDate.now().minusYears(10)),
                                new Dog("", "", LocalDate.now().minusYears(15)),
                                new Dog("", "", LocalDate.now().minusYears(15).minusDays(1)),
                                new Dog("", "", LocalDate.now().minusYears(16)),
                                new Dog("", "", LocalDate.now().minusYears(40))
                        },
                        15,
                        new Animal[]{
                                new Dog("", "", LocalDate.ofYearDay(2000, 1)),
                                new Dog("", "", LocalDate.now().minusYears(15).minusDays(1)),
                                new Dog("", "", LocalDate.now().minusYears(16)),
                                new Dog("", "", LocalDate.now().minusYears(40))
                        }
                )
        );
    }
}