package com.own.springproject.service;

import java.util.List;

import com.own.springproject.model.Sell;


public interface SellService {

	void addSell(Sell sell);
	Sell getSellById(int sellid);
	List<Sell> getAllSells();
}
