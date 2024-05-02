package ru.mts.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.mts.dao.mapper.HabitatMapper;
import ru.mts.entity.Habitat;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class HabitatDao {
    private final JdbcTemplate jdbcTemplate;
    private final HabitatMapper habitatMapper;

    public List<Habitat> getAll() {
        return jdbcTemplate.query("select * from animals.habitat", habitatMapper);
    }
}
