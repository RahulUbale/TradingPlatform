package com.trading.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.trading.bean.UserProxy;
import com.trading.pojos.User;
import com.trading.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private IUserService service;
	
	private static final String authorizationRequestBaseUri = "oauth2/authorize-client";
    Map<String, String> oauth2AuthenticationUrls = new HashMap<>();

    @Autowired
    private ClientRegistrationRepository clientRegistrationRepository;
    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;
    

	@GetMapping("/login")
	public String showLoginForm(Model model, OAuth2AuthenticationToken authentication, 
									RedirectAttributes flashMap, HttpSession session,
									@AuthenticationPrincipal OAuth2User oauth2User) {
        OAuth2AuthorizedClient client = authorizedClientService.loadAuthorizedClient(authentication.getAuthorizedClientRegistrationId(), authentication.getName());
        String email = null;
        String name = null;
        String gToken = null;
        String userInfoEndpointUri = client.getClientRegistration()
            .getProviderDetails()
            .getUserInfoEndpoint()
            .getUri();

        if (!StringUtils.isEmpty(userInfoEndpointUri)) {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + client.getAccessToken()
                .getTokenValue());

            HttpEntity<String> entity = new HttpEntity<String>("", headers);

            ResponseEntity<Map> response = restTemplate.exchange(userInfoEndpointUri, HttpMethod.GET, entity, Map.class);
            Map userAttributes = response.getBody();
            System.out.println("Response : " + response.getBody());
            System.out.println("Access Token : " + client.getAccessToken().getTokenValue());
            System.out.println("Name : " + userAttributes.get("name"));
            model.addAttribute("name", userAttributes.get("name"));
            name = (String)userAttributes.get("name");
            System.out.println("Name : " + userAttributes.get("email"));
            email = (String)userAttributes.get("email");
            model.addAttribute("email", userAttributes.get("email"));
            gToken = ((OidcUser) oauth2User).getIdToken().getTokenValue();
            
        }
        String message = "Successfully logged in";
		UserProxy user = null;
		User u = null;
		try {
		u = service.getUserByToken(gToken);
		}catch(Exception e) {
			user = new UserProxy(name, email, "");
			flashMap.addFlashAttribute("user", user);
			flashMap.addFlashAttribute("message", message);
			return "redirect:/user/signup";			
		}
		if(u==null) {
			user = new UserProxy(name, email, "");
			flashMap.addFlashAttribute("user", user);
			flashMap.addFlashAttribute("message", message);
			return "redirect:/user/signup";			
		}else {
			session.setAttribute("user", u);
			flashMap.addFlashAttribute("message", message);
			return "redirect:/";			
		}
	}

	@GetMapping("/signup")
	public String showSignupForm(Model modelMap, HttpSession session) {
		//modelMap.addAttribute("user", session.getAttribute("user"));
		return "/user/signup";
	}
	
	@GetMapping("/logout")
	public String showSignupForm(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
////Modify Signup Later
	@PostMapping("/signup")
	public String addUser(@ModelAttribute (name = "user") UserProxy u, RedirectAttributes flashMap, HttpSession session) {
		User user = service.saveUser(u);
		session.setAttribute("user", user);
		String message;
		if(user!=null) {
			message = "Signup was successful";
			session.setAttribute("user", user); 	
		}else
			message = "Signup was unsuccessful";
		flashMap.addFlashAttribute("message", message);
		return "redirect:/";
	}
	
}
