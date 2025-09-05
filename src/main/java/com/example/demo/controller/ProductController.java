package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ProductRequest;
import com.example.demo.dto.ProductResponse;
import com.example.demo.mapper.ProductMapper;
import com.example.demo.model.Product;
import com.example.demo.service.ProductService;

import lombok.experimental.var;


@RestController
@RequestMapping("/products")
public class ProductController {

	
	private final ProductService service;
	
	public ProductController(ProductService service)
	{
		this.service = service;
	}
	
	@GetMapping
	public List<ProductResponse> getAllProducts()
	{
		return service.findAll().stream()
				.map(ProductMapper::toDto)
				.toList();
				
	}
	
	@GetMapping("/{id}")
	public ProductResponse findById(@PathVariable long id)
	{
		return service.findById(id)
				.map(ProductMapper::toDto)
				.orElseThrow(() -> new RuntimeException("Can not find the "+id));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProductResponse save(@RequestBody ProductRequest product)
	{
		var Product = ProductMapper.ToEntity(product);
		var saved   = service.save(Product);
		return ProductMapper.toDto(saved);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable long id)
	{
		service.delete(id);
	}
	
	
}
