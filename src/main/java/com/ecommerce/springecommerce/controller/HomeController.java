package com.ecommerce.springecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecommerce.springecommerce.service.IProductoService;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@Autowired
	private IProductoService iProductoService;
	
	@GetMapping("")
	public String home(Model model) {
		
		model.addAttribute("productos", iProductoService.findAll());
		
		return "usuario/home";
	}

}
