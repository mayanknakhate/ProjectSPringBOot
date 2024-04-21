package com.work.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.work.Model.Product;
import com.work.dao.ProductRepo;

@Service
public class ProductService {
@Autowired
ProductRepo productrepo;
public List<Product> getAllProduct(){
	return productrepo.findAll();
	
}
public void saveProduct(Product product) {
	productrepo.save(product);
}
public void deleteproduct(long id) {
	productrepo.deleteById(id);
}
public Optional<Product> getProductsById(long id){
	return productrepo.findById(id);
	
}

public List<Product> getAllproductsById(int id){
	return productrepo.findAllByCategory_Id(id);
	
}
}
