package com.hpsworldwide.powercard.eventinjector.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.hpsworldwide.powercard.eventinjector")
public class AppConfig {
    /**
     * This method defines a bean for DatabaseProperties.
     *
     * @return DatabaseProperties bean instance.
     */

    @Bean
    public DatabaseProperties databaseProperties() {
        return new DatabaseProperties();
    }

    /**
     * This method configures and defines a bean for the DataSource.
     *
     * @return DataSource bean configured with database properties: (username, password, Url...)
     */

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(databaseProperties().getDriverClassName());
        dataSource.setUrl(databaseProperties().getUrl());
        dataSource.setUsername(databaseProperties().getUsername());
        dataSource.setPassword(databaseProperties().getPassword());
        return dataSource;
    }

    /**
     * This method configures and defines a bean for the EntityManagerFactory using Hibernate.
     *
     * @return LocalContainerEntityManagerFactoryBean configured for JPA.
     * @throws SQLException if there is a SQL-related exception.
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws SQLException {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        //  vendorAdapter.setDatabase(Database.MYSQL);
        vendorAdapter.setDatabase(Database.ORACLE);
        vendorAdapter.setShowSql(true);
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("com.hpsworldwide.powercard.eventinjector.entity");
        factory.setDataSource(dataSource());
        return factory;
    }

    /**
     * This method configures and defines a bean for the JpaTransactionManager.
     *
     * @return PlatformTransactionManager configured for JPA.
     * @throws SQLException if there is a SQL-related exception.
     */
    @Bean
    public PlatformTransactionManager transactionManager() throws SQLException {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return txManager;
    }

}
