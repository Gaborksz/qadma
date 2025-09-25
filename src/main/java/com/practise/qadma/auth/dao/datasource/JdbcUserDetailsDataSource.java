package com.practise.qadma.auth.dao.datasource;


import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "defaultSpringUsersEntityManagerFactory",
        transactionManagerRef = "defaultSpringUsersTransactionManager",
        basePackages = "com.practise.qadma.auth.dao")
public class JdbcUserDetailsDataSource {

    @Bean(name = "defaultSpringUsers")
    DataSource dataSource() {

        return DataSourceBuilder.create()
                .url("jdbc:mysql://localhost:3306/default_spring_users")
                .username("default_spring_admin")
                .password("admin")
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .build();
    }


    @Bean(name = "defaultSpringUsersEntityManagerFactory")
    LocalContainerEntityManagerFactoryBean entityManagerFactory(
            @Qualifier("defaultSpringUsers") DataSource dataSource) {

        LocalContainerEntityManagerFactoryBean em =
                new LocalContainerEntityManagerFactoryBean();

        em.setDataSource(dataSource);
        em.setPackagesToScan("com.practise.qadma.auth.entity");
        em.setPersistenceUnitName("SpringDefaultUsersDb");

        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        em.setJpaProperties(getHibernateProperties());

        return em;
    }

    @Bean(name = "defaultSpringUsersTransactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("defaultSpringUsersEntityManagerFactory") EntityManagerFactory entityManagerFactory) {

        return new JpaTransactionManager(entityManagerFactory);
    }

    private static Properties getHibernateProperties() {

        Properties properties = new Properties();
        properties.setProperty("hibernate.show_sql", "true");
//        properties.setProperty("hibernate.hbm2ddl.auto", "update");                               //  ←   auto creates / updates  schemas
//        properties.setProperty("hibernate.archive.autodetection", "class");                      //  ←  auto scan for all @Entity so no need to manually add folders

        return properties;
    }
}
