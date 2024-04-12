package com.own.springproject.service.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.own.springproject.model.Category;
import com.own.springproject.repository.CategoryRepository;
import com.own.springproject.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository catRepo;

	
	@Override
	public void addCat(Category cat) {
		catRepo.save(cat);		
	}

	@Override
	public void deleteCat(int cid) {
		catRepo.deleteById(cid);		
	}

	@Override
	public void updateCat(Category cat) {
		catRepo.save(cat);				
	}

	@Override
	public Category getCatById(int cid) {
		return catRepo.findById(cid).get();

	}

	@Override
	public List<Category> getAllCats() {
		return catRepo.findAll();

	}

}
