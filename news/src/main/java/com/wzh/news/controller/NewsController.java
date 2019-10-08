/*
 * Copyright © 2019-2019 Wzh.All rights reserved.
 */

package com.wzh.news.controller;

import com.wzh.news.entity.News;
import com.wzh.news.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Wzh
 * @since 2019-10-02
 */
@RestController
@RequestMapping("/news")
public class NewsController {

	@Autowired
	private NewsService newsService;

	@PostMapping("/getNews")
	public Map<String, Object> getNews(HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "http://localhost:8803");
		List<News> list = newsService.getNews();
		// 这里只获取八条新闻
		List<News> eightNews = new ArrayList<>();
		for (int i = 0; i < 8; i++) {
			if (list.get(i) == null) {
				break;
			}
			eightNews.add(list.get(i));
		}
		Map<String, Object> result = new HashMap<>();
		result.put("rows", eightNews);
		return result;
	}

}
