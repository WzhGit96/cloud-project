package com.wzh.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {

	@Autowired
	RestTemplate restTemplate;

	public String sayHello() {
		return restTemplate.getForObject("http://user-server/hello?name=rick", String.class);
	}

}
