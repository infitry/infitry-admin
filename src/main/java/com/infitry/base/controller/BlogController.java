package com.infitry.base.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.infitry.base.entity.User;
import com.infitry.base.result.TransResult;

/**
 * @since 2020. 3. 31.
 * @author leesw
 * @mail leesw504@gmail.com
 * @description : 블로그 컨트롤러 (웹)
 */
@Controller
@RequestMapping("/blog")
public class BlogController {
	
	private static final Logger logger = LoggerFactory.getLogger(BlogController.class);
	
	RestTemplate blogClient = new RestTemplate();
	
	@Value("${infitry.blog.url}")
	String blogUrl;
	
	/**
	 * @since 2020. 3. 31.
	 * @author leesw
	 * @description : 블로그 포스트 목록 페이지 이동
	 */
	@RequestMapping(value = "/post/list", method = RequestMethod.GET)
	public String blogPostListPage(User user) {
		return "blog/post/list";
	}
	
	/**
	 * @since 2020. 3. 31.
	 * @author leesw
	 * @description : 블로그 포스트 목록 페이지 이동
	 */
	@RequestMapping(value = "/post/create", method = RequestMethod.GET)
	public String blogPostCreatePage(User user) {
		return "blog/post/create";
	}
	
	/**
	 * @since 2020. 3. 31.
	 * @author leesw
	 * @description : 블로그 포스트 목록 페이지 이동
	 */
	@RequestMapping(value = "/post/create/process", method = RequestMethod.POST)
	@ResponseBody
	public TransResult blogPostCreateProc(User user) {
		TransResult result = new TransResult();
		
		//TODO 글 저장 처리
		
		return result;
	}
}
