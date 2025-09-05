package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.model.Product;
import com.example.demo.repositary.ProductRepositary;

@Service
public class ProductServiceImpl implements ProductService {

	
    private final ProductRepositary repositary;
	
	public ProductServiceImpl(ProductRepositary repositary)
	{
		this.repositary = repositary;
	}
	
	public List<Product> findAll()
	{
		return repositary.findAll();
	}
	
	public Optional<Product> findById(long id)
	{
		return repositary.findById(id);
	}
	
	public Product save(Product p)
	{
		return repositary.save(p);
	}
	
	public void delete(long p)
	{
		repositary.deleteById(p);
	}
}
