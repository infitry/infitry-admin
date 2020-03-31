package com.infitry.base.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import com.infitry.base.entity.User;

@Controller
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	RestTemplate userClient = new RestTemplate();
	
	@Value("${infitry.user.url}")
	String userUrl;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginPage() {
		return "user/login";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(User user) {
		String result = "";
		//TODO logout 처리 
		try {
			logger.info("logout user id : " + user.getId());
			logger.info("userUrl : " + userUrl);
			result = userClient.postForObject(userUrl + "/logout", user, String.class);
		} catch (Exception e) {
			logger.error("USER SERVICE NOT AVAILABLE...!!!");
		}
		
		logger.info(result);
		return "user/login";
	}
}
