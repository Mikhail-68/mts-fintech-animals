package ru.mts.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.mts.dao.mapper.CreatureMapper;
import ru.mts.entity.Creature;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CreatureDao {
    private final JdbcTemplate jdbcTemplate;
    private final CreatureMapper creatureMapper;

    public List<Creature> getAll() {
        return jdbcTemplate.query("select * from animals.creature", creatureMapper);
    }
}
