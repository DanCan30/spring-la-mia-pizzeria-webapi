package org.generation.italy.main.pojo;

import java.util.List;

import jakarta.annotation.Nullable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "ingredient")
public class Ingredient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	@NotNull(message = "The name is necessary")
	@NotBlank(message = "The name is necessary")
	private String name;
	
	@Nullable
	@ManyToMany(mappedBy = "ingredients", cascade = CascadeType.DETACH)
	private List<Pizza> pizzas;
	
	public Ingredient() {};
	public Ingredient(String name) {
		setName(name);
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getId() {
		return this.id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
	
	public void setPizzas(List<Pizza> pizzas) {
		this.pizzas = pizzas;
	}
	public List<Pizza> getPizzas() {
		return this.pizzas;
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
	@Override
	public int hashCode() {
		if(this.id != null)
			return getId();
		
		return 0;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (!(obj instanceof Ingredient)) return false;
		return obj.hashCode() == hashCode();
	}
}
