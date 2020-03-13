package com.project.service;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

	@RequestMapping(value = "/user/failLogin", method = RequestMethod.GET)
	public ModelAndView failLogin(ModelAndView model) {
		model.addObject("errorMsg", "Login failed, Invalid name and/or passwod");
		model.addObject("loginFail", true);
		model.setViewName("/user/login");
		return model;
	}

	@RequestMapping(value = "/user/successLogin", method = RequestMethod.GET)
	public Object successLogin(ModelAndView model, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			model.setViewName("/user/login");
			return model;
		}
		redirectAttributes.addFlashAttribute("message", "Login Successful");
		model.addObject("loginSuccess", true);
		 model.setViewName("/user/main"); 
		return model;
	}

	@RequestMapping("/loginUser")
	@ResponseBody
	public String currentUser(Principal principal) {
		if (principal != null) {
			return principal.getName();
		} else {
			return "pricipal is null!!";
		}
	}

	@RequestMapping("/user/login")

	public String login() {

		return "/user/login";

	}
}