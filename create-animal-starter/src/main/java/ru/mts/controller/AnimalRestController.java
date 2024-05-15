package ru.mts.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.mts.entity.Animal;
import ru.mts.repository.AnimalRepository;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/animal")
public class AnimalRestController {
    private final AnimalRepository animalRepository;

    @GetMapping
    public List<Animal> getAll(){
        return animalRepository.findAll();
    }

    @PostMapping
    public Animal save(@RequestBody Animal animal) {
        return animalRepository.save(animal);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        animalRepository.deleteById(id);
    }
}
