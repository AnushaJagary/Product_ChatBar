package com.ojas.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ProductsEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private String name;
private double price;
private String path;
public ProductsEntity() {
	super();
}

public ProductsEntity(Long id, String name, double price, String path) {
	super();
	this.id = id;
	this.name = name;
	this.price = price;
	this.path = path;
}

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public double getPrice() {
	return price;
}

public void setPrice(double price) {
	this.price = price;
}

public String getPath() {
	return path;
}

public void setPath(String path) {
	this.path = path;
}

@Override
public String toString() {
	return "ProductsEntity [id=" + id + ", name=" + name + ", price=" + price + ", path=" + path + "]";
}




}
