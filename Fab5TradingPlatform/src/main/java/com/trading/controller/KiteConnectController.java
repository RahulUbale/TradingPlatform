package com.trading.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.trading.api.GNewsApi;
import com.trading.api.KiteConnectAPI;
import com.trading.service.ICompanyService;
import com.trading.service.INewsService;
import com.zerodhatech.kiteconnect.KiteConnect;
import com.zerodhatech.kiteconnect.kitehttp.SessionExpiryHook;
import com.zerodhatech.kiteconnect.kitehttp.exceptions.KiteException;
import com.zerodhatech.models.User;


@Controller
@RequestMapping("/kiteapi")
public class KiteConnectController {

	@Autowired
	ICompanyService service;
	
	@Autowired
	INewsService newsService;
	
	private static final String clientID = "HN0149";
	private static final String apiKey = "nyqzyyx2xcq2cabo";
	private static final String apiSecret = "gc6qwuta78ieu8yttpvz9il8n68fxo3t";
	
	public KiteConnectController() {
		System.out.println("In default constructor : " + this.getClass().getName());
	}
	
	//Callback method to retrieve the request token
	@GetMapping("/gettoken")
	public String getRequestToken(@RequestParam String request_token, Model map) {
		String message = "Connected successfully to Kite Connect API";
		try {
		KiteConnect connection = new KiteConnect(apiKey);

		// Set userId.
		connection.setUserId(clientID);
		connection.getLoginURL();

		// Get accessToken as follows,
		User userModel =  connection.generateSession(request_token,apiSecret);

		// Set request token and public token which are obtained from login process.
		connection.setAccessToken(userModel.accessToken);
		connection.setPublicToken(userModel.publicToken);

		// Set session expiry callback.
		connection.setSessionExpiryHook(new SessionExpiryHook() {
		    @Override
		    public void sessionExpired() {
		        System.out.println("session expired");                    
		    }
		});
		KiteConnectAPI.connection = connection;
		System.out.println("load company data");
		KiteConnectAPI.loadCompanyData();
		newsService.saveNews(GNewsApi.loadLatestNews());
		map.addAttribute("message", message);
		service.loadCompanyData(KiteConnectAPI.equityList);
		System.out.println("Redirecting");
		

		//Redirecting to homepage because there were no errors. In case of exception, exception will be seen.
		return "redirect:/";
		}catch(KiteException e) {
			System.out.println("In Exception" + e.getMessage());
			message = e.getMessage();
			map.addAttribute("message", message);
			return "/kiteapi/gettoken";
		}catch(Exception e) {
			System.out.println("In Exception" + e.getMessage());
			message = e.getMessage();
			map.addAttribute("message", message);
			return "/kiteapi/gettoken";
		}
	}

}
