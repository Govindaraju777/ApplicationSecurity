package com.example.springboot.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoggedInUserDetailsController {

	@RequestMapping(value = "/myProfile", method = RequestMethod.GET)
	public Principal currentUser(Principal principal) {
		return principal;
	}

	@RequestMapping(value = "/myName", method = RequestMethod.GET)
	public String currentUserName(Principal principal) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String currentUserName = authentication.getName();
			System.out.println("currentUserName using static call to the SecurityContextHolder:: " + currentUserName);
		}
		System.out.println("currentUserName using principal Object :: " + principal.getName());
		return principal.getName();
	}

	// Alternatively, the authentication token can also be used:
	@RequestMapping(value = "/username1", method = RequestMethod.GET)
	public String currentUserName(Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		System.out.println("User has authorities: " + userDetails.getAuthorities());
		return authentication.getName();
	}

	@RequestMapping(value = "/username2", method = RequestMethod.GET)
	@ResponseBody
	public String currentUserNameSimple(HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		return principal.getName();
	}

}
