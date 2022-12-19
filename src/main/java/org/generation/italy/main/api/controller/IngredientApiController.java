package org.generation.italy.main.api.controller;

import java.util.Set;

import org.generation.italy.main.pojo.Ingredient;
import org.generation.italy.main.pojo.Pizza;
import org.generation.italy.main.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/1/ingredient")
@CrossOrigin("*")
public class IngredientApiController {
	
	@Autowired
	PizzaService pS;
	
	@GetMapping("/pizza/{id}")
	public Set<Ingredient> getIngredientsForPizza(@PathVariable("id") int id) {
		
		Pizza selectedPizza = pS.findPizzaById(id).get();
		
		return selectedPizza.getIngredients();
	}

}
