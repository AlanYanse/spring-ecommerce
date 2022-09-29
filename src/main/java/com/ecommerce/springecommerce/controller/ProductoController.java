package com.ecommerce.springecommerce.controller;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecommerce.springecommerce.entity.Producto;
import com.ecommerce.springecommerce.entity.Usuario;
import com.ecommerce.springecommerce.service.IProductoService;

@Controller
@RequestMapping("/productos")
public class ProductoController {
	
	private final Logger logger = LoggerFactory.getLogger(ProductoController.class);
	
	@Autowired
	private IProductoService iProductoService;
	
	@GetMapping("")
	public String show() {
		
		return "productos/show";
	}
	
	@GetMapping("/create")
	public String create() {
		
		return "productos/create";
	}
	
	
	@PostMapping("/save")
	public String save(Producto producto) {
		
		logger.info("Este es el objeto producto {}", producto.toString());
		Usuario usuario = new Usuario(1, "", "", "", "", "", "", "");
		producto.setUsuario(usuario);
		
		iProductoService.save(producto);
		return "redirect:/productos";
	}

}
