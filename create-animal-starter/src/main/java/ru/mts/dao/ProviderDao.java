package ru.mts.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.mts.dao.mapper.ProviderMapper;
import ru.mts.entity.Provider;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProviderDao {
    private final JdbcTemplate jdbcTemplate;
    private final ProviderMapper providerMapper;

    public List<Provider> getAll() {
        return jdbcTemplate.query("select * from animals.provider", providerMapper);
    }
}
