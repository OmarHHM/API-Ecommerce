package com.api.ecom.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
public class Product {
	
	@Id
	private String id;

    @NotBlank
    @Size(max = 100)
	private String name;
    
    @NotBlank
    @Size(max = 100)
    private String image;

    @NotBlank
    @Size(max = 100)
    private String brand;
	
    private Integer price;

    @NotBlank
    @Size(max = 100)
	private String category;

    private Integer countInStock;
	
    @NotBlank
    @Size(max = 100)
    private String description;
	
    @NotBlank
    @Size(max = 100)
    private String rating;
	
    private Integer numReviews;
	
    @DBRef
    private Reviews reviews;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Integer getCountInStock() {
		return countInStock;
	}
	public void setCountInStock(Integer countInStock) {
		this.countInStock = countInStock;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public Integer getNumReviews() {
		return numReviews;
	}
	public void setNumReviews(Integer numReviews) {
		this.numReviews = numReviews;
	}
	public Reviews getReviews() {
		return reviews;
	}
	public void setReviews(Reviews reviews) {
		this.reviews = reviews;
	}

}
