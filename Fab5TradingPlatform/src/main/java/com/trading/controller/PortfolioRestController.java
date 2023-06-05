package com.trading.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trading.bean.Portfolio;
import com.trading.pojos.User;
import com.trading.service.IPortfolioService;
import com.trading.service.IUserService;

@RestController
@RequestMapping("/rest")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PortfolioRestController {
	@Autowired
	IUserService service;
	@Autowired
	IPortfolioService portfolioService;

	@GetMapping("/displayportfolio/{userId}")
	public ResponseEntity<?> showTransactions(@PathVariable int userId) {

		System.out.println("In Rest - " + userId);
		User user = service.getUserById(userId);
		Portfolio portfolio = portfolioService.getPortfolio(user);

		return new ResponseEntity<>(portfolio, HttpStatus.OK);
	}
	
	@GetMapping("/getportfolio/{gToken}")
	public ResponseEntity<?> getTransactions(@PathVariable String gToken) {
		User user = service.getUserByToken(gToken);
		
		if(user==null)
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);

		Portfolio portfolio = portfolioService.getPortfolio(user);

		return new ResponseEntity<>(portfolio, HttpStatus.OK);
	}

}
