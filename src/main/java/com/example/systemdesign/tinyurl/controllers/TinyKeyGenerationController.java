package com.example.systemdesign.tinyurl.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.trace.http.HttpTrace.Principal;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.example.systemdesign.tinyurl.service.TinyKeyGenerationService;

/**
 * @author govindaraju.v
 *
 */
@Controller
public class TinyKeyGenerationController {

	@Autowired
	TinyKeyGenerationService tinyKeyGenerationService;

	@Autowired
	OAuth2ClientContext clientContext;

	// @RequestMapping(value = "/generateShortURL", method = RequestMethod.GET)
	@PostMapping(value = "/generateShortURL")
	public ModelAndView generateUrl(@RequestParam("originalURL") String url, ModelAndView model) {
		String tinyUrl = tinyKeyGenerationService.generateUrl(url);
		model.setViewName("tinyUrl");
		model.getModelMap().addAttribute("tinyUrl", tinyUrl);
		model.getModelMap().addAttribute("originalUrl", url);
		return model;
	}

	@GetMapping(value = "/{tinyUrl}")
	public RedirectView redirectTinyUrl(RedirectAttributes attributes, @PathVariable("tinyUrl") String tinyUrl) {
		String revRedirectUrl = tinyKeyGenerationService.reverseURL(tinyUrl);
		return new RedirectView(revRedirectUrl);
	}

	@GetMapping(value = "/access-token")
	@ResponseBody
	public String appTokenGenerator() {
		String token = clientContext.getAccessToken().getValue();
		System.out.println("access token : " + token);
		return token;
	}
	@GetMapping(value = "/user")
	@ResponseBody
	public Principal user(Principal principal) {
		return principal;
	}
	
}
