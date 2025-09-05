package com.example.demo.mapper;

import com.example.demo.dto.ProductRequest;
import com.example.demo.dto.ProductResponse;
import com.example.demo.model.Product;

public class ProductMapper {
	
	
	public static Product ToEntity(ProductRequest request)
	{
		return Product.builder()
				.name(request.getName())
				.sku(request.getSku())
				.price(request.getPrice())
				.stock(request.getStock())
				.build();

	}
	
	public static ProductResponse toDto(Product response)
	{
		return ProductResponse.builder()
				.id(response.getId())
				.name(response.getName())
				.sku(response.getSku())
				.price(response.getPrice())
				.stock(response.getStock())
				.build();
	}

}
