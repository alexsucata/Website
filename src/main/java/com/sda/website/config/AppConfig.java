package com.sda.website.config;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.sda.website.entity")
@ComponentScan("com.sda.website")
@EnableJpaRepositories("com.sda.website.repository")
public class AppConfig {



}
