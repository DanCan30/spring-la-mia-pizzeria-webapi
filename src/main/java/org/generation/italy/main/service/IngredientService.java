package org.generation.italy.main.service;

import java.util.List;

import org.generation.italy.main.pojo.Ingredient;
import org.generation.italy.main.pojo.Pizza;
import org.generation.italy.main.repo.IngredientRepo;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class IngredientService {

	@Autowired
	private IngredientRepo ingredientRepo;
	
	public List<Ingredient> findAll() {
		return ingredientRepo.findAll();
	}
	
	public void save(Ingredient ingredient) {
		ingredientRepo.save(ingredient);
	}
	
	public void delete(Ingredient ingredient) {
		ingredientRepo.delete(ingredient);
	}
	
	public Ingredient findById(Integer id) {
		return ingredientRepo.findById(id).get();
	}
	
	@Transactional
	public List<Pizza> findWithPizzas(Ingredient ingredient) {
		
		Ingredient i = ingredientRepo.findById(ingredient.getId()).get();
		List<Pizza> pizzas = i.getPizzas();
		
		for (Pizza p : pizzas) {
			Hibernate.initialize(p);
		}
		
		return pizzas;
	}
}
