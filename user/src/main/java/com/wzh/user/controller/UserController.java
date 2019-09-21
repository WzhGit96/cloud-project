package com.wzh.user.controller;

import com.wzh.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class UserController {

	@Autowired
	private UserService userService;


	@GetMapping("/hello")
	public String sayHello(String name) {
		return userService.sayHello() + name;
	}


}
