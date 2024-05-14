package ru.mts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mts.entity.Animal;

public interface AnimalRepository extends JpaRepository<Animal, Integer> {
}
