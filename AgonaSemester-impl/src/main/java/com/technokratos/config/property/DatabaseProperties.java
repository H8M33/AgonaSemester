package com.technokratos.config.property;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class DatabaseProperties {

    private final String url;

    private final String username;

    private final String password;

    private final int poolSize;

    private final String driverClassName;

    public DatabaseProperties(@Value("${db.url}") String url,
                              @Value("${db.username}") String username,
                              @Value("${db.password}") String password,
                              @Value("${db.hikari.max-pool-size}") int poolSize,
                              @Value("${db.driver.classname}") String driverClassName) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.poolSize = poolSize;
        this.driverClassName = driverClassName;
    }
}
