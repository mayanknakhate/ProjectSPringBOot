package com.work.c;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.work.Model.Category;
import com.work.Model.Product;
import com.work.Service.ProductService;
import com.work.Service.UserService;
import com.work.globalSet.GlobalDataa;

@Controller
public class MainController {
	@Autowired
	 ProductService productservice;
  @Autowired
UserService service;
@GetMapping("/admin")
public String trial(Model model) {
   model.addAttribute("name", "aj");
   List<String> l= List.of("aj","nus");//of("aj","nus");
   model.addAttribute("names", l);
   
  // model.addAttribute("CurrentDate", new Date(0));
	return"adminHome";
}

@GetMapping("/admin/categories")
public String getc(Model model)
{
	
	List<Category> l=service.getAllCategory();
	model.addAttribute("categories", l);
	return "categories";
}


@GetMapping("/admin/categories/add")
public String getAddCet(Model model)
{
	model.addAttribute("category", new Category());
	return "categoriesAdd";
}

@PostMapping("/admin/categories/add")
public String setAddCet(@ModelAttribute("category") Category category, Model model)
{
	service.saveCategory(category);
	model.addAttribute("category", new Category());
	return "redirect:/admin/categories";
}

@GetMapping("/home")
public String index() {
	return"index";
}

@GetMapping("/shop")
public String shop(Model model) {
	model.addAttribute("products",productservice.getAllProduct());
	model.addAttribute("cartCount", GlobalDataa.cart.size());
	model.addAttribute("categories", service.getAllCategory());
	return"shop";
}
@GetMapping("/shop/category/{id}")
public String chooseproductByid(@PathVariable int id, Model model) {
	
	model.addAttribute("cartCount", GlobalDataa.cart.size());
	model.addAttribute("products", productservice.getAllproductsById(id));
	model.addAttribute("categories", service.getAllCategory());
	return "shop";
}
@GetMapping("/shop/viewproduct/{id}")
public String viewProductByid(@PathVariable long id, Model model) {
	model.addAttribute("cartCount", GlobalDataa.cart.size());
	model.addAttribute("product", productservice.getProductsById(id).get());
	return "viewProduct";
}

@GetMapping("/addToCart/{id}")
public String getCrte(@PathVariable int id) {
	GlobalDataa.cart.add(productservice.getProductsById(id).get());
	System.out.println("Cart Contents: " + GlobalDataa.cart);
	return "redirect:/shop";
	
}
@GetMapping("/cart")
public String getContents(Model model) {
	 double total = 0.0;

	    for (Product product : GlobalDataa.cart) {
	        total += product.getPrice();
	    }
	model.addAttribute("cartCount", GlobalDataa.cart.size());
	model.addAttribute("total",total);
	model.addAttribute("cart",GlobalDataa.cart);
	return "cart";
}




}
