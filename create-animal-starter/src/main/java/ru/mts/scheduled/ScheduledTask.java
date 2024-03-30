package ru.mts.scheduled;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.mts.exception.IllegalArraySizeException;
import ru.mts.exception.NegativeNumberException;
import ru.mts.model.Animal;
import ru.mts.service.CreateAnimalService;
import ru.mts.service.OperationsWithAnimalsService;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class ScheduledTask {

    private final OperationsWithAnimalsService operationsWithAnimalsService;
    private final CreateAnimalService createAnimalService;

    private List<Animal> animals;

    @Autowired
    public ScheduledTask(OperationsWithAnimalsService operationsWithAnimalsService, CreateAnimalService createAnimalService) {
        this.operationsWithAnimalsService = operationsWithAnimalsService;
        this.createAnimalService = createAnimalService;
    }

    @PostConstruct
    public void init() {
        animals = createAnimalService.createListRandomAnimals(10);
        animals.set(3, animals.get(7));
        animals.set(8, animals.get(5));

        ScheduledExecutorService es = Executors.newScheduledThreadPool(2);
        es.scheduleAtFixedRate(() -> {
            Thread.currentThread().setName("printDuplicatedThread");
            System.out.println("Current thread: " + Thread.currentThread().getName() +
                    "\n\t " + operationsWithAnimalsService.findDuplicate(animals));
        }, 0, 5, TimeUnit.SECONDS);
        es.scheduleWithFixedDelay(() -> {
            Thread.currentThread().setName("findAverageAgeThread");
            System.out.println("Current thread: " + Thread.currentThread().getName() +
                    "\n\t " + operationsWithAnimalsService.findAverageAge(animals));
        }, 0, 10, TimeUnit.SECONDS);
    }

    @Scheduled(fixedDelay = 1, timeUnit = TimeUnit.MINUTES)
    public void print() {
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
