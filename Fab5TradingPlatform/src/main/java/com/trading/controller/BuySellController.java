package com.trading.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.trading.api.KiteConnectAPI;
import com.trading.bean.OrderQuote;
import com.trading.dao.ICompanyRepository;
import com.trading.pojos.Company;
import com.trading.pojos.Order;
import com.trading.pojos.OrderType;
import com.trading.pojos.User;
import com.trading.service.IUserService;

@Controller
@RequestMapping("/trade")
public class BuySellController {

	@Autowired
	IUserService service;
	@Autowired
	ICompanyRepository companyRepo;
	private static final String authorizationRequestBaseUri = "oauth2/authorize-client";
	Map<String, String> oauth2AuthenticationUrls = new HashMap<>();

	@Autowired
	private ClientRegistrationRepository clientRegistrationRepository;
	@Autowired
	private OAuth2AuthorizedClientService authorizedClientService;

	public BuySellController() {
		System.out.println("in cunstructor od" + getClass().getName());
	}

	@GetMapping("/search")
	public String showSearchForm(@RequestParam int userId, ModelMap map) {
		map.addAttribute("userId", userId);
		return "/trade/search";
	}

	@PostMapping("/search")
	public String showSearchResults(@RequestParam String enteredName, @RequestParam int userId, Model model,
			OAuth2AuthenticationToken authentication, RedirectAttributes flashMap, HttpSession session) {

		List<Company> companies = companyRepo.getSearchedCompanies(enteredName);
		if (companies.isEmpty()) {
			String message = "No matching Comapanies found for requested search parameters";
			flashMap.addFlashAttribute("message", message);
			flashMap.addFlashAttribute("userId", userId);
			return "redirect:/trade/search?userId="+userId;
		}

		flashMap.addFlashAttribute("companies", companies);
		flashMap.addFlashAttribute("userId", userId);
		return "redirect:/trade/list";

	}

	@GetMapping("/list")
	public String showCompaniesList() {

		return "/trade/list";
	}

	@GetMapping("/quote")
	public String selectOrderType(@RequestParam int id, @RequestParam int userId, Model map) {
		String apiUniqueName = companyRepo.getCompanyNameById(id);
		OrderQuote quote = KiteConnectAPI.getQuote(apiUniqueName);
		map.addAttribute("orderquote", quote);
		map.addAttribute("userId", userId);
		map.addAttribute("id", id);
		return "/trade/quote";
	}

	@PostMapping("/buyquote")
	public String showBuyQuote(@RequestParam int id, @RequestParam int userId, @RequestParam String apiUniqueName,
			@RequestParam double price, Model map) {
		OrderQuote quote = KiteConnectAPI.getQuote(apiUniqueName);
		map.addAttribute("orderquote", quote);
		map.addAttribute("userId", userId);
		map.addAttribute("id", id);
		return "/trade/buyquote";
	}

	@PostMapping("/sellquote")
	public String showSellQuote(@RequestParam int id, @RequestParam int userId, @RequestParam String apiUniqueName,
			@RequestParam double price, Model map) {
		OrderQuote quote = KiteConnectAPI.getQuote(apiUniqueName);
		map.addAttribute("orderquote", quote);
		map.addAttribute("userId", userId);
		map.addAttribute("id", id);
		return "/trade/sellquote";
	}

	@PostMapping("/buy")
	public String saveOrder(@RequestParam int id, @RequestParam String company, @RequestParam String ticker,
			@RequestParam String apiUniqueName, @RequestParam int quantity, @RequestParam double price,
			@RequestParam int userId, RedirectAttributes flashMap, HttpSession session) {
		if (session.getAttribute("user") != null) {

			User user = service.getUserById(userId);
			Order order = new Order(company, ticker, apiUniqueName, quantity, price, OrderType.valueOf("BUY"));
			user.addBuyOrder(order);
			String message = service.buy(user);
			flashMap.addFlashAttribute("response", message);
			return "redirect:/";
		} else {
			return "redirect:/user/signup";
		}
	}

	@PostMapping("/sell")
	public String sellStocks(@RequestParam int id, @RequestParam String company, @RequestParam String ticker,
			@RequestParam String apiUniqueName, @RequestParam int quantity, @RequestParam double price,
			@RequestParam int userId, Model map, HttpSession session){
		if(quantity<=0) {
				
				String msg ="Please insert required quantity of shares";
				map.addAttribute("response", msg);
				OrderQuote quote = KiteConnectAPI.getQuote(apiUniqueName);
				map.addAttribute("orderquote", quote);
				map.addAttribute("userId", userId);
				map.addAttribute("id", id);
				return "/trade/sellquote";
			}
			
						
			User user = service.getUserById(userId);
			Order order = new Order(company, ticker, apiUniqueName, quantity, price, OrderType.valueOf("SELL"));
			
			if(service.sell(user,quantity,apiUniqueName,order) == null) {
				
				String msg ="No short selling allowed!";
				map.addAttribute("response", msg);
				OrderQuote quote = KiteConnectAPI.getQuote(apiUniqueName);
				map.addAttribute("orderquote", quote);
				map.addAttribute("userId", userId);
				map.addAttribute("id", id);
				return "/trade/sellquote";
			}			
			return "redirect:/";
	
	}
}
