
package com.xender.practical;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class AuthApplication {

	public static void main(String[] args) {
		System.out.println("Start");
		SpringApplication.run(AuthApplication.class, args);
		System.out.println("End");
	}
	
}
