package com.api.ecom.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.api.ecom.models.UploadFile;

public interface UploadRepository extends MongoRepository<UploadFile, String> {

	public UploadFile findImageById(String id, Class<?> clase);
}
