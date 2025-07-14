package com.BackendApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.PropertySource;

import java.util.Properties;

@SpringBootApplication
public class BackendApplication extends SpringBootServletInitializer {


	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);

	}

}
