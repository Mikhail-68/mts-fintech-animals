package ru.mts;

import ru.mts.createAnimal.CreateAnimalService;
import ru.mts.createAnimal.CreateAnimalServiceImpl;
import ru.mts.searchAnimal.SearchAnimalService;
import ru.mts.searchAnimal.SearchAnimalServiceImpl;

import java.math.BigDecimal;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        CreateAnimalService createService = new CreateAnimalServiceImpl();
        Animal[] animals = createService.create10Animals();

        SearchAnimalService searchService = new SearchAnimalServiceImpl();

        System.out.println(Arrays.toString(searchService.findLeapYearNames(animals)));
        System.out.println(Arrays.toString(searchService.findOlderAnimal(animals, 10)));

        animals = new CreateAnimalServiceImpl().create10CustomAnimals();
        searchService.findDuplicate(animals);
    }
}