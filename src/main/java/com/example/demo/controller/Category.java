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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.CategoryModel;
import com.example.demo.repository.CategoryRepo;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")

public class Category {

	@Autowired
	 CategoryRepo categoryRepo;
	  @GetMapping("/category")
	  public ResponseEntity<List<CategoryModel>> getAllCategory(@RequestParam(required = false) String title) {
	    List<CategoryModel> category = new ArrayList<CategoryModel>();
	    if (title == null)
	      categoryRepo.findAll().forEach(category::add);
	    else
	    	 categoryRepo.findByTitleContaining(title).forEach(category::add);
	    if (category.isEmpty()) {
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }
	    return new ResponseEntity<>(category, HttpStatus.OK);
	  }
	  
	  @GetMapping("/category/{id}")
	    public ResponseEntity < CategoryModel > getCategoryById(@PathVariable Long id) {
	      CategoryModel   cmodel = categoryRepo.findById(id) 
	  .orElseThrow(() -> new ResourceNotFoundException("Category not exist with id :" + id));
	        return ResponseEntity.ok(cmodel);
	      
	    }
	 
	  @PostMapping("/category")
	  public ResponseEntity<CategoryModel> createTutorial(@RequestBody CategoryModel category ) {
	    CategoryModel _category = categoryRepo.save(new CategoryModel(category.getTitle(), category.getDescription(), true));
	    return new ResponseEntity<>(_category, HttpStatus.CREATED);
	  }
	  
	  
	  @PutMapping("/category/{id}")
	  public ResponseEntity<CategoryModel> updateCategory(@PathVariable("id") long id, @RequestBody CategoryModel cmodel) {
	    CategoryModel _cmodel =  categoryRepo.findById(id)
	        .orElseThrow(() -> new ResourceNotFoundException("Not found Category with id = " + id));
	    _cmodel.setTitle(cmodel.getTitle());
	    _cmodel.setDescription(cmodel.getDescription());
	    _cmodel.setPublished(cmodel.isPublished());
	    
	    return new ResponseEntity<>(categoryRepo.save(_cmodel), HttpStatus.OK);
	  }
	  
	  
	  @DeleteMapping("/category/{id}")
	  public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
		  categoryRepo.deleteById(id);
	    
	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	  }
	  @DeleteMapping("/category")
	  public ResponseEntity<HttpStatus> deleteAllTutorials() {
		  categoryRepo.deleteAll();
	    
	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	  }
	  @GetMapping("/category/published")
	  public ResponseEntity<List<CategoryModel>> findByPublished() {
	    List<CategoryModel> tutorials =  categoryRepo.findByPublished(true);
	    if (tutorials.isEmpty()) {
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }
	    
	    return new ResponseEntity<>(tutorials, HttpStatus.OK);

	  }}

