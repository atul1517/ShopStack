package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ProductResponse;
import com.example.demo.mapper.ProductMapper;
import com.example.demo.model.Product;
import com.example.demo.repositary.ProductRepositary;

@Service
public class ProductServiceImpl implements ProductService {

	
    private final ProductRepositary repositary;
    private final ProductMapper mapper;
	
	public ProductServiceImpl(ProductRepositary repositary , ProductMapper mapper)
	{
		this.repositary = repositary;
		this.mapper     = mapper;
	}
	
	public List<ProductResponse> findAll()
	{
		return repositary.findAll()
				.stream()
				.map(ProductMapper::toResponse)
				.collect(Collectors.toList());
	}
	
	public Optional<ProductResponse> findById(long id)
	{
		return repositary.findById(id)
				.map(ProductMapper::toResponse);
	}
	
	public ProductResponse save(Product p)
	{
		Product product =  repositary.save(p);
		return mapper.toResponse(product);
	}
	
	public void delete(long p)
	{
		repositary.deleteById(p);
	}

	@Override
	public Page<ProductResponse> findAll(Pageable pageable) {
		
		Page<Product> products = repositary.findAll(pageable);
		
		return products.map(ProductMapper::toResponse);
		
	}
}
