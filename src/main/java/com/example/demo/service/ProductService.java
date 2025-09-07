package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ProductResponse;
import com.example.demo.model.Product;

@Service
public interface ProductService {

	
	public List<ProductResponse> findAll();	
	
	public Optional<ProductResponse> findById(long id);
	
	public ProductResponse save(Product p);
	
	public void delete(long p);
	
	public Page<ProductResponse> findAll(Pageable pageable);
	
}
