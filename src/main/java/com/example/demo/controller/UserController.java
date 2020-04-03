package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Service.UserService;
import com.example.demo.entity.User;

@Controller
public class UserController {
@Autowired
private UserService userService;

@RequestMapping(value = "/home",method = RequestMethod.GET)
public String index(Model model) {
	model.addAttribute("users", userService.getAllUser());
	return "index";
}

@GetMapping(value = "/add")
public String add(Model model) {
	model.addAttribute("user", new User());
	return "addUser";
}

@GetMapping(value = "/edit/{id}")
public String edit(Model model, @PathVariable long id) {
	model.addAttribute("user", userService.findUserById(id));
	return "editUser";
}

@RequestMapping(value = "/save",method = RequestMethod.POST)
public String save(@Valid User user) {
	userService.saveUser(user);
	return "redirect:/home";
}

@GetMapping(value = "/delete/{id}")
public String delete(@PathVariable Long id) {
	userService.deleteUser(id);
	return "redirect:/home";
}

@GetMapping(value = "/search")
public String search(@RequestParam("s") String s, Model model) {
	if(s.equals("")) {
		return "redirect:/home";
	}
	model.addAttribute("users", userService.search(s));
	return "index";
}
	
}
