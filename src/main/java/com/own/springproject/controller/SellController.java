package com.own.springproject.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.own.springproject.model.Inventory;
import com.own.springproject.model.Sell;
import com.own.springproject.service.InventoryService;
import com.own.springproject.service.SellService;
import com.own.springproject.util.SellExcell;
import com.own.springproject.util.SellPdf;


@Controller
@RequestMapping("/admin")
public class SellController {
	
	@Autowired
	private InventoryService invser;
	
	@Autowired
	private SellService sellser;
	
	@GetMapping("/sell/add")
	public String getselladd(Model model ) {
		model.addAttribute("invList", invser.getAllInvs());
		return "admin/selladd";
	}
	
	@PostMapping("/sell/add")
	public String postProduct(@ModelAttribute Sell sell, @RequestParam("invid") int invid, Model model) {
	    LocalDateTime currentDateAndTime = LocalDateTime.now();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    String formattedDateTime = currentDateAndTime.format(formatter);
	    
	    
	    	Inventory inv = invser.getInvById(invid);
	    	sell.setInventory(inv);
	        sell.setAddedDate(formattedDateTime);
	        
	        
	        int b = inv.getQuantity();
	    	int a = sell.getSellquantity();

	    	if(b>=a)
	    	{
	        sellser.addSell(sell);
	        model.addAttribute("invList", invser.getAllInvs());
	        model.addAttribute("message", "successful added");
		    return "admin/selladd";

	    	}else {
		        model.addAttribute("message", "not that much quantity in my inventory");
	        model.addAttribute("invList", invser.getAllInvs());
		    return "admin/selladd";
	    	}
	}
	



	@GetMapping("/sell/list")
	public String getselllist(Model model) {
	    List<Sell> sellList = sellser.getAllSells(); // Fetch all inventory.
	    model.addAttribute("sellList", sellList); // Add inventoryList to the model
	    return "admin/selllist"; // Return the inventory list view		
	    }
	
	@GetMapping("/sellexcell")
	public ModelAndView excleView() {
		ModelAndView mv = new ModelAndView();
		mv.setView(new SellExcell());
		mv.addObject("list", sellser.getAllSells());
		return mv;
	}
	
	
	@GetMapping("/sellpdf")
	public ModelAndView pdfView() {
		ModelAndView mv = new ModelAndView();
		mv.setView(new SellPdf());
		mv.addObject("list", sellser.getAllSells());
		return mv;
	}

}
