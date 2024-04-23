package ru.mts.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.mts.entity.AnimalType;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class AnimalTypeMapper implements RowMapper<AnimalType> {
    @Override
    public AnimalType mapRow(ResultSet rs, int rowNum) throws SQLException {
        return AnimalType.builder()
                .id(rs.getInt("id_type"))
                .type(rs.getString("type"))
                .isWild(rs.getBoolean("is_wild"))
                .build();
    }
}
