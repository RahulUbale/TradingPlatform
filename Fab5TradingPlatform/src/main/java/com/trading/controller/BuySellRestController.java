package com.trading.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.trading.api.KiteConnectAPI;
import com.trading.bean.OrderQuote;
import com.trading.bean.QuoteBean;
import com.trading.dao.ICompanyRepository;
import com.trading.pojos.Company;
import com.trading.pojos.Order;
import com.trading.pojos.OrderType;
import com.trading.pojos.User;
import com.trading.service.IUserService;

@RestController
@RequestMapping("/shares")
@CrossOrigin(origins = "*")

public class BuySellRestController {

	@Autowired
	IUserService service;
	@Autowired
	ICompanyRepository companyRepo;
	Map<String, String> oauth2AuthenticationUrls = new HashMap<>();


	public BuySellRestController() {
		System.out.println("in cunstructor od" + getClass().getName());
	}
	
	@ResponseBody
	@GetMapping("/restsearch/{gToken}")
	public ResponseEntity<?> showSearchResults(@RequestParam String enteredName, @PathVariable String gToken) {

		//User user = service.getUserByToken(gToken);
		
		//if(user==null)
			//return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		
		List<Company> companies = companyRepo.getSearchedCompanies(enteredName);
		if (companies.isEmpty()) {

			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(companies, HttpStatus.CREATED);

	}

	@ResponseBody
	@GetMapping("/restQuote/{gToken}")
	public ResponseEntity<?> restSelectOrderType(@RequestParam String apiUniqueName, @PathVariable String gToken) {
		User user = service.getUserByToken(gToken);
		
		if(user==null)
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		OrderQuote orderquote = KiteConnectAPI.getQuote(apiUniqueName);
		
		QuoteBean quoteBean = new QuoteBean(orderquote,apiUniqueName);
		return new ResponseEntity<>(quoteBean, HttpStatus.CREATED);
	}


	@GetMapping("/restBuy/{gToken}")
	ResponseEntity<?> restSaveOrder(@RequestParam String apiUniqueName, @RequestParam int quantity, @RequestParam double price,
			@PathVariable String gToken) {
		User user = service.getUserByToken(gToken);
		
		if(user==null)
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		
		OrderQuote quote = KiteConnectAPI.getQuote(apiUniqueName);
		String ticker = quote.getCompany().getTicker();
		String company = quote.getCompany().getName();

		if (quantity <= 0) {
			QuoteBean quoteBean = new QuoteBean(quote,apiUniqueName);
			return new ResponseEntity<>(quoteBean, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		Order order = new Order(company, ticker, apiUniqueName, quantity, price, OrderType.valueOf("BUY"));
		user.addBuyOrder(order);
		String message = service.buy(user);
		return new ResponseEntity<>(message,HttpStatus.OK);
	
	}

	@GetMapping("/restsell/{gToken}")
	ResponseEntity<?> restSellStocks(@RequestParam String apiUniqueName, @RequestParam int quantity, @RequestParam double price,
			@PathVariable String gToken) {
		User user = service.getUserByToken(gToken);
		
		if(user==null)
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		
				OrderQuote quote = KiteConnectAPI.getQuote(apiUniqueName);
		String ticker = quote.getCompany().getTicker();
		String company = quote.getCompany().getName();
		
		if (quantity <= 0) {
			QuoteBean quoteBean = new QuoteBean(quote,apiUniqueName);
			return new ResponseEntity<>(quoteBean, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		Order order = new Order(company, ticker, apiUniqueName, quantity, price, OrderType.valueOf("SELL"));

		if (service.sell(user, quantity, apiUniqueName, order) == null) {
			
			QuoteBean quoteBean = new QuoteBean(quote,apiUniqueName);
			return new ResponseEntity<>(quoteBean, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(HttpStatus.OK);

	}
}
