package com.gaida.exam.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.gaida.exam.models.LoginUser;
import com.gaida.exam.models.User;
import com.gaida.exam.services.UserService;



@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
		
	@GetMapping("/")
	public String index(Model model) {
		
		model.addAttribute("newUser",new User());
		model.addAttribute("loginUser", new LoginUser());
	
		return "loginHome.jsp";
	}
	
	
	@PostMapping("/user/register")
	public String register(@Valid @ModelAttribute("newUser") User newUser, BindingResult result, HttpSession session, Model model) {
		
		User regUser = this.userService.checkRegister(newUser, result);
		
		if(regUser!=null) {
			session.setAttribute("userid", regUser.getId());
			return "redirect:/shows";
		}
		else {
			model.addAttribute("newUser",newUser);
			model.addAttribute("loginUser",new LoginUser());
			return "loginHome.jsp";
		}
	}
	
	@PostMapping("/user/login")
	public String login(@Valid @ModelAttribute("loginUser") LoginUser loginUser,BindingResult result,HttpSession session, Model model) {
		
		User loggedInUser =   this.userService.checkLogin(loginUser, result);
		
		if(loggedInUser==null) {
			model.addAttribute("newUser",new User());
			model.addAttribute("loginUser", loginUser);
			return "loginHome.jsp";
		}
		else {
			session.setAttribute("userid", loggedInUser.getId());
			return "redirect:/shows";
		}
	
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	

	

}
