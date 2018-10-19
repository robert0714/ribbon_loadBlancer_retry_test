package com.springboot.zipkin.demo.springbootzipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.sleuth.Sampler;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
@RestController
@SpringBootApplication 
@EnableEurekaClient
public class UserNameService {
	
	@RequestMapping("/api/user/name")
	public String getUserName() {
		
		try {
			Thread.sleep(1000); 
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return "John Smith";
	}
    @Bean
    public Sampler defaultSampler() {
        return new AlwaysSampler();
    }
	
	public static void main(String[] args) {
		SpringApplication.run(UserNameService.class,
				"--spring.application.name=user-name-service",
				"--spring.zipkin.baseUrl: http://localhost:9411/" 
		);
	}
}
