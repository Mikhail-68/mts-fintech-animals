package ru.mts.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = "id")
@Entity
@Table(schema = "animals")
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_animal")
    private Integer id;
    private String name;
    private BigDecimal cost;
    private String character;
    private LocalDate birthdate;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private AnimalType animalType;

    @ManyToOne
    @JoinColumn(name = "breed_id")
    private Breed breed;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            schema = "animals",
            name = "animals_habitats",
            joinColumns = @JoinColumn(name = "id_animal"),
            inverseJoinColumns = @JoinColumn(name = "id_habitat")
    )
    private Set<Habitat> habitats = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            schema = "animals",
            name = "animals_providers",
            joinColumns = @JoinColumn(name = "id_animal"),
            inverseJoinColumns = @JoinColumn(name = "id_provider")
    )
    private Set<Provider> providers = new HashSet<>();
}
