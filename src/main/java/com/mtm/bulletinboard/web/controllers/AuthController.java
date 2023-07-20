package com.mtm.bulletinboard.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.mtm.bulletinboard.web.form.LoginForm;

import jakarta.validation.Valid;

@Controller
public class AuthController {

	@GetMapping({ "/", "/login" })
	public String index(Model model, LoginForm loginForm) {
		model.addAttribute("loginForm", loginForm);
		return "login";
	}

	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("loginForm") LoginForm loginForm,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "login";
		}
		return "posts";
	}
}
