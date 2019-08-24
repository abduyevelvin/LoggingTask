package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.dao.EventService;

/**
 * Java class including main method for starting boot application
 * Getting EventService as a bean for using method and saving event in DB
 * 
 * @author Abduyev Elvin
 * 
 */
@SpringBootApplication
@EnableJpaRepositories
public class LogingTaskApplication {

	public static void main(String[] args) {
		//SpringApplication.run(LogingTaskApplication.class, args);
		ApplicationContext context = SpringApplication.run(LogingTaskApplication.class, args);

		EventService dao = context.getBean(EventService.class);
		dao.createEvent();
	}
}
