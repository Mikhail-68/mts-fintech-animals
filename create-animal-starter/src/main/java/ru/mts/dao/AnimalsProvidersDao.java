package ru.mts.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.mts.dao.mapper.AnimalsProvidersMapper;
import ru.mts.entity.AnimalsProviders;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AnimalsProvidersDao {
    private final JdbcTemplate jdbcTemplate;
    private final AnimalsProvidersMapper animalsProvidersMapper;

    public List<AnimalsProviders> getAll() {
        return jdbcTemplate.query("select * from animals.animals_providers", animalsProvidersMapper);
    }
}
