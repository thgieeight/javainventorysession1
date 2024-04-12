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
@Table
@Data
public class Sell {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int sellid;
	private String custname;
	private String addedDate;
	private double sellamount;
	private int sellquantity;
	private String sellname; 
	
	@ManyToOne
	@JoinColumn(name = "inv_id")
	private Inventory inventory;

}
