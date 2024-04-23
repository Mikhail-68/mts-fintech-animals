package ru.mts.entity;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = "id")
public class AnimalType {
    private int id;
    private String type;
    private boolean isWild;
}
