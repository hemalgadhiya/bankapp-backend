package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.*;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		System.out.println("App started....");
		Map<String, String> env = System.getenv();
		for (String envName : env.keySet()) {
    	System.out.format("%s=%s%n", envName, env.get(envName));
		}

	}

}
