package org.generation.italy.main.api.controller;

import java.util.List;
import java.util.Set;

import org.generation.italy.main.pojo.Ingredient;
import org.generation.italy.main.pojo.Pizza;
import org.generation.italy.main.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/1/pizza")
@CrossOrigin("*")
public class PizzaApiController {

	@Autowired
	PizzaService pS;
	
	@GetMapping("/all")
	public List<Pizza> getPizzas() {
		return pS.findAll();
	}
	
	@PostMapping("/edit/{id}")
	public Pizza updatePizza(@PathVariable("id") int id,
					@Valid @RequestBody Pizza pizza) {
		Pizza selectedPizza = pS.findPizzaById(id).get();
		Set<Ingredient> pizzaIngredients = selectedPizza.getIngredients();
		
		pizza.setIngredients(pizzaIngredients);
		pS.save(pizza);
		
		return pizza;
	}
	
	@PostMapping("/create")
	public Pizza createPizza(@Valid @RequestBody Pizza pizza) {
		
		Pizza newPizza = pizza; 
				
		pS.save(newPizza);
		
		return newPizza;
	}
	
	@PostMapping("/delete/{id}")
	public void deletePizza(@PathVariable("id") int id) {
		Pizza selectedPizza = pS.findPizzaById(id).get();
		pS.delete(selectedPizza);
		
	}
}
