package ru.mts.scheduled;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.mts.exception.IllegalArraySizeException;
import ru.mts.exception.NegativeNumberException;
import ru.mts.model.Animal;
import ru.mts.service.CreateAnimalService;
import ru.mts.service.OperationsWithAnimalsService;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class ScheduledTask {

    private final OperationsWithAnimalsService operationsWithAnimalsService;
    private final CreateAnimalService createAnimalService;

    @Autowired
    public ScheduledTask(OperationsWithAnimalsService operationsWithAnimalsService, CreateAnimalService createAnimalService) {
        this.operationsWithAnimalsService = operationsWithAnimalsService;
        this.createAnimalService = createAnimalService;
    }

    @Scheduled(fixedDelay = 1, timeUnit = TimeUnit.MINUTES)
    public void print() {
        List<Animal> animals = createAnimalService.createListRandomAnimals(10);

        System.out.println("\n\n");
        try {
            System.out.println("\n=== olderAnimals === ");
            System.out.print(operationsWithAnimalsService.findOlderAnimal(animals, -1));
        } catch (NegativeNumberException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\n=== leapYearAnimals === " + operationsWithAnimalsService.findLeapYearNames(animals));
        System.out.println("\n=== findDuplicate === " + operationsWithAnimalsService.findDuplicate(animals));
        System.out.println("\n=== findAverageAge === " + operationsWithAnimalsService.findAverageAge(animals));
        System.out.println("\n=== findOldAndExpensive === " + operationsWithAnimalsService.findOldAndExpensive(animals));

        try {
            System.out.println("\n=== findMinConstAnimals === ");
            System.out.print(operationsWithAnimalsService.findMinConstAnimals(createAnimalService.createListRandomAnimals(1)));
        } catch (NullPointerException e) {
            System.out.println(e.getClass().getSimpleName());
        } catch (IllegalArraySizeException e) {
            System.out.println(e.getMessage());
        }
    }
}
