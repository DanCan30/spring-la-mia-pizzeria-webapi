package org.generation.italy.main.pojo;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "promotion")
public class Promotion {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Integer id;
	
	@NotEmpty(message = "Name field can't be empty.")
	@Size(min = 3, message = "The name's length must be at least 3 characters.")
	@Column(name = "name", unique = true)
	private String name;
	
	@NotNull(message = "The promotion must have a starting date")
	@Column(name = "starting_date")
	private LocalDate startingDate;
	
	@NotNull(message = "The promotion must have an ending date")
	@Column(name = "ending_date")
	private LocalDate endingDate;
	
	@OneToMany(mappedBy = "promotion", cascade = CascadeType.PERSIST)
	private List<Pizza> pizzas;
	
	public Promotion() {};
	public Promotion(String name, LocalDate startingDate, LocalDate endingDate) {
		setName(name);
		setStartingDate(startingDate);
		setEndingDate(endingDate);
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
	
	public void setStartingDate(LocalDate startingDate) {
	
//		LocalDate startingDateLD = LocalDate.parse(startingDate);
		this.startingDate = startingDate;
	}
	public LocalDate getStartingDate() {
		return this.startingDate;
	}	
	
	public void setEndingDate(LocalDate endingDate) {

//		LocalDate endingDateLD = LocalDate.parse(endingDate);

		this.endingDate = endingDate;
	}
	public LocalDate getEndingDate() {
		return this.endingDate;
	}
	
	public List<Pizza> getPizzas() {
		return pizzas;
	}
	public void setPizzas(List<Pizza> pizzas) {
		this.pizzas = pizzas;
	}
	
	public String getFormattedDate(LocalDate date) {

		String formattedDate = date.getDayOfMonth() + "/" + date.getMonthValue() + "/" +  date.getYear();
        return formattedDate;
	}
	
	@Override
	public String toString() {
		return getName() + ": " + getFormattedDate(getStartingDate()) + " - " + getFormattedDate(getEndingDate());
	}
	
}
