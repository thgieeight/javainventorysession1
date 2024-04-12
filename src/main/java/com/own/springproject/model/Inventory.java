package com.own.springproject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table
public class Inventory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int invid;
	private String invname;
	private String photo;
	private String addedDate;
	private double sellprice;
	private double costprice;
	private int quantity;
	private int status;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
}
