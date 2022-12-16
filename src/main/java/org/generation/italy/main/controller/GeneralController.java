package org.generation.italy.main.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.generation.italy.main.interfaces.PriceableInt;
import org.generation.italy.main.pojo.Drink;
import org.generation.italy.main.pojo.Pizza;
import org.generation.italy.main.service.DrinkService;
import org.generation.italy.main.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/general")
public class GeneralController {

	public static class PriceComparator implements Comparator<PriceableInt> {

		@Override
		public int compare(PriceableInt o1, PriceableInt o2) {

			if(o1.getPrice() > o2.getPrice())
				return 1;
			else if(o1.getPrice() < o2.getPrice())
				return -1;
			return 0;
		}
	}
	
	
	@Autowired
	private PizzaService pizzaService;
	@Autowired
	private DrinkService drinkService;
	
	
	@GetMapping("/search")
	public String mixedSearchByName(@RequestParam(name = "query", required = false) String query, Model model) {
		
		List<Pizza> pizzas = query == null ? pizzaService.findAll() : pizzaService.findByName(query);
		List<Drink> drinks = query == null ? drinkService.findAll() : drinkService.findByName(query);
				
		model.addAttribute("pizzas", pizzas);
		model.addAttribute("drinks", drinks);
		model.addAttribute("query", query);
		
		return "general-search";
	}
	
	@GetMapping("/order-by-price")
	public String orderByPrice(Model model) {
		
		List<PriceableInt> products = new ArrayList<>();
		
		List<Pizza> pizzas = pizzaService.findAll();
		List<Drink> drinks = drinkService.findAll();
		for(Pizza p : pizzas) {
			products.add(p);
		}
		
		for(Drink d : drinks) {
			products.add(d);
		}
		
		products.sort(new PriceComparator());
		
		model.addAttribute("products", products);
		
		return "ordered-list";
	}
	
}
