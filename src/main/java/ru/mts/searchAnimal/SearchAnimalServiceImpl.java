package ru.mts.searchAnimal;

import ru.mts.model.Animal;

import java.time.LocalDate;
import java.util.Arrays;

public class SearchAnimalServiceImpl implements SearchAnimalService {

    @Override
    public String[] findLeapYearNames(Animal[] animals) {
        if (animals == null) return null;

        String[] names = new String[0];
        int crntInd = 0;

        for (Animal animal : animals) {
            if (animal == null) continue;
            if (animal.getBirthdate().isLeapYear()) {
                names = Arrays.copyOf(names, crntInd + 1);
                names[crntInd++] = animal.getName();
            }
        }
        return names.length > 0 ? names : null;
    }

    @Override
    public Animal[] findOlderAnimal(Animal[] animals, int N) {
        if (animals == null) return null;

        Animal[] olderAnimals = new Animal[0];
        int crntInd = 0;

        for (int i = 0; i < animals.length; i++) {
            if (animals[i] == null || animals[i].getBirthdate() == null) continue;
            if (LocalDate.now().compareTo(animals[i].getBirthdate().plusYears(N)) > 0) {
                olderAnimals = Arrays.copyOf(olderAnimals, crntInd + 1);
                olderAnimals[crntInd++] = animals[i];
            }
        }
        return olderAnimals.length > 0 ? olderAnimals : null;
    }

    @Override
    public Animal[] findDuplicate(Animal[] animals) {
        if(animals == null) return null;

        Animal[] duplicateAnimals = new Animal[0];
        int crntInd = 0;

        for (int i = 0; i < animals.length - 1; i++) {
            if(animals[i] == null) continue;
            for (int j = i + 1; j < animals.length; j++) {
                if(animals[j] == null) continue;
                if(animals[i].equals(animals[j])) {
                    duplicateAnimals = Arrays.copyOf(duplicateAnimals, crntInd + 1);
                    duplicateAnimals[crntInd++] = animals[i];
                    animals[j] = null;
                    break;
                }
            }
        }
        return duplicateAnimals.length > 0 ? duplicateAnimals : null;
    }

    @Override
    public void printDuplicate(Animal[] animals) {
        Animal[] duplicateAnimals = findDuplicate(animals);

        if(duplicateAnimals == null) return;

        for(Animal animal : duplicateAnimals) {
            System.out.println("Duplicate: name=" + animal.getName() + " birthdate=" + animal.getBirthdate());
        }
    }
}
