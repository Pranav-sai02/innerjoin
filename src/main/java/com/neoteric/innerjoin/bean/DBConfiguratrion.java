package com.neoteric.innerjoin.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;


@Configuration
public class DBConfiguratrion {


    @Autowired
    DatabaseProperties databaseProperties;

    @Bean
    public DataSource dataSource(DatabaseProperties dbProps) {
        return DataSourceBuilder.create()
                .url(dbProps.getUrl())
                .username(dbProps.getUsername())
                .password(dbProps.getPassword())
                .driverClassName(dbProps.getDriverClassName())
                .build();
    }
}
