package com.infitry.base.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


/**
 * @since 2020. 4. 7.
 * @author leesw
 * @mail leesw504@gmail.com
 * @description : 로그인 여부 판단 인터셉터
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//세션에서 로그인아이디 추출
		String loginId = (String) request.getSession().getAttribute("loginId");
		
		//로그인 되지 않은 사용자는 로그인페이지로 이동
		if (StringUtils.isEmpty(loginId)) {
			response.sendRedirect("/login");
			return false;
		}
		
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}
}
