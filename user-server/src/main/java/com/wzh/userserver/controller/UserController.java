package com.wzh.userserver.controller;

import com.wzh.userserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/hello")
	public String sayHello() {
		return userService.hello();
	}

}
