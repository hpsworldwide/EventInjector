package com.hpsworldwide.powercard.eventinjector.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * A configuration class for mapping database-related properties: url, username, password...)
 */

@ConfigurationProperties(prefix = "spring.datasource")
class DatabaseProperties {
    // Properties to be mapped from the configuration file
    String url;
    String username;
    String password;
    String driverClassName;
    String jndiName;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getJndiName() {
        return jndiName;
    }

    public void setJndiName(String jndiName) {
        this.jndiName = jndiName;
    }

}