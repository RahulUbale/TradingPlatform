package com.trading.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.trading.pojos.Order;
import com.trading.service.IUserService;

@Controller
@RequestMapping("/transactions")
public class TransactionsController {
	@Autowired
	IUserService service;

	public TransactionsController() {
		System.out.println("in cunstructor " + getClass().getName());
	}

	@GetMapping("show")
	public String showTransactions(@RequestParam int userId, Model map) {

		List<Order> orders = service.getOrdersBuyId(userId);

		String message = "";
		if (orders == null) {
			message = "No Trasactions found!!!";
		}
		map.addAttribute("orders", orders);
		map.addAttribute("message", message);
		map.addAttribute("userId", userId);
		return "/transactions/show";
	}

}
