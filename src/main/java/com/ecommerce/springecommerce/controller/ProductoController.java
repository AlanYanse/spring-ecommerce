package com.ecommerce.springecommerce.controller;

import java.util.Optional;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecommerce.springecommerce.entity.Producto;
import com.ecommerce.springecommerce.entity.Usuario;
import com.ecommerce.springecommerce.service.IProductoService;

@Controller
@RequestMapping("/productos")
public class ProductoController {
	
	private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);
	
	@Autowired
	private IProductoService iProductoService;
	
	@GetMapping("")
	public String show(Model model) {
		
		model.addAttribute("productos", iProductoService.findAll());
		
		return "productos/show";
	}
	
	@GetMapping("/create")
	public String create() {
		
		return "productos/create";
	}
	
	
	@PostMapping("/save")
	public String save(Producto producto) {
		
		LOGGER.info("Este es el objeto producto {}", producto.toString());
		Usuario usuario = new Usuario(1, "", "", "", "", "", "", "");
		producto.setUsuario(usuario);
		
		iProductoService.save(producto);
		return "redirect:/productos";
	}
	
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Integer id, Model model) {
		
		Producto producto = new Producto();
		Optional<Producto> optionalProducto = iProductoService.get(id);
		producto = optionalProducto.get(); // Esto permite capturar el producto que se quiere editar
		
		LOGGER.info("El objeto a modificar será {}", producto.toString());
		model.addAttribute("producto", producto);
		
		return "productos/edit";
	}
	
	@PostMapping("/update")
	public String update(Producto producto) {
		
		iProductoService.update(producto);
		
		return "redirect:/productos";
	}
	
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable int id) {
		
		iProductoService.delete(id);
		
		return "redirect:/productos";
	}
	
	
	
	
	
	

}
