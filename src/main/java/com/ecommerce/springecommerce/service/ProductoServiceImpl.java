package com.ecommerce.springecommerce.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.springecommerce.entity.Producto;
import com.ecommerce.springecommerce.repository.IProductoRepository;

@Service
public class ProductoServiceImpl implements IProductoService {
	
	@Autowired
	IProductoRepository iProductoRepository;

	@Override
	public Producto save(Producto producto) {
		// TODO Auto-generated method stub
		return iProductoRepository.save(producto);
	}

	@Override
	public Optional<Producto> get(Integer id) {
		// TODO Auto-generated method stub
		return iProductoRepository.findById(id);
	}

	@Override
	public void update(Producto producto) {
		// TODO Auto-generated method stub
		iProductoRepository.save(producto);
		
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		iProductoRepository.deleteById(id);
		
	}

}
