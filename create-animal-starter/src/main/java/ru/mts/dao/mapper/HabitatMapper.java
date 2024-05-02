package ru.mts.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.mts.entity.Habitat;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class HabitatMapper implements RowMapper<Habitat> {
    @Override
    public Habitat mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Habitat.builder()
                .id(rs.getInt("id_area"))
                .area(rs.getString("area"))
                .build();
    }
}
