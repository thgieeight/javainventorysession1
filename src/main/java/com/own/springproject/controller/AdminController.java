package com.own.springproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {


	
	@GetMapping("/")
	
	public String gethome(HttpSession session) {
		
		if(session.getAttribute("validuser")== null) {
			return "login";
		}
		return "admin/index";
	}
	
	@GetMapping("/report")
	public String getReport(HttpSession session) {
		
		if(session.getAttribute("validuser")== null) {
			return "login";
		}
		return "admin/report";
	}
	

}
