package ru.mts.scheduled;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.mts.dao.*;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
@Slf4j
public class JdbcScheduler {

    private final CreatureDao creatureDao;
    private final HabitatDao habitatDao;
    private final ProviderDao providerDao;
    private final AnimalTypeDao animalTypeDao;
    private final AnimalsHabitatsDao animalsHabitatsDao;
    private final AnimalsProvidersDao animalsProvidersDao;

    @Scheduled(fixedDelay = 10, timeUnit = TimeUnit.SECONDS)
    private void scheduler() {
        log.info(creatureDao.getAll().toString());
        log.info(habitatDao.getAll().toString());
        log.info(providerDao.getAll().toString());
        log.info(animalTypeDao.getAll().toString());
        log.info(animalsHabitatsDao.getAll().toString());
        log.info(animalsProvidersDao.getAll().toString());
    }
}
