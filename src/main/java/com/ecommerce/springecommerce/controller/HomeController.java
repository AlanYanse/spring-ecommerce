package com.ecommerce.springecommerce.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecommerce.springecommerce.service.IProductoService;

@Controller
@RequestMapping("/")
public class HomeController {
	
	private Logger log = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private IProductoService iProductoService;
	
	@GetMapping("")
	public String home(Model model) {
		
		model.addAttribute("productos", iProductoService.findAll());
		
		return "usuario/home";
	}
	
	@GetMapping("productohome/{id}")
	public String productoHome( @PathVariable int id) {
		
		log.info("Id del producto enviado por parámetro {}", id);
		
		return "usuario/productohome";
	}

}
