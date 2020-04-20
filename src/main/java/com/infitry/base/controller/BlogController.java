package com.infitry.base.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.infitry.base.component.BlogComponent;
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
	
	@Autowired
	BlogComponent blogComponent;
	
	/**
	 * @since 2020. 3. 31.
	 * @author leesw
	 * @description : 블로그 포스트 목록 페이지 이동
	 */
	@RequestMapping(value = "/post/list", method = RequestMethod.GET)
	public String blogPostListPage(Model model) {
		//post 전체목록
		model.addAttribute("blogPostList", blogComponent.getPostListAll());
		return "blog/post/list";
	}
	
	/**
	 * @since 2020. 4. 07.
	 * @author leesw
	 * @description : 블로그 포스트 수정 페이지 이동
	 */
	@RequestMapping(value = "/post/edit", method = RequestMethod.GET)
	public String blogPostEditPage(long blogPostSeq, Model model) {
		//카테고리 목록
		model.addAttribute("postCategoryList", blogComponent.getCategoryList());
		model.addAttribute("blogPost", blogComponent.getPostDetail(blogPostSeq));
		
		return "blog/post/edit";
	}
	
	/**
	 * @since 2020. 4. 07.
	 * @author leesw
	 * @description : 블로그 포스트 수정처리
	 */
	@RequestMapping(value = "/post/edit/proc", method = RequestMethod.POST)
	@ResponseBody
	public TransResult blogPostEditProc(BlogPost blogPost, HttpSession session) {
		TransResult result = new TransResult();
		//기존 정보를 불러온다.
		BlogPost baseBlogPost = blogComponent.getPostDetail(blogPost.getBlogPostSeq());
		blogPost.setRegUser(baseBlogPost.getRegUser());	//기존 정보 작성자
		blogPost.setRegDate(baseBlogPost.getRegDate());	//기존 정보 작성일
		
		System.out.println(blogPost.toString());
		
		result = blogComponent.savePost(blogPost);
		
		return result;	
	}
	
	/**
	 * @since 2020. 3. 31.
	 * @author leesw
	 * @description : 블로그 포스트 생성 페이지 이동
	 */
	@RequestMapping(value = "/post/create", method = RequestMethod.GET)
	public String blogPostCreatePage(Model model) {
		
		//카테고리 목록
		model.addAttribute("postCategoryList", blogComponent.getCategoryList());
		
		return "blog/post/create";
	}
	
	/**
	 * @since 2020. 3. 31.
	 * @author leesw
	 * @description : 블로그 포스트 저장처리
	 */
	@RequestMapping(value = "/post/create/proc", method = RequestMethod.POST)
	@ResponseBody
	public TransResult blogPostCreateProc(BlogPost blogPost, HttpSession session) {
		TransResult result = new TransResult();
		blogPost.setRegUser((String) session.getAttribute("loginId"));
		blogPost.setRegDate(new Date());
		//포스트 저장
		result = blogComponent.savePost(blogPost);
		
		return result;
	}
	
	/**
	 * @since 2020. 3. 31.
	 * @author leesw
	 * @description : 포스트 카테고리 목록
	 */
	@RequestMapping(value = "/category/list", method = RequestMethod.GET)
	public String blogCategoryListPage(Model model) {
		//카테고리 목록
		model.addAttribute("categoryList", blogComponent.getCategoryList());
		return "blog/category/list";
	}
	
	/**
	 * @since 2020. 4. 08.
	 * @author leesw
	 * @description : 포스트 카테고리 생성페이지
	 */
	@RequestMapping(value = "/category/create", method = RequestMethod.GET)
	public String blogCategoryCreatePage(Model model) {
		return "blog/category/create";
	}
	
	/**
	 * @since 2020. 4. 08.
	 * @author leesw
	 * @description : 포스트 카테고리 생성처리
	 */
	@RequestMapping(value = "/category/create/proc", method = RequestMethod.POST)
	@ResponseBody
	public TransResult blogCategoryCreateProc(PostCategory category, HttpSession session) {
		TransResult result = new TransResult();
		category.setRegUser((String) session.getAttribute("loginId"));
		
		result = blogComponent.saveCategory(category);
		
		return result;
	}
}
