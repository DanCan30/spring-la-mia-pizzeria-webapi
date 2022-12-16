package org.generation.italy.main.controller;

import java.util.List;
import java.util.Set;

import org.generation.italy.main.pojo.Ingredient;
import org.generation.italy.main.pojo.Pizza;
import org.generation.italy.main.service.IngredientService;
import org.generation.italy.main.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/ingredient")
public class IngredientController {
	
	@Autowired
	private IngredientService ingredientService;
	@Autowired
	private PizzaService pizzaService;
	
	@GetMapping
	public String getIngredients(Model model) {
		
		List<Ingredient> ingredients = ingredientService.findAll();
		model.addAttribute("ingredients", ingredients);
		
		return "ingredient/index";
	}
	
	@GetMapping("/{id}")
	public String showIngredient(@PathVariable("id") int id, Model model) {
		
		Ingredient ingredient = ingredientService.findById(id);
		model.addAttribute("ingredient", ingredient);
		
		List<Pizza> pizzas = ingredient.getPizzas();
		model.addAttribute("pizzas", pizzas);
		
		return "ingredient/ingredient";
	}
	
	@GetMapping("/create")
	public String createIngredient(Model model) {
		
		Ingredient ingredient = new Ingredient();
		model.addAttribute("ingredient", ingredient);
		
		List<Pizza> pizzas = pizzaService.findAll();
		model.addAttribute("pizzas", pizzas);
		
		return "ingredient/create";
	}
	
	@PostMapping("/create")
	public String storeIngredient(@Valid Ingredient ingredient,
								BindingResult bindingResult, RedirectAttributes redirectAttributes ) {
		
		if(bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			return "redirect:/ingredient/create";
		}
		
		List<Pizza> pizzas = ingredient.getPizzas();
		
		for(Pizza p : pizzas) {
			Set<Ingredient> ingredients = p.getIngredients();
			ingredients.add(ingredient);
		}
		
		ingredientService.save(ingredient);
		
		return "redirect:/ingredient";
	}
	
	@GetMapping("/edit/{id}")
	public String editIngredient(@PathVariable("id") int id, Model model) {
		
		Ingredient ingredient = ingredientService.findById(id);
		model.addAttribute("ingredient", ingredient);
		
		List<Pizza> pizzas = pizzaService.findAll();
		model.addAttribute("pizzas", pizzas);
		return "ingredient/edit";
	}
	
	@PostMapping("/edit/{id}")
	public String updateIngredient(@Valid Ingredient ingredient,
			BindingResult bindingResult, RedirectAttributes redirectAttributes ) {
		
		if(bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			return "redirect:/ingredient/edit/" + ingredient.getId();
		}

		List<Pizza> ingredientPizzas = ingredient.getPizzas();
		Ingredient oldDBIngredient = ingredientService.findById(ingredient.getId());
		
		for(Pizza p : oldDBIngredient.getPizzas()) {
			p.getIngredients().remove(ingredient);
		}
		
		for(Pizza p : ingredientPizzas) {
			p.getIngredients().add(ingredient);
		}
		
		ingredientService.save(ingredient);
				
		return "redirect:/ingredient";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteIngredient(@PathVariable("id") int id, Ingredient ingredient) {
		
		List<Pizza> ingredientPizzas = ingredientService.findWithPizzas(ingredient);
		
//		if(ingredientPizzas != null ) {
			
			for(Pizza p : ingredientPizzas) {
				p.getIngredients().remove(ingredient);
			}
//		}
		
		ingredientService.delete(ingredient);
		return "redirect:/ingredient";
	}
	

}
