package com.work.c;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.work.DTO.ProductDTO;
import com.work.Model.Category;
import com.work.Model.Product;
import com.work.Service.ProductService;
import com.work.Service.UserService;

@Controller
public class AdminController {
	
	 @Autowired
	 UserService service;
	 @Autowired
	 ProductService productservice;
	 public static String uploadDir= "src/main/resources/static/productImages";
	 @GetMapping("/admin/categories/delete/{id}")
	 public String deleteCategories(@PathVariable int id, Model model) {
		 service.deleteCategry(id);
		return "redirect:/admin/categories";
		 
	 }
	 
	 @GetMapping("/admin/categories/update/{id}")
	 public String editCategories(@PathVariable int id, Model model) {
		
		 Category c= service.editCategry(id);
		 model.addAttribute("category",c);
		return "cateforiesUpdate";
		 
	 }
	 @PostMapping("/admin/categories/edit")
	 public String updateCategories(@ModelAttribute("c") Category c,Model model) {
		 service.updateCate(c);
		 model.addAttribute("category", new Category());
		 return "redirect:/admin/categories";
	 }
	 //product
	 @GetMapping("/admin/products")
	 public String productList( Model model) {
		
		model.addAttribute("products",productservice.getAllProduct());
		return "products";
		 
	 }
	 @GetMapping("/admin/products/add")
	 public String Nwproduct( Model model) {
		
		model.addAttribute("productDTO",new ProductDTO());
		model.addAttribute("categories",service.getAllCategory());
		return "productsAdd";
		 
	 }
	 @PostMapping("/admin/products/add")
	 public String saveProduct(@ModelAttribute("productDTO")ProductDTO productDTO,
			                   @RequestParam ("imgName") String imgName, 
			                   @RequestParam ("productImage")MultipartFile file) throws IOException {
		 Product product= new Product();
		 product.setId(productDTO.getId());
		 product.setName(productDTO.getName());
		 //for taking id of product dto from  form
		 product.setCategory(service.editCategry(productDTO.getCategoryId()));
		 product.setPrice(productDTO.getPrice());
		 product.setWeight(productDTO.getWeight());
		 product.setDescription(productDTO.getDescription());
		 String imageUUID;
		 if(!file.isEmpty()) {
			 imageUUID=file.getOriginalFilename();
			 System.err.println(imageUUID);
			 Path FileNameandPath= Paths.get(uploadDir,imageUUID);
			 Files.write(FileNameandPath,file.getBytes());
		 }
		 else {
			 imageUUID=imgName;
		 }
		 product.setImageName(imageUUID);
		 productservice.saveProduct(product);
		 
		return "redirect:/admin/products";
		 
	 }
	 @GetMapping("/admin/product/delete/{id}")
	 public String deleteProduct(@PathVariable int id, Model model) {
		 System.out.println(id);
		 productservice.deleteproduct(id);
		return "redirect:/admin/products";
		 
	 }
	 
	 @GetMapping("/admin/product/update/{id}")
	 public String updateproduct(@PathVariable int id, Model model) {
		 Product product = productservice.getProductsById(id).get();
		 ProductDTO productDTO=new ProductDTO();
		 productDTO.setId(product.getId());
		 productDTO.setName(product.getName());
		 productDTO.setCategoryId(product.getCategory().getId());
		 productDTO.setPrice(product.getPrice());
		 productDTO.setWeight(product.getWeight());
		 productDTO.setDescription(product.getDescription());
		 productDTO.setImageName(product.getImageName());
		 model.addAttribute("categories",service.getAllCategory());
		 model.addAttribute("productDTO",productDTO);
		return "productsAdd";
		 
	 }
		
}
