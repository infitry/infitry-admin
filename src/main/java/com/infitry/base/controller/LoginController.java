package com.infitry.base.controller;

import javax.servlet.http.HttpSession;

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
 * @description : 로그인 컨트롤러 (웹)
 */
@Controller
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	RestTemplate userClient = new RestTemplate();
	
	@Value("${infitry.user.url}")
	String userUrl;
	
	/**
	 * @since 2020. 3. 31.
	 * @author leesw
	 * @description : 로그인 페이지 이동
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage(User user, HttpSession session) {
		return "user/login";
	}
	
	/**
	 * @since 2020. 3. 31.
	 * @author leesw
	 * @description : 로그인 처리
	 */
	@RequestMapping(value = "/login/process", method = RequestMethod.POST)
	@ResponseBody
	public TransResult loginProc(User user, HttpSession session) {
		TransResult result = new TransResult();
		try {
			logger.info("login proc user id : " + user.getId());
			logger.info("admin session ID : " + session.getId());
			
			result = userClient.postForObject(userUrl + "/login", user, TransResult.class);
			
			/* 로그인 성공 시 현재 웹 WAS의 세션에 로그인 ID저장 */
			//TODO api에서도 redis로 세션공유되도록 설정해야함.
			if (result.isSuccess()) {
				session.setAttribute("loginId", user.getId());
			}
		} catch (Exception e) {
			logger.error("USER SERVICE NOT AVAILABLE...!!!");
		}
		return result;
	}
	
	/**
	 * @since 2020. 3. 31.
	 * @author leesw
	 * @description : 로그아웃 처리
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	@ResponseBody
	public TransResult logout(User user, HttpSession session) {
		TransResult result = new TransResult();
		try {
			logger.info("logout user id : " + user.getId());
			result = userClient.postForObject(userUrl + "/logout", user, TransResult.class);
			
			if (result.isSuccess()) {
				session.removeAttribute("loginId");
			}
			
		} catch (Exception e) {
			logger.error("USER SERVICE NOT AVAILABLE...!!!");
		}
		logger.info(result.toString());
		
		return result;
	}
}
