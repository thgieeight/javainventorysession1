package com.own.springproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.own.springproject.model.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {

}
