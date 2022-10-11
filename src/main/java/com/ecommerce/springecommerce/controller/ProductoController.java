package com.ecommerce.springecommerce.controller;

import java.io.IOException;
import java.util.Optional;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.springecommerce.entity.Producto;
import com.ecommerce.springecommerce.entity.Usuario;
import com.ecommerce.springecommerce.service.IProductoService;
import com.ecommerce.springecommerce.service.UploadFileService;

@Controller
@RequestMapping("/productos")
public class ProductoController {

	private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);

	@Autowired
	private IProductoService iProductoService;

	@Autowired
	private UploadFileService uploadFileService;

	// ---------------------------------------------------------------------------------------------------

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
	public String save(Producto producto, @RequestParam("img") MultipartFile file) throws IOException {

		LOGGER.info("Este es el objeto producto {}", producto.toString());
		Usuario usuario = new Usuario(1, "", "", "", "", "", "", "");
		producto.setUsuario(usuario);

		// Imagen

		String nombreImagen = uploadFileService.saveImage(file);
		producto.setImagen(nombreImagen);

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
	public String update(Producto producto, @RequestParam("img") MultipartFile file) throws IOException {

		// imagen
		if (file.isEmpty()) { // Editamos el producto sin cambiar la imagen

			Producto p = new Producto();
			p = iProductoService.get(producto.getId()).get();
			producto.setImagen(p.getImagen());
		} else { // Cuando se edita también la imagen
			
			Producto p = new Producto();
			p = iProductoService.get(producto.getId()).get();
			
			// Elimina cuando la imagen no sea por defecto
			if(!p.getImagen().equals("default.jpg")) {
				
				uploadFileService.deleteImage(p.getImagen());
				
			}

			String nombreImagen = uploadFileService.saveImage(file);
			producto.setImagen(nombreImagen);
		}

		iProductoService.update(producto);

		return "redirect:/productos";
	}

	
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable int id) {
		
		Producto p = new Producto();
		p = iProductoService.get(id).get();
		
		// Elimina cuando la imagen no sea por defecto
		if(!p.getImagen().equals("default.jpg")) {
			
			uploadFileService.deleteImage(p.getImagen());
			
		}

		iProductoService.delete(id);

		return "redirect:/productos";
	}




}
