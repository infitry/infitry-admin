package com.infitry.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/blog-post")
public class BlogPostController {
	
	@RequestMapping(value = "/list")
	public String list() {
		return "blog-post/list";
	}
	
	@RequestMapping(value = "/detail")
	public String detail() {
		return "blog-post/detail";
	}
}
