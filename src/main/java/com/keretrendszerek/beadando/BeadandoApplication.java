package com.keretrendszerek.beadando;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class BeadandoApplication{
	public static void main(String[] args) {
		SpringApplication.run(BeadandoApplication.class, args);
	}
}
