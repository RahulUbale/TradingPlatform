package com.trading.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.trading.bean.Portfolio;
import com.trading.pojos.User;
import com.trading.service.IPortfolioService;
import com.trading.service.IUserService;

@Controller
@RequestMapping("/portfolio")
public class PortfolioController {
	@Autowired
	IUserService service;
	@Autowired
	IPortfolioService portfolioService;

	@GetMapping("/display")
	public String displayPostfolio(@RequestParam int userId, ModelMap map) {
		
		User user = service.getUserById(userId);
		Portfolio portfolio = portfolioService.getPortfolio(user);
		
		map.addAttribute("portfolio", portfolio);
		map.addAttribute("userId", userId);	
		return "/portfolio/display";
	}
	
	
}
