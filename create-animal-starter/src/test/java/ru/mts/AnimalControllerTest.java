package ru.mts;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.mts.controller.AnimalRestController;
import ru.mts.entity.Animal;
import ru.mts.repository.AnimalRepository;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AnimalRestController.class)
@ComponentScan(basePackages = "ru.mts")
class AnimalControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AnimalRepository animalRepository;

    @Test
    void getAll() throws Exception {
        Animal animal1 = new Animal();
        animal1.setId(1);
        Animal animal2 = new Animal();
        animal2.setId(2);
        List<Animal> animals = List.of(animal1, animal2);
        Mockito.when(animalRepository.findAll()).thenReturn(animals);

        mockMvc.perform(get("/rest/animal")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(jsonPath("$[1].id", Matchers.is(2)));
    }

    @Test
    void save() throws Exception {
        Animal animal = new Animal();
        mockMvc.perform(MockMvcRequestBuilders.post("/rest/animal")
                        .content(new ObjectMapper().writeValueAsString(animal))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        Mockito.verify(animalRepository, Mockito.times(1)).save(animal);
    }

    @Test
    void delete() throws Exception {
        int id = 1;
        mockMvc.perform(MockMvcRequestBuilders.delete("/rest/animal/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        Mockito.verify(animalRepository, Mockito.times(1)).deleteById(id);
    }
}