package org.generation.italy.main.controller;

import java.util.List;

import org.generation.italy.main.pojo.Pizza;
import org.generation.italy.main.pojo.Promotion;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/promotion")
public class PromotionController {

	@Autowired
	private PromotionService promotionService;
	@Autowired
	private PizzaService pizzaService;
	
	@GetMapping
	public String getPromotions(Model model) {
		
		List<Promotion> promotions = promotionService.findAll();
		model.addAttribute("promotions", promotions);
		
		return "promotion/index";
	}
	
	@GetMapping("/{id}")
	public String findPromotion(@PathVariable("id") int id, Model model) {
		
		Promotion promotion = promotionService.findById(id);
		List<Pizza> promotedPizzas = promotion.getPizzas();
		
		model.addAttribute("promotion", promotion);
		model.addAttribute("pizzas", promotedPizzas);
		
		return "promotion/promotion";
	}
	
	@GetMapping("/create")
	public String createPromotion(Model model) {
		
		Promotion promotion = new Promotion();
		
		List <Pizza> pizzas = pizzaService.findAll();
		
		model.addAttribute("promotion", promotion);
		model.addAttribute("pizzas", pizzas);
		
		return "promotion/create";
	}
	
	@PostMapping("/create")
	public String storePromotion(@Valid @ModelAttribute("promotion") Promotion promotion,
								BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		
		if(bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			return "redirect:/promotion/create";
		}
		
		List<Pizza> promotedPizzas = promotion.getPizzas();
		
		for(Pizza p : promotedPizzas) {
			p.setPromotion(promotion);
		}
		
		promotionService.save(promotion);
		
		return "redirect:/promotion";
	}
	
	@GetMapping("/edit/{id}")
	public String editPromotion(@PathVariable("id") int id, Model model) {
		
		Promotion promotion = promotionService.findById(id);
		List<Pizza> pizzas = pizzaService.findAll();
		
		model.addAttribute("promotion", promotion);
		model.addAttribute("pizzas", pizzas);
		
		return "promotion/edit";
	}
	
	@PostMapping("/edit/{id}")
	public String updatePromotion(@Valid @ModelAttribute("promotion") Promotion promotion, 
			BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		
		if(bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			return "redirect:/promotion/edit/" + promotion.getId();
		}
		
		List<Pizza> promotedPizzas = promotion.getPizzas();
		
		for(Pizza p : promotedPizzas) {
			p.setPromotion(promotion);
		}
		
		promotionService.save(promotion);
		return "redirect:/promotion";
	}
	
	@GetMapping("/delete/{id}")
	public String deletePromotion(@PathVariable("id") int id) {
		
		Promotion promotion = promotionService.findById(id);
		
		List<Pizza> promotedPizzas = promotion.getPizzas();

			for (Pizza p : promotedPizzas) {
				p.setPromotion(null);
			}
		
		
		promotionService.delete(promotion);
		
		return "redirect:/promotion";
	}
}
