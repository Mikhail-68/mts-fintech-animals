package ru.mts.dao.mapper;

import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.mts.entity.Creature;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@NoArgsConstructor
public class CreatureMapper implements RowMapper<Creature> {
    @Override
    public Creature mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Creature.builder()
                .id(rs.getInt("id_creature"))
                .name(rs.getString("name"))
                .typeId(rs.getInt("type_id"))
                .age(rs.getInt("age"))
                .build();
    }
}
