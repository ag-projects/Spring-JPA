package com.agharibi.domain;

import java.math.BigDecimal;

public class Product {
	private Integer id;
	private String description;
	private BigDecimal price;
	private String imageUrl;

	public String getDescription() {
		return description;
	}

	public Integer getId() {
		return id;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
