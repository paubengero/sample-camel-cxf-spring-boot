package com.paulo.practice.application.samplecamelcxfspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({"classpath:spring/camel-context.xml"})
public class SampleCamelCxfSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(SampleCamelCxfSpringBootApplication.class, args);
	}

}
