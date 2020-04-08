package com.infitry.base.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.infitry.base.component.UserComponent;
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
	
	@Autowired
	UserComponent userComponent;
	
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
		result = userComponent.login(user);
			
		/* 로그인 성공 시 현재 웹 WAS의 세션에 로그인 ID저장 */
		//TODO api에서도 redis로 세션공유되도록 설정해야함.
		if (result.isSuccess()) {
			session.setAttribute("loginId", user.getId());
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
		result = userComponent.logout(user);
		
		if (result.isSuccess()) {
			session.removeAttribute("loginId");
		}
		return result;
	}
}
