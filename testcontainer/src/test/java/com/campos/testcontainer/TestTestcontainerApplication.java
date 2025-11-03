package com.campos.testcontainer;

import org.springframework.boot.SpringApplication;

public class TestTestcontainerApplication {

	public static void main(String[] args) {
		SpringApplication.from(Startup::main).with(TestcontainersConfiguration.class).run(args);
	}

}
