package ru.mts.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.mts.exception.NegativeNumberException;
import ru.mts.model.*;
import ru.mts.properties.AnimalsProperties;
import ru.mts.service.AnimalType;
import ru.mts.service.CreateAnimalService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class CreateAnimalServiceImpl implements CreateAnimalService {

    @Value("${application.animal.log.path}")
    private String logFilePath;
    @Value("${application.animal.log.enabled:false}")
    private String logEnabled;

    @Value("${application.animal.secret-information.path}")
    private String secretInformationPath;

    private AnimalType animalType;
    private final AnimalsProperties animalsProperties;

    @Autowired
    public CreateAnimalServiceImpl(AnimalsProperties animalsProperties) {
        this.animalsProperties = animalsProperties;
    }

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
    public List<Animal> createListRandomAnimals(int n) {
        if (n < 0) throw new NegativeNumberException(n);
        List<Animal> animals = new CopyOnWriteArrayList<>();
        for (int i = 0; i < n; i++) {
            animals.add(createRandomAnimal());
        }
        return animals;
    }

    @Override
    public Animal createRandomAnimal() {
        int countAnimals = 4;
        AbstractAnimal someAnimal;
        int id = new Random().nextInt(countAnimals);
        someAnimal = switch (id) {
            case 0 -> new Wolf();
            case 1 -> new Shark();
            case 2 -> new Dog();
            default -> new Cat();
        };
        Random random = new Random();
        List<String> namesList = animalsProperties.getNames();
        someAnimal.setName(namesList.get(random.nextInt(namesList.size())));

        Path path = Paths.get(secretInformationPath);
        List<String> listSecretInformation;
        try {
            listSecretInformation = Files.readAllLines(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        someAnimal.setSecretInformation(listSecretInformation.get(random.nextInt(listSecretInformation.size())));
        saveAnimalToFile(someAnimal);
        return someAnimal;
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
                    animal.getCost().toString(),animal.getBirthdate());
            list.add(str);
            Files.write(path, list);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setAnimalType(AnimalType animalType) {
        this.animalType = animalType;
    }

    public Animal getAnimal() {
        return animalType.getAnimal();
    }

}
