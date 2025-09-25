package com.practise.qadma;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication
public class QadmaApplication {

    public static String BASEURL = "http://localhost:8080";

    public static void main(String[] args) {
        SpringApplication.run(QadmaApplication.class, args);
    }
}
