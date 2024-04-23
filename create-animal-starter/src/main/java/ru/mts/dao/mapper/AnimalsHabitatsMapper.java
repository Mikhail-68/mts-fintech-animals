package ru.mts.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.mts.entity.AnimalsHabitats;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class AnimalsHabitatsMapper implements RowMapper<AnimalsHabitats> {
    @Override
    public AnimalsHabitats mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new AnimalsHabitats(
                rs.getInt("id_animal_type"),
                rs.getInt("id_area")
        );
    }
}
