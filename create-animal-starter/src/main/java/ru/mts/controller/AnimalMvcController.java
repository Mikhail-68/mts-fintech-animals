package ru.mts.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mts.entity.Animal;
import ru.mts.repository.AnimalRepository;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mvc/animal")
public class AnimalMvcController {
    private final AnimalRepository animalRepository;

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("animalList", animalRepository.findAll(Sort.by(Sort.Direction.ASC, "id")));
        return "index";
    }

    @GetMapping("/edit/{id}")
    public String editView(@PathVariable Integer id, Model model) throws ChangeSetPersister.NotFoundException {
        model.addAttribute("animal", animalRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new));
        return "edit";
    }

    @GetMapping("/add")
    public String viewAdd(@ModelAttribute Animal animal) {
        return "add";
    }

    @PostMapping(value = "/add", params = "action=add")
    public String add(Animal animal) {
        animalRepository.save(animal);
        return "redirect:/mvc/animal";
    }

    @PostMapping(value = "/add", params = "action=cancel")
    public String cancel(Animal animal) {
        return "redirect:/mvc/animal";
    }

    @PostMapping(value = "/update/{id}", params = "action=update")
    public String update(@PathVariable("id") Integer id, Model model, Animal animal) {
        Animal savedAnimal = animalRepository.findById(id).get();
        savedAnimal.setName(animal.getName());
        savedAnimal.setCost(animal.getCost());
        savedAnimal.setCharacter(animal.getCharacter());
        savedAnimal.setBirthdate(animal.getBirthdate());
        animalRepository.save(savedAnimal);
        return "redirect:/mvc/animal";
    }

    @PostMapping(value = "/update/{id}", params = "action=cancel")
    public String cancel(@PathVariable("id") Integer id, Model model, Animal animal) {
        return "redirect:/mvc/animal";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id, Model model, Animal animal) {
        animalRepository.deleteById(animal.getId());
        return "redirect:/mvc/animal";
    }
}
