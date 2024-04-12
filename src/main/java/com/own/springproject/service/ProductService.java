package com.own.springproject.service;

import java.util.List;

import com.own.springproject.model.Product;


public interface ProductService {

	void addProd(Product prod);
	void deleteProd(int id);
	void updateProd(Product prod);
	Product getProdById(int id);
	List<Product> getAllProds();
}
