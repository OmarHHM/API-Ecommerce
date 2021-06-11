package com.api.ecom.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.api.ecom.models.Product;

public interface ProductRepository extends MongoRepository<Product, String> {
	
}
