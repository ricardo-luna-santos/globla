package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name="c_product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  
	public int id_product;
	//Documeto de Visual
	@Column(name="desc_product")
	public String description;
	@Column(name="quantity")
	public int quantity;
	@Column(name="price")
	public double price;
	
	public int getId_product() {
		return id_product;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Product(String description, int quantity, double price) {
		this.description = description;
		this.quantity = quantity;
		this.price = price;
	}
	public Product() {
	}
	
}
