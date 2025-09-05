package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.model.Product;
import com.example.demo.repositary.ProductRepositary;

@Service
public interface ProductService {

	
	public List<Product> findAll();	
	public Optional<Product> findById(long id);
	public Product save(Product p);
	public void delete(long p);
	
}
