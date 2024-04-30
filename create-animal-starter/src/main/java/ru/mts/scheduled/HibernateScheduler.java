package ru.mts.scheduled;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.mts.entity.Animal;
import ru.mts.repository.AnimalRepository;
import ru.mts.service.CreateAnimalService;

import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
@Slf4j
public class HibernateScheduler {
    private final AnimalRepository animalRepository;
    private final CreateAnimalService createAnimalService;

    @Scheduled(fixedDelay = 10, timeUnit = TimeUnit.SECONDS)
    private void scheduler() {
        createAnimalService.createRandomAnimal();
        log.info(animalRepository.findAll().toString());
    }
}
