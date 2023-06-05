package com.trading.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trading.pojos.Order;
import com.trading.pojos.User;
import com.trading.service.IUserService;

@RestController
@RequestMapping("/rest")
@CrossOrigin(origins = "*")
public class TransactionsRestController {

	@Autowired
	IUserService service;
	
	
	public TransactionsRestController() {
		System.out.println("in constructor " + getClass().getName());
	}

	@GetMapping("/transactions/{userId}")
	public ResponseEntity<?> showTransactions(@PathVariable int userId) {

		System.out.println("In Rest - " + userId);
		List<Order> orders = service.getOrdersBuyId(userId);

		if(orders.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else {
			System.out.println(orders);
			return new ResponseEntity<>(orders, HttpStatus.CREATED);
		}
	}
	
	@GetMapping("/orders/{gToken}")
	public ResponseEntity<?> showTransactions(@PathVariable String gToken) {

		User user = service.getUserByToken(gToken);
		
		if(user==null)
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		
		List<Order> orders = service.getOrdersBuyId(user.getId());

		if(orders.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else {
			System.out.println(orders);
			return new ResponseEntity<>(orders, HttpStatus.CREATED);
		}
	}

}
