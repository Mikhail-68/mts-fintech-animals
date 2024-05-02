package ru.mts.entity;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = "id")
public class Provider {
    private int id;
    private String name;
    private String phone;
}
