package com.own.springproject.service.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.own.springproject.model.Product;
import com.own.springproject.repository.ProductRepository;
import com.own.springproject.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository prodRepo;
	
	@Override
	public void addProd(Product prod) {
		prodRepo.save(prod);
	}

	@Override
	public void deleteProd(int id) {
		prodRepo.deleteById(id);
	}

	@Override
	public void updateProd(Product prod) {
		prodRepo.save(prod);	
	}

	@Override
	public Product getProdById(int id) {
		return prodRepo.findById(id).get() ;
	}

	@Override
	public List<Product> getAllProds() {
		return prodRepo.findAll();
	}

}
