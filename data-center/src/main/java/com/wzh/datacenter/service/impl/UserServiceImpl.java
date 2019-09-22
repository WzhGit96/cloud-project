/*
 * Copyright © 2019-2019 Wzh.All rights reserved.
 */
package com.wzh.datacenter.service.impl;

import com.wzh.datacenter.dao.UsersMapper;
import com.wzh.datacenter.entity.User;
import com.wzh.datacenter.service.UserService;
import com.wzh.datacenter.util.CollectionUtil;
import com.wzh.datacenter.util.MapUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Wzh
 * @since 2019-9-22
 */
@Service
public class UserServiceImpl implements UserService{

	@Resource
	private UsersMapper usersMapper;

	private CollectionUtil collectionUtil = new CollectionUtil();

	@Override
	public int addUser(Map<String, Object> paraMap) {
		int result = usersMapper.addUser(paraMap);
		return result;
	}

	@Override
	public List<User> findAllUserByPage(Map<String, Object> paraMap) {
		List<User> list = usersMapper.findAllUserByPage(paraMap);
		return collectionUtil.isNotEmpty(list) ? list : new ArrayList<>();
	}
}
