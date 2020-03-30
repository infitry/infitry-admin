package com.infitry.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/admin")
public class LoginController {
	
	@RequestMapping(value = "/login")
	public String list() {
		return "user/login";
	}
}
