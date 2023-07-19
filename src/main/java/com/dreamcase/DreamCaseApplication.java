package com.dreamcase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.dreamcase.fallback.GlobalExceptionHandler;

@SpringBootApplication
@Import(GlobalExceptionHandler.class)
public class DreamCaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(DreamCaseApplication.class, args);
	}

}
