package ru.mts.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.mts.dao.mapper.AnimalsHabitatsMapper;
import ru.mts.entity.AnimalsHabitats;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AnimalsHabitatsDao {
    private final JdbcTemplate jdbcTemplate;
    private final AnimalsHabitatsMapper animalsHabitatsMapper;

    public List<AnimalsHabitats> getAll() {
        return jdbcTemplate.query("select * from animals.animals_habitats", animalsHabitatsMapper);
    }
}
