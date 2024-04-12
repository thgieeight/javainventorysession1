package com.own.springproject.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
import com.own.springproject.service.CategoryService;
import com.own.springproject.util.FileUtil;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class CategoryController {

	@Autowired
	private CategoryService catService;
	@Autowired
	private FileUtil fileUtil;

	
	@GetMapping("/category")
	public String getcat(HttpSession session) {
		
		if(session.getAttribute("validuser")== null) {
			return "login";
		}
		return "admin/category";
	}
	
	@PostMapping("/category/add")
	public String postCat(@ModelAttribute Category cat,@RequestParam(value = "status", required = false) String status, @RequestParam("image") MultipartFile image, Model model) {
	    LocalDateTime currentDateAndTime = LocalDateTime.now();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    String formattedDateTime = currentDateAndTime.format(formatter);
	    if (!image.isEmpty()) {
	        fileUtil.fileUpload(image);
	        cat.setPhoto(image.getOriginalFilename());
	        cat.setAddedDate(formattedDateTime);
	        cat.setStatus(status != null && status.equals("1") ? 1 : 0); 
	        catService.addCat(cat);
	        model.addAttribute("message", "Upload success");
	    } else {
	        model.addAttribute("message", "Upload fail");
	    }
	    return "admin/categoryadd";
	}

	
	@GetMapping("/category/add")
	public String getcatadd(HttpSession session) {
		if(session.getAttribute("validuser")== null) {
			return "login";
		}
		return "admin/categoryadd";
	}
	
	@GetMapping("/category/edit")
	public String getedit(@RequestParam int cid, Model model) {
		
		model.addAttribute("catObject",catService.getCatById(cid));
		
		return "admin/categoryedit";
	}
	
	@PostMapping("/category/update")
	public String update(@ModelAttribute Category cat, @RequestParam(value = "status", required = false) String status, @RequestParam(value = "image", required = false) MultipartFile image, Model model) {
	    Category existingCat = catService.getCatById(cat.getCid());
	    if (image.isEmpty()) {
	        cat.setPhoto(existingCat.getPhoto());
	    } else {
	        fileUtil.fileUpload(image);
	        cat.setPhoto(image.getOriginalFilename());
	    }
	    cat.setAddedDate(existingCat.getAddedDate());
	    cat.setStatus(status != null && status.equals("1") ? 1 : 0);
	    catService.updateCat(cat);
	    return "redirect:/admin/category/list";
	}


	@GetMapping("/category/delete")
	public String delete(@RequestParam int cid) {
		catService.deleteCat(cid);
		return "redirect:/admin/category/list";
	}

	
	@GetMapping("/category/list")
	public String getcatlist(Model model ) {
		model.addAttribute("catList", catService.getAllCats());
		return "admin/categorylist";
	}
}
