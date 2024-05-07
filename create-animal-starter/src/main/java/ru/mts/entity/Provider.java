package ru.mts.entity;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = "id")
@Entity
@Table(schema = "animals")
public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_provider")
    private int id;
    private String name;
    private String phone;

    public Provider(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }
}
