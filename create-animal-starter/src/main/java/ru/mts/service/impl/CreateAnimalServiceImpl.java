package ru.mts.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.mts.annotation.Logging;
import ru.mts.entity.Animal;
import ru.mts.entity.Habitat;
import ru.mts.entity.Provider;
import ru.mts.exception.NegativeNumberException;
import ru.mts.properties.AnimalsProperties;
import ru.mts.properties.HabitatProperties;
import ru.mts.properties.ProviderProperties;
import ru.mts.repository.AnimalRepository;
import ru.mts.service.AnimalType;
import ru.mts.service.CreateAnimalService;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
@RequiredArgsConstructor
public class CreateAnimalServiceImpl implements CreateAnimalService {

    @Value("${application.animal.log.path}")
    private String logFilePath;
    @Value("${application.animal.log.enabled:false}")
    private String logEnabled;

    @Value("${application.animal.secret-information.path}")
    private String secretInformationPath;

    private AnimalType animalType;

    private final AnimalsProperties animalsProperties;
    private final HabitatProperties habitatProperties;
    private final ProviderProperties providerProperties;
    private final AnimalRepository animalRepository;

    @Override
    public Map<String, List<Animal>> createMapRandomAnimals(int n) {
        if (n < 0) throw new NegativeNumberException(n);
        if (n == 0) return Collections.emptyMap();
        Map<String, List<Animal>> animalsMap = new ConcurrentHashMap<>();
        int i = n;
        do {
            i--;
            Animal animal = createRandomAnimal();
            List<Animal> animalList = animalsMap.getOrDefault(animal.getClass().getSimpleName(), new CopyOnWriteArrayList<>());
            animalList.add(animal);
            animalsMap.put(animal.getClass().getSimpleName(), animalList);
        } while (i > 0);
        return animalsMap;
    }

    @Override
    @Logging(entering = true)
    public List<Animal> createListRandomAnimals(int n) {
        if (n < 0) throw new NegativeNumberException(n);
        List<Animal> animals = new CopyOnWriteArrayList<>();
        for (int i = 0; i < n; i++) {
            animals.add(createRandomAnimal());
        }
        return animals;
    }

    @Override
    @Logging(entering = true, exiting = true, level = "WARN")
    public Animal createRandomAnimal() {
        Animal animal = new Animal();

        Random random = new Random();
        List<String> namesList = animalsProperties.getNames();
        animal.setName(namesList.get(random.nextInt(namesList.size())));
        animal.setBirthdate(LocalDate.now());
        animal.setCost(BigDecimal.valueOf(random.nextInt(1000, 50000)));

        int providersCount = random.nextInt(1,3);
        Set<Provider> providers = new HashSet<>();
        for (int i = 0; i < providersCount; i++) {
            providers.add(createRandomProvider());
        }
        animal.setProviders(providers);

        int habitatsCount = random.nextInt(1, 3);
        Set<Habitat> habitats = new HashSet<>();
        for (int i = 0; i < habitatsCount; i++) {
            habitats.add(createRandonHabitat());
        }
        animal.setHabitats(habitats);
        animalRepository.save(animal);
        saveAnimalToFile(animal);
        return animal;
    }

    private Habitat createRandonHabitat() {
        Random random = new Random();
        return Habitat.builder()
                .area(habitatProperties.getArea().get(random.nextInt(habitatProperties.getArea().size())))
                .animals(new HashSet<>())
                .build();
    }

    private Provider createRandomProvider() {
        Random random = new Random();
        return Provider.builder()
                .name(providerProperties.getName().get(random.nextInt(providerProperties.getName().size())))
                .phone(providerProperties.getPhone().get(random.nextInt(providerProperties.getPhone().size())))
                .build();
    }

    private void saveAnimalToFile(Animal animal) {
        if (logEnabled.equals("false")) {
            return;
        }
        Path path = Paths.get(logFilePath);
        try {
            List<String> list = Files.readAllLines(path);
            String str = String.format("%d. %s %s %s %s",
                    list.size() + 1, animal.getBreed(), animal.getName(),
                    animal.getCost().toString(), animal.getBirthdate());
            list.add(str);
            Files.write(path, list);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    public void setAnimalType(AnimalType animalType) {
//        this.animalType = animalType;
//    }
//
//    public Animal getAnimal() {
//        return animalType.getAnimal();
//    }

}
