package com.own.springproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.own.springproject.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
