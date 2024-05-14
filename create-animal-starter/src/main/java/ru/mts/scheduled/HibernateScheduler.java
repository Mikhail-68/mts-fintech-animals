package ru.mts.scheduled;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.mts.repository.AnimalRepository;
import ru.mts.service.CreateAnimalService;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
@Slf4j
public class HibernateScheduler {
    private final AnimalRepository animalRepository;

    @Scheduled(fixedDelay = 10, timeUnit = TimeUnit.SECONDS)
    private void scheduler() {
        log.info(animalRepository.findAll().toString());
    }
}
