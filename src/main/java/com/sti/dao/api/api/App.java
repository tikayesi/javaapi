package com.sti.dao.api.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

import com.sti.dao.config.DaoSpringConfig;

@Configuration
@EnableAutoConfiguration
@Component
@ComponentScan({"com.sti.dao.controller"})
@EnableJpaRepositories({"com.sti.dao.repository"})
@EntityScan({"com.sti.dao.model"})
@Import({DaoSpringConfig.class})
public class App {
	
		public static void main( String[] args)
		{
			SpringApplication.run(App.class, args);
		}
	}


