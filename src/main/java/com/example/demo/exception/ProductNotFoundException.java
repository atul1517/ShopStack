package com.example.demo.exception;

public class ProductNotFoundException extends RuntimeException
{
	public ProductNotFoundException(long id)
	{
		super("Product with id"+id+"is not found");
	}

}
