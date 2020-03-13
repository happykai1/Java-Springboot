package com.project.service;

import java.security.NoSuchAlgorithmException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller

public class SignupController {

	@Autowired
	UserService userService;

	@RequestMapping("/user/regis")
	public ModelAndView signup(ModelAndView modelAndView) {
		modelAndView.setViewName("/user/regis");
		modelAndView.addObject("userAddForm", new AddForm());
		return modelAndView;
	}

	@RequestMapping("/user/add")
	public Object add(@ModelAttribute("userAddForm") @Valid AddForm userAddForm, BindingResult bindingResult,

			RedirectAttributes attributes, ModelAndView modelAndView) throws

	NoSuchAlgorithmException {
		if (bindingResult.hasErrors()) {
			return "user/regis";
		}
		userService.createUser(userAddForm);
		attributes.addFlashAttribute("messageDialog", "User was created.");
		return "redirect:/";
	}

}
