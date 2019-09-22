/*
 * Copyright © 2019-2019 Wzh.All rights reserved.
 */
package com.wzh.datacenter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author Wzh
 * @since 2019-9-22
 */
@EnableEurekaClient
@SpringBootApplication
public class DataCenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataCenterApplication.class, args);
	}

	@Bean
	@Autowired
	@LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
