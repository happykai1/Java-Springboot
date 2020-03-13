package com.project.service;

import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import lombok.Data;
import com.project.service.PageWrapper;
import com.project.service.EditForm;
import com.project.entity.User;
import com.project.service.UserService;

@Controller
public class ControlUser {
	@Autowired
	UserService userService;

	@RequestMapping("/user/list")
	public ModelAndView list(ModelAndView modelAndView, Principal principal, Pageable pageable) {
		Page<User> userPage = userService.findAllUser(pageable);
		PageWrapper<User> page = new PageWrapper<>(userPage, "/user/list");
		modelAndView.addObject("users", page.getContent());
		modelAndView.addObject("page", page);
		modelAndView.addObject("userId", userService.getUserId(principal));
		modelAndView.setViewName("user/list");
		return modelAndView;
	}

	@RequestMapping(value = "/user/edit/{userId}", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable int userId, ModelAndView modelAndView, Principal principal) {
		modelAndView.addObject("user", userService.findOne(userId));
		modelAndView.addObject("EditForm", userService.setUserEditForm(userId));
		modelAndView.setViewName("user/edit");
		return modelAndView;
	}

	@RequestMapping(value = "/user/edit", method = RequestMethod.POST)
	public Object edit(@Validated EditForm EditForm, BindingResult bindingResult,
			RedirectAttributes redirectAttributes, ModelAndView modelAndView, Principal principal)
			throws NoSuchAlgorithmException {
		if (bindingResult.hasErrors()) {
			modelAndView.addObject("user", userService.findOne(EditForm.getUserId()));
			modelAndView.setViewName("user/edit");
			return modelAndView;
		}
		userService.updateUser(EditForm);
		redirectAttributes.addFlashAttribute("message", "User was updated.");
		return "redirect:/user/main";
	}

	@RequestMapping(value = "/user/delete/{userId}")
	public String delete(@PathVariable int userId, RedirectAttributes redirectAttributes) {
		userService.deleteUser(userId);
		redirectAttributes.addFlashAttribute("message", "User was deleted.");
		return "redirect:/user/main";
	}
	

	
}