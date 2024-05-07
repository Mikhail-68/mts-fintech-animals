package ru.mts.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = {"id", "animals"})
@ToString(exclude = "animals")
@Entity
@Table(schema = "animals")
public class Habitat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_area")
    private Integer id;
    private String area;

    @ManyToMany(mappedBy = "habitats", fetch = FetchType.EAGER)
    private Set<Animal> animals;

}
