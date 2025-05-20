package com.spring.boot.web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class HomeController {
	
	@RequestMapping
	public String showRegistrationPage() {
		return "index.html";
	}
	
}

