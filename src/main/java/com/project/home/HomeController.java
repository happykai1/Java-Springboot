package com.project.home;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.project.service.AspectController;

@Controller
public class HomeController {

	@Autowired
	AspectController controllerAspect;
	
	@RequestMapping("/user/main")
	public ModelAndView home(ModelAndView modelAndView, Principal principal){
		controllerAspect.setIsLogin(modelAndView, principal);
	modelAndView.setViewName("/user/main");
	return modelAndView;
	}
	@RequestMapping("/")
	public String login(){
		return "user/login";
	}
	
	
}
