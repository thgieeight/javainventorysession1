package com.own.springproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.own.springproject.model.Sell;

public interface SellRepository extends JpaRepository<Sell, Integer> {

}
