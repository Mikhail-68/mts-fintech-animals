package ru.mts.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.mts.dao.mapper.AnimalTypeMapper;
import ru.mts.entity.AnimalType;
import ru.mts.entity.Creature;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AnimalTypeDao {
    private final JdbcTemplate jdbcTemplate;
    private final AnimalTypeMapper animalTypeMapper;

    public List<AnimalType> getAll() {
        return jdbcTemplate.query("select * from animals.animal_type", animalTypeMapper);
    }
}
