package com.trading.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trading.bean.HomePageBean;
import com.trading.bean.MarketQuote;
import com.trading.pojos.User;
import com.trading.service.IHomePageService;

@Controller
public class HomePageController {

	@Autowired
	IHomePageService service;
	
	@Autowired
    private SimpMessagingTemplate template;	
	
	public HomePageController() {
		System.out.println("In default constructor " + this.getClass().getName());
	}
	
	@GetMapping("/")
	public String getIndexPage(Model map, HttpSession session) {
		HomePageBean bean = service.getHomePageData();
		map.addAttribute("bean", bean);
		if (session.getAttribute("user") != null) {
			User user = (User) session.getAttribute("user");
			map.addAttribute("userId", user.getId());
		}
	    ObjectMapper mapper = new ObjectMapper();
	    String jsonResponse;
	    try {
	    	List<MarketQuote> markets = bean.getMarketChangeData();
			jsonResponse = mapper.writeValueAsString(markets);
			System.out.println("Sending to angular : " + jsonResponse);
			template.convertAndSend("/livetrading/markets", jsonResponse);
		} catch (JsonProcessingException e) {
			System.out.println("Could not send update to Angular : " + e.getMessage());
			e.printStackTrace();
		}catch(Exception e) {
			System.out.println("General angular exception - " + e.getMessage());
			e.printStackTrace();
		}
				
		return "/home/home";
	}
}