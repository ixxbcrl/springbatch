package com.example.springbatch.repository.jdbc;

import com.example.springbatch.model.Person;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PersonDAO {
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void PersonDAO(final DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);

        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public int[] batchUpdateJDBCTemplate(final List<Person> persons) {
        return jdbcTemplate.batchUpdate("INSERT INTO PERSON VALUES (?, ?, ?)", new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setLong(1, persons.get(i).getId());
                ps.setString(2, persons.get(i).getName());
                ps.setString(3, persons.get(i).getCountry());
            }

            @Override
            public int getBatchSize() {
                return 5;
            }
        });
    }
}
