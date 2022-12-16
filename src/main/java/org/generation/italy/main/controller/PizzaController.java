package org.generation.italy.main.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.generation.italy.main.pojo.Ingredient;
import org.generation.italy.main.pojo.Pizza;
import org.generation.italy.main.pojo.Promotion;
import org.generation.italy.main.service.IngredientService;
import org.generation.italy.main.service.PizzaService;
import org.generation.italy.main.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/")
public class PizzaController {
	
	@Autowired
	private PizzaService pizzaService;
	@Autowired
	private PromotionService promotionService;
	@Autowired
	private IngredientService ingredientService;

	@GetMapping("/")
	public String getIndex(Model model) {
		
		List<Pizza> pizzas = pizzaService.findAll();
		
		model.addAttribute("pizzas", pizzas);
		
		return "pizza/index";
	}
	
	@GetMapping("/pizza/{id}")
	public String showPizza(@PathVariable("id") int id,  Model model) {
				
		Optional<Pizza> optPizza = pizzaService.findPizzaById(id);
		
		if (optPizza.isEmpty()) {
			return "404";
		}
		
		Pizza pizza = optPizza.get();
		Promotion promotion = null;
		
		if (pizza.getPromotion() != null) {
			promotion = pizza.getPromotion();
		}
		
		Set<Ingredient> ingredients = pizza.getIngredients();
		
		model.addAttribute("pizza", pizza);
		model.addAttribute("ingredients", ingredients);
		model.addAttribute("promotion", promotion);
		return "pizza/pizza";
		
	}
	
	@GetMapping("/pizza/create")
	public String addPizza(Model model) {
		
		Pizza pizza = new Pizza();
		List<Promotion> promotions = promotionService.findAll();
		List<Ingredient> ingredients = ingredientService.findAll();
		
		model.addAttribute("pizza", pizza);
		model.addAttribute("promotions", promotions);
		model.addAttribute("ingredients", ingredients);
		
		return "pizza/create";
	}
	
	@PostMapping("/pizza/create")
	public String savePizza(@Valid @ModelAttribute("pizza") Pizza pizza,
							BindingResult bindingResult, RedirectAttributes redirectAttributes  ) {
		
		if(bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			return "redirect:/pizza/create";
		}
		
		pizzaService.save(pizza);	
		
		return "redirect:/";
	}
	
	@GetMapping("/pizza/edit/{id}")
	public String editPizza(@PathVariable("id") int id, Model model) {
		
		Optional<Pizza> optPizza = pizzaService.findPizzaById(id);
		List<Promotion> promotions = promotionService.findAll();
		List<Ingredient> ingredients = ingredientService.findAll();
		if (optPizza.isEmpty()) {
			return "404";
		}
		
		Pizza pizza = optPizza.get();
		
		model.addAttribute("pizza", pizza);
		model.addAttribute("promotions", promotions);
		model.addAttribute("ingredients", ingredients);
		
		return "pizza/edit";
	}
	
	@PostMapping("/pizza/edit/{id}")
	public String updatePizza(@Valid @ModelAttribute("pizza") Pizza pizza,
								BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		
		if(bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			return "redirect:/pizza/edit/" + pizza.getId();
		}
		
		pizzaService.save(pizza);

		return "redirect:/";
	}
	
	@GetMapping("/pizza/delete/{id}")
	public String deletePizza(@PathVariable("id") Pizza pizza) {
		
		pizzaService.delete(pizza);
		return "redirect:/";
	}
	
	@GetMapping("/pizza/search")
	public String searchPizzaByName(@RequestParam(name = "query", required = false) String query, Model model) {
		
		List<Pizza> pizzas = query == null
							? pizzaService.findAll()
							: pizzaService.findByName(query);
		
		
		
		model.addAttribute("pizzas", pizzas);
		model.addAttribute("query", query);
		
		return "pizza/search";
	}
	
}
