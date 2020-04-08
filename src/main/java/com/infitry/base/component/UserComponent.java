package com.infitry.base.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.infitry.base.entity.User;
import com.infitry.base.result.TransResult;

/**
 * @since 2020. 4. 8.
 * @author leesw
 * @mail leesw504@gmail.com
 * @description : 유저 컴포넌트
 */
@Component
public class UserComponent {
	
	private static final Logger logger = LoggerFactory.getLogger(UserComponent.class);
	
	@Value("${infitry.user.url}")
	String userUrl;
	
	RestTemplate userClient = new RestTemplate();
	
	public TransResult login(User user) {
		TransResult result = new TransResult();
		try {
			result = userClient.postForObject(userUrl + "/login", user, TransResult.class);
		} catch (Exception e) {
			logger.error("USER SERVICE NOT AVAILABLE...!!!");
		}
		return result;
	}
	
	public TransResult logout(User user) {
		TransResult result = new TransResult();
		try {
			logger.info("logout user id : " + user.getId());
			result = userClient.postForObject(userUrl + "/logout", user, TransResult.class);
		} catch (Exception e) {
			logger.error("USER SERVICE NOT AVAILABLE...!!!");
		}
		return result;
	}
	
}
