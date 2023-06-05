package com.trading.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trading.bean.HomePageBean;
import com.trading.bean.MarketQuote;
import com.trading.bean.MoversAndNews;
import com.trading.service.IHomePageService;

@RestController
@RequestMapping("/rest")
@CrossOrigin(origins = "*")
public class HomePageRestController {

	@Autowired
	IHomePageService service;
	
	@Autowired
    private SimpMessagingTemplate template;
	
	List<MarketQuote> markets;
	
	public HomePageRestController() {
		System.out.println("In default constructor " + this.getClass().getName());
	}
	
	@GetMapping("/home")
	public ResponseEntity<?>  getIndexPage() {
		HomePageBean bean = service.getHomePageData();
		markets = bean.getMarketChangeData();
		return new ResponseEntity<>(markets, HttpStatus.CREATED);
	}
	
	@GetMapping("/movers")
	public ResponseEntity<?>  getMoversAndNews() {
		MoversAndNews bean = service.getMoversAndNews();
		return new ResponseEntity<>(bean, HttpStatus.CREATED);
	}
	
	
	@KafkaListener(topics = "market", groupId = "home")
	public void listenGroupFoo(ArrayList<MarketQuote> update) {

	    ObjectMapper mapper = new ObjectMapper();
	    String jsonResponse;
	    try {
			jsonResponse = mapper.writeValueAsString(update);
			System.out.println("Sending to angular : " + jsonResponse);
			template.convertAndSend("/livetrading/markets", jsonResponse);
		} catch (JsonProcessingException e) {
			System.out.println("Could not send update to Angular : " + e.getMessage());
			e.printStackTrace();
		}catch(Exception e) {
			System.out.println("General angular exception - " + e.getMessage());
			e.printStackTrace();
		}
	}
}
