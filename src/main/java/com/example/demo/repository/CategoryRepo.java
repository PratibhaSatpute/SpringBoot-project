package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.CategoryModel;
@Repository
public  interface CategoryRepo extends JpaRepository<CategoryModel, Long> {
	List<CategoryModel> findByPublished(boolean published);
	  List<CategoryModel> findByTitleContaining(String title);
}
