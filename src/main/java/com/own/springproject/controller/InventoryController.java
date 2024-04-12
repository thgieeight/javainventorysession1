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
import org.springframework.web.multipart.MultipartFile;

import com.own.springproject.model.Inventory;
import com.own.springproject.model.Product;
import com.own.springproject.model.Sell;
import com.own.springproject.repository.SellRepository;
import com.own.springproject.service.InventoryService;
import com.own.springproject.service.ProductService;
import com.own.springproject.util.FileUtil;

@Controller
@RequestMapping("/admin")
public class InventoryController {
	
	@Autowired
	private ProductService prodser;
	
	@Autowired
	private FileUtil fileUtil;
	
	@Autowired
	private InventoryService invserv;
	
	@Autowired
	private SellRepository sellrepo;

	@GetMapping("/inventory/add")
	public String getinvadd(Model model) {
		model.addAttribute("prodList",prodser.getAllProds());
		return "admin/inventoryadd";
	}
	
	@PostMapping("/inventory/add")
	public String postInv(@ModelAttribute Inventory inv, @RequestParam(value = "status", required = false) String status,
 @RequestParam("image") MultipartFile image, @RequestParam("id") int id, Model model) {
	    LocalDateTime currentDateAndTime = LocalDateTime.now();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    String formattedDateTime = currentDateAndTime.format(formatter);
	    if (!image.isEmpty()) {
	        fileUtil.fileUpload(image);
	        inv.setPhoto(image.getOriginalFilename());
	        inv.setAddedDate(formattedDateTime);
	        inv.setStatus(status != null && status.equals("1") ? 1 : 0); 
	        Product product = prodser.getProdById(id);
	        inv.setProduct(product);
	        invserv.addInv(inv);
	        model.addAttribute("message", "Upload success");
	        model.addAttribute("prodList", prodser.getAllProds() );
	    } else {
	        model.addAttribute("message", "Upload fail");
	    }
	    return "admin/inventoryadd";
	}
	
	@GetMapping("/inventory/list")
	public String getinvlist(Model model) {
	    List<Inventory> invList = invserv.getAllInvs(); // Fetch all inventory.
	    model.addAttribute("invList", invList); // Add inventoryList to the model
	    
	    
	    return "admin/inventorylist"; // Return the inventory list view	
	    
	    }
	
	@GetMapping("/inventory/edit")
	public String getedit(@RequestParam int invid, Model model) {
		
		model.addAttribute("invObject",invserv.getInvById(invid));
		
		return "admin/inventoryedit";
	}
	
	@PostMapping("/inventory/update")
	public String update(@ModelAttribute Inventory inv, @RequestParam(value = "status", required = false) String status, @RequestParam(value = "image", required = false) MultipartFile image, Model model) {
	    Inventory existingInv = invserv.getInvById(inv.getInvid());
	    if (image.isEmpty()) {
	        inv.setPhoto(existingInv.getPhoto());
	    } else {
	        fileUtil.fileUpload(image);
	        inv.setPhoto(image.getOriginalFilename());
	    }
	    inv.setAddedDate(existingInv.getAddedDate());
	    inv.setStatus(status != null && status.equals("1") ? 1 : 0);
	    inv.setProduct(existingInv.getProduct());
	    inv.setSellprice(existingInv.getSellprice());
	    inv.setCostprice(existingInv.getCostprice());
	    inv.setQuantity(existingInv.getQuantity());
	    invserv.updateInv(inv);
	    return "redirect:/admin/inventory/list";
	}
	
	@GetMapping("/inventory/delete")
	public String getdelete(@ModelAttribute Inventory inv, @RequestParam int invid, Model model) {

    	Inventory invs = invserv.getInvById(invid);        
        int b = invs.getQuantity();
		if(b==0) {
			invserv.deleteInv(invid);
		}else {
			model.addAttribute("msg","it cannot be delete because it is still remaining");
		    return "redirect:/admin/inventory/list";

		}

	    return "redirect:/admin/inventory/list";
	}
	
	
}

