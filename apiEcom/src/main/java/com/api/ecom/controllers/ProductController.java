package com.api.ecom.controllers;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.api.ecom.models.Product;
import com.api.ecom.models.UploadFile;
import com.api.ecom.payload.response.MessageResponse;
import com.api.ecom.payload.response.UploadFileResponse;
import com.api.ecom.repository.ProductRepository;
import com.api.ecom.repository.UploadRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	UploadRepository uploadRepository;

	@GetMapping("/")
	public ResponseEntity<?> getProducts() {
		List<Product> listProducts = productRepository.findAll();
		if (listProducts.isEmpty()) {
			return new ResponseEntity<>(new MessageResponse("No existen productos."), HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(listProducts, HttpStatus.OK);
		}
	}

	@PostMapping("/createProduct")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> createProduct(@Valid @RequestBody Product product) {
		System.out.println(product.getName() + product.getDescription());
		try {

			productRepository.save(product);
			return new ResponseEntity<>(new MessageResponse("Producto creado exitosamente."), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(new MessageResponse("El producto no se pudo crear."), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/file/uploadImage")
	@PreAuthorize("hasRole('ADMIN')")
	@ResponseBody
	public ResponseEntity<?> uploadImage(@RequestParam(value = "image") MultipartFile file) {
		if (file.isEmpty())
			return new ResponseEntity<>(new MessageResponse("Elija una imagen."), HttpStatus.BAD_REQUEST);

		String fileName = file.getOriginalFilename();
		try {

			UploadFile uploadFile = new UploadFile();
			uploadFile.setName(fileName);
			uploadFile.setCreatedTime(new Date());
			uploadFile.setContent(new Binary(file.getBytes()));
			uploadFile.setContentType(file.getContentType());
			uploadFile.setSize(file.getSize());

			UploadFileResponse uploadReponse = new UploadFileResponse();
			uploadReponse.setUploadFile(uploadRepository.save(uploadFile));
			uploadReponse.setMessage(new MessageResponse("Imagen agregada exitosamente."));
			return new ResponseEntity<>(uploadReponse, HttpStatus.CREATED);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<>(new MessageResponse("Error al subir imagen"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/file/image/{id}", produces = { MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE })
	@PreAuthorize("hasRole('ADMIN')")
	@ResponseBody
	public byte[] image(@PathVariable String id) {
		byte[] data = null;
		UploadFile file = uploadRepository.findImageById(id, UploadFile.class);
		if (file != null) {
			data = file.getContent().getData();
		}
		return data;
	}
}
