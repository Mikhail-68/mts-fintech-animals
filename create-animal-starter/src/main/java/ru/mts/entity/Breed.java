package ru.mts.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = "id")
@Entity
@Table(schema = "animals")
public class Breed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_breed")
    private int id;
    private String breed;

    @JsonIgnore
    @OneToMany(mappedBy = "breed")
    private List<Animal> animals;
}
