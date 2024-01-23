package com.ojas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ojas.entity.ProductsEntity;
import com.ojas.repository.ProductRepository;

@Service
public class ProductService {

	private final ProductRepository productRepository;

	@Autowired
	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public List<ProductsEntity> getAllProducts() {
		List<ProductsEntity> productList = productRepository.findAll();
		return productList;
	}

	public Optional<ProductsEntity> getProductById(Long id) {
		Optional<ProductsEntity> findById = productRepository.findById(id);
		return findById;
	}
	
	public ProductsEntity createOrUpdateProduct(ProductsEntity product) {
		ProductsEntity productsEntity = productRepository.save(product);
		return productsEntity;	
	}
	
	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}
}
