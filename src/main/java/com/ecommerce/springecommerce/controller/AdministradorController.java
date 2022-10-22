package com.ecommerce.springecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecommerce.springecommerce.entity.Producto;
import com.ecommerce.springecommerce.service.IProductoService;

@Controller
@RequestMapping("/administrador")
public class AdministradorController {
	
	@Autowired
	private IProductoService iProductoService;
	
	@GetMapping("")
	public String home(Model model) {
		
		List <Producto> productos = iProductoService.findAll();
		model.addAttribute("productos", productos);
		
		return "administrador/home";
	}

}
