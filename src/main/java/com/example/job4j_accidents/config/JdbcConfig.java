package com.example.job4j_accidents.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
public class JdbcConfig {

    @Bean
    public DataSource ds(final @Value("${jdbc.driver}") String driver,
                         final @Value("${jdbc.url}") String url,
                         final @Value("${jdbc.username}") String username,
                         final @Value("${jdbc.password}") String password) {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName(driver);
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        return ds;
    }

    @Bean
    public JdbcTemplate jdbc(final DataSource ds) {
        return new JdbcTemplate(ds);
    }

}
