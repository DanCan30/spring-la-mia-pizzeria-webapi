package org.generation.italy.main.pojo;

import org.generation.italy.main.interfaces.PriceableInt;

import io.micrometer.common.lang.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "drink")
public class Drink implements PriceableInt {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotEmpty(message="Name field can't be empty.")
	@Column(name = "name", unique = true)
	private String name;
	
	@Nullable
	@Lob
	@Column(name = "description")
	private String description;
	
	@NotNull(message = "Price field can't be empty.")
	@Min(value= 1, message= "the price must be 1 or higher.")
	@Column(name = "price")
	private Integer price;
	
	public Drink() {}
	
	public Drink(String name, String description, int price) {
		setName(name);
		setDescription(description);
		setPrice(price);
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return this.id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDescription() {
		return this.description;
	}
	
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getPrice() {
		return this.price;
	}
}
