package com.infitry.base.component;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.infitry.base.entity.BlogPost;
import com.infitry.base.entity.PostCategory;
import com.infitry.base.result.TransResult;

/**
 * @since 2020. 4. 8.
 * @author leesw
 * @mail leesw504@gmail.com
 * @description : 블로그 컴포넌트
 */
@Component
public class BlogComponent {
	
	private static final Logger logger = LoggerFactory.getLogger(BlogComponent.class);
	
	@Value("${infitry.blog.url}")
	String blogUrl;
	
	RestTemplate blogClient = new RestTemplate();
	
	/**
	 * @since 2020. 4. 8.
	 * @author leesw
	 * @description : 카테고리 목록
	 */
	public List<PostCategory> getCategoryList() {
		PostCategory[] postCategoryList = null;
		try {
			postCategoryList = blogClient.getForObject(blogUrl + "/blog/category/list-all", PostCategory[].class);
		} catch (Exception e) {
			logger.error("BLOG SERVICE NOT AVAILABLE...!!!");
		}
		return Arrays.asList(postCategoryList);
	}
	
	/**
	 * @since 2020. 4. 8.
	 * @author leesw
	 * @description : 포스트 전체목록
	 */
	public List<BlogPost> getPostListAll() {
		List<BlogPost> result = null;
		try {
			BlogPost[] blogPostList = blogClient.getForObject(blogUrl + "/blog/post/list-all", BlogPost[].class);
			result = Arrays.asList(blogPostList);
		} catch (Exception e) {
			logger.error("BLOG SERVICE NOT AVAILABLE...!!!");
			logger.error("[ERROR] - " + e.getMessage());
		}
		return result;
	}
	
	/**
	 * @since 2020. 4. 8.
	 * @author leesw
	 * @description : 포스트 상세
	 */
	public BlogPost getPostDetail(long blogPostSeq) {
		BlogPost blogPost = null;
		try {
			blogPost = blogClient.getForObject(blogUrl + "/blog/post/detail?blogPostSeq=" + blogPostSeq, BlogPost.class);
		
		} catch (Exception e) {
			logger.error(e.getMessage());
			logger.error("BLOG SERVICE NOT AVAILABLE...!!!");
		}
		
		return blogPost;
	}
	
	public TransResult savePost(BlogPost blogPost) {
		TransResult result = new TransResult();
		
		try {
			result = blogClient.postForObject(blogUrl + "/blog/post/save", blogPost, TransResult.class);
		} catch (Exception e) {
			logger.error("[ERROR] - " + e.getMessage());
			logger.error("BLOG SERVICE NOT AVAILABLE...!!!");
			result.setSuccess(false);
			result.setErrorMessage("API 서버가 응답하지 않습니다.");
		}
		
		return result;
	}
	
	public TransResult saveCategory(PostCategory category) {
		TransResult result = new TransResult();
		
		try {
			result = blogClient.postForObject(blogUrl + "/blog/category/save", category, TransResult.class);
		} catch (Exception e) {
			logger.error("BLOG SERVICE NOT AVAILABLE...!!!");
			result.setSuccess(false);
			result.setErrorMessage("API 서버가 응답하지 않습니다.");
		}
		
		return result;
	}
}
