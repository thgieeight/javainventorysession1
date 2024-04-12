package com.own.springproject.service.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.own.springproject.model.Sell;
import com.own.springproject.repository.SellRepository;
import com.own.springproject.service.SellService;

@Service
public class SellServiceImpl implements SellService {

	@Autowired
	private SellRepository sellrepo;
	
	@Override
	public void addSell(Sell sell) {
		sellrepo.save(sell);
	}

	@Override
	public Sell getSellById(int sellid) {
		return sellrepo.findById(sellid).get();
	}

	@Override
	public List<Sell> getAllSells() {
		return sellrepo.findAll();
	}

}
