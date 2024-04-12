package com.own.springproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.own.springproject.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
