/*
 * Copyright © 2019-2019 Wzh.All rights reserved.
 */
package com.wzh.datacenter;

import com.wzh.datacenter.entity.User;
import com.wzh.datacenter.service.UserService;
import com.wzh.datacenter.util.MapUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Wzh
 * @since 2019-9-22
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTests {

	@Resource(name = "userServiceImpl")
	private UserService userService;


	private MapUtils mapUtils = new MapUtils();

	private Map<String, Object> map = new HashMap<>();


	private String account = "tesdst";
	private String pwd = "123asd";
	private String email="2187asd362@qq.com";
	private Integer ulevel = 0;
	private Integer yc = 0;
	private Integer dove = 0;
	private Integer status = 1;

	@Test
	public void testAddUser() {
		User user = new User();
		user.setAccount(account);
		user.setPwd(pwd);
		user.setEmail(email);
		user.setUlevel(ulevel);
		user.setYc(yc);
		user.setDove(dove);
		user.setStatus(status);
		Map<String, Object> map = mapUtils.toMap(user);
		int result = userService.addUser(map);
		Assert.assertEquals(1, result);
	}

	@Test
	public void testFindAllUser() {
		map.clear();
		List<User> list = userService.findAllUserByPage(map);
		System.out.println(list.toString());
	}

	@Test
	public void testUpdateUser() {
		map.clear();
		map.put("name", "rick");
		map.put("id", 3);
		Assert.assertEquals(1, userService.updateUser(map));
	}

	@Test
	public void testLogin() {
		map.clear();
		map.put("account", "aslkd89@129.com");
		map.put("email", "aslkd89@129.com");
		map.put("pwd", "asdkhsadh");
		System.out.println(userService.login(map));
	}

	@Test
	public void testDeleteUser() {
		map.clear();
		map.put("id", 3);
		Assert.assertEquals(1, userService.deleteUser(map));
	}

}
