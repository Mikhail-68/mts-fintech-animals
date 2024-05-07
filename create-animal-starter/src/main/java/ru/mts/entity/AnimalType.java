package ru.mts.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = "id")
@Entity
@Table(name = "animal_type", schema = "animals")
public class AnimalType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_type")
    private int id;
    private String type;
    private boolean isWild;

    public AnimalType(String type, boolean isWild) {
        this.type = type;
        this.isWild = isWild;
    }
}
