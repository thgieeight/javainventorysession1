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

import com.own.springproject.model.Category;
import com.own.springproject.model.Product;
import com.own.springproject.service.CategoryService;
import com.own.springproject.service.ProductService;
import com.own.springproject.util.FileUtil;


@Controller
@RequestMapping("/admin")
public class ProductController {

	@Autowired
	private CategoryService catser;
	
	@Autowired
	private ProductService prodser;
	
	@Autowired
	private FileUtil fileUtil;
	
	@GetMapping("/product/add")
	public String getprodadd(Model model) {
		model.addAttribute("catList",catser.getAllCats());
		return "admin/productadd";
	}
	
	@PostMapping("/product/add")
	public String postProduct(@ModelAttribute Product product, @RequestParam(value = "status", required = false) String status,
 @RequestParam("image") MultipartFile image, @RequestParam("cid") int cid, Model model) {
	    LocalDateTime currentDateAndTime = LocalDateTime.now();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    String formattedDateTime = currentDateAndTime.format(formatter);
	    if (!image.isEmpty()) {
	        fileUtil.fileUpload(image);
	        product.setPhoto(image.getOriginalFilename());
	        product.setAddedDate(formattedDateTime);
	        product.setStatus(status != null && status.equals("1") ? 1 : 0); 
	        Category category = catser.getCatById(cid);
	        product.setCategory(category);
	        prodser.addProd(product);
	        model.addAttribute("message", "Upload success");
			model.addAttribute("catList",catser.getAllCats());
	    } else {
	        model.addAttribute("message", "Upload fail");
	    }
	    return "admin/productadd";
	}


	@GetMapping("/product/delete")
	public String delete(@RequestParam int id) {
		prodser.deleteProd(id);
		return "redirect:/admin/product/list";
	}
	
	@GetMapping("/product/edit")
	public String getedit(@RequestParam int id, Model model) {
		
		model.addAttribute("prodObject",catser.getCatById(id));
		
		return "admin/productedit";
	}
	
	@PostMapping("/product/update")
	public String update(@ModelAttribute Product prod, @RequestParam(value = "status", required = false) String status, @RequestParam(value = "image", required = false) MultipartFile image, Model model) {
	    Product existingProd = prodser.getProdById(prod.getId());
	    if (image.isEmpty()) {
	        prod.setPhoto(existingProd.getPhoto());
	    } else {
	        fileUtil.fileUpload(image);
	        prod.setPhoto(image.getOriginalFilename());
	    }
	    prod.setAddedDate(existingProd.getAddedDate());
	    prod.setStatus(status != null && status.equals("1") ? 1 : 0);
	    prod.setCategory(existingProd.getCategory());
	    prodser.updateProd(prod);
	    return "redirect:/admin/product/list";
	}
	
	
	@GetMapping("/product/list")
	public String getProdList(Model model) {
	    List<Product> productList = prodser.getAllProds(); // Fetch all products.
	    model.addAttribute("productList", productList); // Add productList to the model
	    return "admin/productlist"; // Return the product list view
	}

}
