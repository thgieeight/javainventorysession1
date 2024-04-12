package com.own.springproject.service;

import java.util.List;

import com.own.springproject.model.Category;

public interface CategoryService {

	void addCat(Category cat);
	void deleteCat(int cid);
	void updateCat(Category cat);
	Category getCatById(int cid);
	List<Category> getAllCats();

}
