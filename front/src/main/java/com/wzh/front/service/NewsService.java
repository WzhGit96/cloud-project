/*
 * Copyright © 2019-2019 Wzh.All rights reserved.
 */

package com.wzh.front.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NewsService {

	@Autowired
	RestTemplate restTemplate;

	public Map<String, Object> getNews() {
		List list =  restTemplate.postForObject("http://new-service/news/getNews", null, List.class);
		Map<String, Object> result = new HashMap<>();
		result.put("rows", list);
		return result;
	}

}
