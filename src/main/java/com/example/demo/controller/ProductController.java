package com.example.demo.controller;

import java.util.List;


import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ProductRequest;
import com.example.demo.dto.ProductResponse;
import com.example.demo.exception.ProductNotFoundException;
import com.example.demo.mapper.ProductMapper;
import com.example.demo.service.ProductService;

import jakarta.validation.Valid;



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
		return service.findAll();		
	}
	
	@GetMapping("/{id}")
	public ProductResponse findById(@PathVariable long id)
	{
		return service.findById(id)
				.orElseThrow(() -> new ProductNotFoundException(id));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProductResponse save(@Valid @RequestBody ProductRequest product)
	{
		var Product = ProductMapper.ToEntity(product);
		return service.save(Product);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable long id)
	{
		service.delete(id);
	}
	
	@GetMapping("/paged")
	public List<ProductResponse> getAllProductPaged(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size,
			@RequestParam(defaultValue = "id") String sortBy,
			@RequestParam(defaultValue = "asc") String direction)
	{
		
		Sort sort = direction.equalsIgnoreCase("desc") ?
				                Sort.by(sortBy).descending():
				                Sort.by(sortBy).ascending();
		
		Pageable pageable = PageRequest.of(page, size,sort);
		
		return service.findAll(pageable).getContent();
		
	}
	
	
}
