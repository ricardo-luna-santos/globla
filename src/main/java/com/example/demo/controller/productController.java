package com.example.demo.controller;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Product;
import com.example.demo.repository.productRepository;

@RestController
public class productController {
	@Autowired
	productRepository repProduct;
	@GetMapping("/productos")
	public ResponseEntity<List<Product>> getAllC_products(@RequestParam(required=false) String description){
		try {
			List<Product> product= new ArrayList<Product>();
			if(description==null)
				repProduct.findAll().forEach(product::add);
			else
				repProduct.findByDescription(description).forEach(product::add);
				if(product.isEmpty()) {
					return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
				}
			return new ResponseEntity<>(product, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PostMapping("/addproducto")
	 public ResponseEntity<Product> createProduct(@RequestBody Product producto){
		 try {
			 Product _product = repProduct
					 .save(new Product(  			
							 producto.getDescription(),
							 producto.getQuantity(),
							 producto.getPrice()					 
							 ));
			 return new ResponseEntity<>( _product, HttpStatus.CREATED); 
		 }catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
	 }
	
	 @DeleteMapping("/delproducto/{id_product}")
	 public ResponseEntity<HttpStatus> deletePost(@PathVariable Integer id_product){ 
		 try {
			 repProduct.deleteById(id_product);
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);			 
		 }catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	 }
	 
	 @PutMapping("/updateproductos/{id_product}")
	 public ResponseEntity<Product> updateProducto (@PathVariable("id_product") Integer id_product, @RequestBody Product product ){
			Optional<Product> productData = repProduct.findById(id_product);
			if (productData.isPresent()) {
				Product _product= productData.get();
				_product.setDescription(product.getDescription());
				_product.setQuantity(product.getQuantity());
				_product.setPrice(product.getPrice());				
	    	 return new ResponseEntity<>( repProduct.save(_product), HttpStatus.OK);
			 }else{
		
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}
		 }

}
