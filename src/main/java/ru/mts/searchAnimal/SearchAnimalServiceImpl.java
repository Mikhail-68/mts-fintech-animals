package ru.mts.searchAnimal;

import ru.mts.Animal;

import java.time.LocalDate;

public class SearchAnimalServiceImpl implements SearchAnimalService {

    @Override
    public String[] findLeapYearNames(Animal[] animals) {
        if (animals == null) return null;
        String[] names = new String[animals.length];
        int crntInd = 0;
        for (Animal animal : animals) {
            if (animal == null) continue;
            if (animal.getBirthdate().isLeapYear()) {
                names[crntInd++] = animal.getName();
            }
        }
        return names;
    }

    @Override
    public Animal[] findOlderAnimal(Animal[] animals, int N) {
        if (animals == null) return null;

        Animal[] olderAnimals = new Animal[animals.length];
        int crntInd = 0;

        for (int i = 0; i < animals.length; i++) {
            if (animals[i] == null) continue;
            if (LocalDate.now().getYear() - animals[i].getBirthdate().getYear() > N) {
                olderAnimals[crntInd++] = animals[i];
            }
        }
        return olderAnimals;
    }

    @Override
    public void findDuplicate(Animal[] animals) {
        if(animals == null) return;
        for (int i = 0; i < animals.length - 1; i++) {
            if(animals[i] == null) continue;
            for (int j = i + 1; j < animals.length; j++) {
                if(animals[j] == null) continue;
                if(animals[i].equals(animals[j])) {
                    System.out.println("Duplicate: name=" + animals[i].getName() + " birthdate=" + animals[i].getBirthdate());
                    animals[j] = null;
                    break;
                }
            }
        }
    }
}
