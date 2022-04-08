package com.example.demo.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.ProductModel;
import com.example.demo.repository.CategoryRepo;
import com.example.demo.repository.ProductRepo;
@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class Product {
	@Autowired
	  private ProductRepo productRepo;
	  @Autowired
	  private CategoryRepo categoryRepo;
	  @GetMapping("/category/{categoryId}/product")
	  public ResponseEntity<List<ProductModel>> getAllproductsByCategoryId(@PathVariable(value = "categoryId") Long categoryId) {
	    if (!categoryRepo.existsById(categoryId)) {
	      throw new ResourceNotFoundException("Not found Tutorial with id = " + categoryId);
	    }
	    List<ProductModel> comments = productRepo.findByCategoryId(categoryId);
	    return new ResponseEntity<>(comments, HttpStatus.OK);
	  }
	  @GetMapping("/product/{id}")
	  public ResponseEntity<ProductModel> getPrroductsByCategoryId(@PathVariable(value = "id") Long id) {
	    ProductModel comment = productRepo.findById(id)
	        .orElseThrow(() -> new ResourceNotFoundException("Not found product with id = " + id));
	    return new ResponseEntity<>(comment, HttpStatus.OK);
	  }
	  
	  @PutMapping("/product/{id}")
	  public ResponseEntity<ProductModel> updateComment(@PathVariable("id") long id, @RequestBody ProductModel commentRequest) {
		  ProductModel product = productRepo.findById(id)
	        .orElseThrow(() -> new ResourceNotFoundException("ProductId " + id + "not found"));
	    product.setContent(commentRequest.getContent());
	    return new ResponseEntity<>(productRepo.save(product), HttpStatus.OK);
	  }
	  @DeleteMapping("/products/{id}")
	  public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("id") long id) {
	    productRepo.deleteById(id);
	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	  }
	  
	  @DeleteMapping("/category/{categoryId}/comments")
	  public ResponseEntity<List<ProductModel>> deleteAllproductOfCategory(@PathVariable(value = "tutorialId") Long categoryId) {
	    if (!categoryRepo.existsById(categoryId)) {
	      throw new ResourceNotFoundException("Not found Category with id = " + categoryId);
	    }
	    productRepo.deleteByCategoryId(categoryId);
	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	  }
}
