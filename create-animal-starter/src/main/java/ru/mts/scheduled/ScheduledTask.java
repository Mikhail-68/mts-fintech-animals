package ru.mts.scheduled;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.mts.repository.AnimalsRepositoryImpl;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Component
public class ScheduledTask {

    private final AnimalsRepositoryImpl animalsRepository;

    public ScheduledTask(AnimalsRepositoryImpl animalsRepository) {
        this.animalsRepository = animalsRepository;
    }

    @Scheduled(fixedDelay = 1, timeUnit = TimeUnit.MINUTES)
    public void print() {
        System.out.println("\n\n=== repo === " + animalsRepository.getAnimals());
        System.out.println("\n\n=== olderAnimals === " + animalsRepository.findOlderAnimal(10));
        System.out.println("\n\n=== leapYearAnimals === " + animalsRepository.findLeapYearNames());
        System.out.println("\n\n=== findDuplicate === " + animalsRepository.findDuplicate());
    }
}
