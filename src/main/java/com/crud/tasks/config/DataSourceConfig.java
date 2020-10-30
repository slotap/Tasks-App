package com.crud.tasks.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
class DatasourceConfig {
    @Bean
    public DataSource datasource() {
        return DataSourceBuilder.create()
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .url("jdbc:mysql://localhost:3306/task_crud?serverTimezone=Europe/Warsaw&useSSL=False&allowPublicKeyRetrieval=true")
                .username("user1")
                .password("password")
                .build();
    }
}
