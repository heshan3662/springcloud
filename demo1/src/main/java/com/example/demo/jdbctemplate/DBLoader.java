package com.example.demo.jdbctemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class DBLoader {
    @Bean(name = "mysqlJdbcTemplate")
    public JdbcTemplate primaryJdbcTemplate(@Qualifier("mysql") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "sqlserverJdbcTemplate")
    public JdbcTemplate secondaryJdbcTemplate(@Qualifier("sqlserver") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

}
