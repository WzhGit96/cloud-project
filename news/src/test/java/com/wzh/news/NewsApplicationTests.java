package com.wzh.news;

import com.wzh.news.entity.News;
import com.wzh.news.util.NewsUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NewsApplicationTests {

	@Test
	public void contextLoads() throws IOException {
		List<News> list = NewsUtil.getNews();
		System.out.println(list);
	}

}
