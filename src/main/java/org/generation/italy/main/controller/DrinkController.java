package org.generation.italy.main.controller;

import java.util.List;
import java.util.Optional;

import org.generation.italy.main.pojo.Drink;
import org.generation.italy.main.service.DrinkService;
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
@RequestMapping("/drink")
public class DrinkController {

	@Autowired
	private DrinkService drinkService;
	
	@GetMapping
	public String getDrinkIndex(Model model) {
		List<Drink> drinks = drinkService.findAll();
		
		model.addAttribute("drinks", drinks);
		
		return "drink/index";
	}
	
	@GetMapping("/{id}")
	public String showDrink(@PathVariable("id") int id, Model model) {
		Optional<Drink> optDrink = drinkService.findById(id);
		
		if(optDrink.isEmpty()) {
			return "404";
		}
		
		Drink drink = optDrink.get();
		
		model.addAttribute("drink", drink);
		
		return "/drink/drink";
	}
	
	@GetMapping("/create")
	public String addDrink(Model model) {
		Drink drink = new Drink();
		
		model.addAttribute("drink", drink);
		
		return "drink/create";
	}
	
	@PostMapping("/create")
	public String saveDrink(@Valid @ModelAttribute("drink") Drink drink,
							BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		
		if(bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			return "redirect:/drink/create";
		}
		
		drinkService.save(drink);
		
		return "redirect:/drink";
	}
	
	@GetMapping("/edit/{id}")
	public String editDrink(@PathVariable("id") int id, Model model) {
		Optional<Drink> optDrink = drinkService.findById(id);
		
		if(optDrink.isEmpty()) {
			return "404";
		}
		
		Drink drink = optDrink.get();
		
		model.addAttribute("drink", drink);
		
		return "drink/edit";
	}
	
	@PostMapping("/edit/{id}")
	public String updateDrink(@Valid @ModelAttribute("drink") Drink drink,
								BindingResult bindingResult, RedirectAttributes redirectAttributes){
		
		if(bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			return "redirect:/drink/edit/" + drink.getId();
		}
		
		drinkService.save(drink);
		return "redirect:/drink";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteDrink(@ModelAttribute("drink") Drink drink) {
		drinkService.delete(drink);
		
		return "redirect:/drink";
	}
	
	@GetMapping("/search")
	public String searchDrinkByName(@RequestParam(name = "query", required = false) String query, Model model) {
		
		List<Drink> drinks = query == null
							? drinkService.findAll()
							: drinkService.findByName(query);
		
		
		
		model.addAttribute("drinks", drinks);
		model.addAttribute("query", query);
		
		return "drink/search";
	}
}
