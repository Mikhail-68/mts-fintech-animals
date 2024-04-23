package ru.mts.entity;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = "id")
public class Creature {
    private int id;
    private String name;
    private int typeId;
    private int age;
}
