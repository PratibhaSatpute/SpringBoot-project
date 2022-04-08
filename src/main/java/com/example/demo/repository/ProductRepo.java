package com.example.demo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.ProductModel;

public interface ProductRepo extends JpaRepository<ProductModel, Long> {
	List<ProductModel> findByCategoryId(Long postId);
	  
	  @Transactional
	  void deleteByCategoryId(long categoryId);

}
