package com.springboot.zipkin.demo.springbootzipkin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.sleuth.Sampler;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@EnableEurekaClient
@SpringBootApplication
@CrossOrigin
public class UserGreetingService {
	
	@Autowired
	private DiscoveryClient discoveryClient;

	@RequestMapping("/api/user/greet")
	public String greet() {
		List<ServiceInstance> userNameServiceInstances = discoveryClient.getInstances("user-name-service");
		List<ServiceInstance> userAddressServiceInstances = discoveryClient.getInstances("user-address-service");
		System.out.println("userNameServiceInstances: "+ userNameServiceInstances.size());
		System.out.println("userAddressServiceInstances: "+ userAddressServiceInstances.size());
		
		String greetingMsg = "Hello";

		String userName = restTemplate().getForObject("http://user-name-service/api/user/name", String.class);
		String userAddress = restTemplate().getForObject("http://user-address-service/api/user/address", String.class);

		return greetingMsg + " " + userName + "!\n\n" + userAddress;
	}

	@LoadBalanced
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
	@Bean
    public Sampler defaultSampler() {
        return new AlwaysSampler();
    }
	public static void main(String[] args) {
		SpringApplication.run(UserGreetingService.class, "--spring.application.name=user-greeting-service",
				"--spring.zipkin.baseUrl: http://localhost:9411/" );
	}
}
