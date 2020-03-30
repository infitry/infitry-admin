package com.infitry.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/admin")
public class MainController {
	
	@RequestMapping(value = "")
	public String main() {
		return "admin/index";
	}
}
