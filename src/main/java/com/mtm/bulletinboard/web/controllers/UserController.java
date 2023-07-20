package com.mtm.bulletinboard.web.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mtm.bulletinboard.bl.dto.UserDto;
import com.mtm.bulletinboard.bl.services.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public String index(Model model) {
		List<UserDto> users = userService.getAllUsers();
		model.addAttribute("users", users);
		return "users";
	}

	@GetMapping("create")
	public String create() {
		// Show the form for creating a new user
		return "create";
	}

	@PostMapping("store")
	public String store() {
		// Store a newly created user
		return "store";
	}

	@GetMapping("/users/show")
	public String show() {
		// Show the specified user
		return "";
	}

	@GetMapping("/users/edit")
	public String edit() {
		// Show the form for editing the specified user
		return "";
	}

	@PutMapping("/users/update")
	public String update() {
		// Update the specified user
		return "";
	}

	@DeleteMapping("/users/destroy")
	public String destroy() {
		// Remove the specified user
		return "";
	}
}
