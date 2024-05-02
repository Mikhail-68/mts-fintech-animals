package ru.mts.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.mts.entity.Provider;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ProviderMapper implements RowMapper<Provider> {
    @Override
    public Provider mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Provider.builder()
                .id(rs.getInt("id_provider"))
                .name(rs.getString("name"))
                .phone(rs.getString("phone"))
                .build();
    }
}
