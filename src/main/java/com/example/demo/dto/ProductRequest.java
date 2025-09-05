package com.example.demo.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequest {
	
	@NotBlank(message = "Name is required")
	private String name;
	
	@NotBlank(message = "SKU is Required ")
	private String sku;
	
	@NotNull(message = "Price is Required ")
	@Min(value = 1,message = "Price should be greated than 1")
	private Double price;
	
	@NotNull(message = "Stock is required")
	@Min(value = 0 , message = "Stock can not be in negative")
	private Integer stock;
	

}
