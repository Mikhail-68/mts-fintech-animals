package ru.mts.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import ru.mts.entity.Animal;
import ru.mts.exception.IllegalArraySizeException;
import ru.mts.exception.NegativeNumberException;
import ru.mts.service.OperationsWithAnimalsService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@Repository
public class OperationsWithAnimalsServiceImpl implements OperationsWithAnimalsService {

    @Value("${application.animal.directory.result}")
    private String resultsDirectory;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public Map<String, LocalDate> findLeapYearNames(List<Animal> animals) {
        if (animals == null) return Collections.emptyMap();
        ConcurrentMap<String, LocalDate> answer = animals.stream()
                .filter(animal -> Objects.nonNull(animal) && animal.getBirthdate().isLeapYear())
                .collect(Collectors.toConcurrentMap(
                        animal -> animal.getClass().getSimpleName() + " " + animal.getName(),
                        Animal::getBirthdate,
                        (x, y) -> y
                ));

        writeToFile("findLeapYearNames.json", answer);

        return answer;
    }

    @Override
    public Map<Animal, Integer> findOlderAnimal(List<Animal> animals, int age) {
        if (animals == null) return Collections.emptyMap();
        if (age < 0) throw new NegativeNumberException(age);

        Map<Animal, Integer> resMap = animals.stream()
                .filter(animal -> Objects.nonNull(animal) && LocalDate.now().compareTo(animal.getBirthdate().plusYears(age)) > 0)
                .collect(Collectors.toConcurrentMap(
                        animal -> animal,
                        animal -> Period.between(animal.getBirthdate(), LocalDate.now()).getYears(),
                        (x, y) -> y
                ));
        if (resMap.isEmpty()) {
            resMap = animals.stream()
                    .filter(Objects::nonNull)
                    .sorted(Comparator.comparing(Animal::getBirthdate).reversed())
                    .limit(1)
                    .collect(Collectors.toConcurrentMap(
                            animal -> animal,
                            animal -> Period.between(animal.getBirthdate(), LocalDate.now()).getYears(),
                            (x, y) -> y
                    ));
        }

        writeToFile("findOlderAnimal.json", resMap);

        return resMap;
    }

    @Override
    public Map<String, List<Animal>> findDuplicate(List<Animal> animals) {
        if (animals == null) return Collections.emptyMap();

        ConcurrentMap<String, List<Animal>> answer = animals.stream()
                .filter(Objects::nonNull)
                .filter(animal -> Collections.frequency(animals, animal) > 1)
                .distinct()
                .collect(Collectors.groupingByConcurrent(
                        animal -> animal.getClass().getSimpleName(),
                        Collectors.collectingAndThen(Collectors.toList(), CopyOnWriteArrayList::new)));

        writeToFile("findDuplicate.json", answer);

        return answer;
    }

    @Override
    public double findAverageAge(List<Animal> animals) {
        if (animals == null) return 0;
        double answer = animals.stream()
                .filter(Objects::nonNull)
                .mapToInt(animal -> Period.between(animal.getBirthdate(), LocalDate.now()).getYears())
                .average().orElse(0);

        writeToFile("findAverageAge.json", answer);

        return answer;
    }

    @Override
    public List<Animal> findOldAndExpensive(List<Animal> animals) {
        if (animals == null) return Collections.emptyList();
        double averageCost = animals.stream()
                .filter(Objects::nonNull)
                .mapToDouble(animal -> animal.getCost().doubleValue())
                .average().orElse(0);
        CopyOnWriteArrayList<Animal> answer = animals.stream()
                .filter(Objects::nonNull)
                .filter(animal -> Period.between(animal.getBirthdate(), LocalDate.now()).getYears() > 5)
                .filter(animal -> animal.getCost().doubleValue() > averageCost)
                .sorted(Comparator.comparing(Animal::getBirthdate))
                .collect(Collectors.collectingAndThen(Collectors.toList(), CopyOnWriteArrayList::new));

        writeToFile("findOldAndExpensive.json", answer);

        return answer;
    }

    @Override
    public List<String> findMinConstAnimals(List<Animal> animals) throws IllegalArraySizeException {
        Objects.requireNonNull(animals);
        if (animals.size() < 3) throw new IllegalArraySizeException("Размер массива должен быть равен или больше 3");
        return animals.stream()
                .filter(Objects::nonNull)
                .sorted(Comparator.comparing(Animal::getCost))
                .limit(3)
                .map(Animal::getName)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.collectingAndThen(Collectors.toList(), CopyOnWriteArrayList::new));
    }

    private void writeToFile(String file, Object obj) {
        Path path = Paths.get(resultsDirectory + "/" + file);
        if (Files.notExists(path)) {
            try {
                Files.createFile(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            objectMapper.writeValue(path.toFile(), obj);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
