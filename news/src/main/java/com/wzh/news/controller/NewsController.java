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

	/**
	 * 新闻条数
	 */
	private static final int NUMS = 8;

	@PostMapping("/getNews")
	public List<News> getNews() {
		List<News> list = newsService.getNews();
		// 这里只获取八条新闻
		List<News> eightNews = new ArrayList<>();
		for (int i = 0; i < NUMS; i++) {
			if (list.get(i) == null) {
				break;
			}
			eightNews.add(list.get(i));
		}
		return eightNews;
	}

}
