/*
 * Copyright © 2019-2019 Wzh.All rights reserved.
 */

package com.wzh.front.controller;

import com.wzh.front.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author Wzh
 * @since 2019-10-14
 */
@RestController
@RequestMapping("/news")
public class NewsController {

	@Autowired
	NewsService newsService;

	@PostMapping("/getNews")
	public Map<String, Object> getNews() {
		return newsService.getNews();
	}

}
