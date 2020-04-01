package com.infitry.base.controller;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.infitry.base.entity.BlogPost;
import com.infitry.base.entity.PostCategory;
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
	public String blogPostListPage(Model model) {
		try {
			BlogPost[] blogPostList = blogClient.getForObject(blogUrl + "/blog/post/list-all", BlogPost[].class);
			model.addAttribute("blogPostList", Arrays.asList(blogPostList));
		} catch (Exception e) {
			logger.error("BLOG SERVICE NOT AVAILABLE...!!!");
		}
		
		return "blog/post/list";
	}
	
	/**
	 * @since 2020. 3. 31.
	 * @author leesw
	 * @description : 블로그 포스트 생성 페이지 이동
	 */
	@RequestMapping(value = "/post/create", method = RequestMethod.GET)
	public String blogPostCreatePage(Model model) {
		try {
			PostCategory[] postCategoryList = blogClient.getForObject(blogUrl + "/blog/post/categories", PostCategory[].class);
			model.addAttribute("postCategoryList", Arrays.asList(postCategoryList));
		} catch (Exception e) {
			logger.error("BLOG SERVICE NOT AVAILABLE...!!!");
		}
		
		return "blog/post/create";
	}
	
	/**
	 * @since 2020. 3. 31.
	 * @author leesw
	 * @description : 블로그 포스트 저장처리
	 */
	@RequestMapping(value = "/post/create/process", method = RequestMethod.POST)
	@ResponseBody
	public TransResult blogPostCreateProc(BlogPost blogPost) {
		TransResult result = new TransResult();
		
		try {
			result = blogClient.postForObject(blogUrl + "/blog/post/create", blogPost, TransResult.class);
		} catch (Exception e) {
			logger.error("BLOG SERVICE NOT AVAILABLE...!!!");
			result.setSuccess(false);
			result.setErrorMessage("API 서버가 응답하지 않습니다.");
		}
		return result;
	}
}
