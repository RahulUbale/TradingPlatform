package com.trading.api;

import static java.util.Objects.isNull;

import org.springframework.web.client.RestTemplate;

import com.trading.bean.UserProxy;


public class UserMicroserviceAPI {
	
	private static final String authUrl = "http://localhost:8080/user/authorize/";
	private static final String addUrl = "http://localhost:8080/user/add";
	
	
	public UserMicroserviceAPI() {
		// TODO Auto-generated constructor stub
	}
	
	public UserProxy authorizeUser(String gToken) {
		UserProxy user = null;
		RestTemplate restTemplate = new RestTemplate();
		String userMicroserviceUrl = authUrl + gToken;
		user = restTemplate
				  .getForObject(userMicroserviceUrl, UserProxy.class);
		if (isNull(user)){
			System.out.println("User is Null");
			return null;
		}
		return user;
	}

	public UserProxy addUser(UserProxy u) {
		// TODO Auto-generated method stub
		UserProxy user = null;
		RestTemplate restTemplate = new RestTemplate();
		user = restTemplate
				  .postForObject(addUrl,u, UserProxy.class);
		if (isNull(user)){
			System.out.println("User is Null");
			return null;
		}
		return user;
	}

}
