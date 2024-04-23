package ru.mts.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.mts.entity.AnimalsProviders;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class AnimalsProvidersMapper implements RowMapper<AnimalsProviders> {
    @Override
    public AnimalsProviders mapRow(ResultSet rs, int rowNum) throws SQLException {
        return AnimalsProviders.builder()
                .animalTypeId(rs.getInt("id_animal_type"))
                .providerId(rs.getInt("id_provider"))
                .build();
    }
}
