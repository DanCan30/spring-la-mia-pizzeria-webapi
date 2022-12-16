package org.generation.italy.main.api.controller;

import java.util.List;

import org.generation.italy.main.pojo.Pizza;
import org.generation.italy.main.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/1/pizza")
public class PizzaApiController {

	@Autowired
	PizzaService pS;
	
	@GetMapping("/all")
	public List<Pizza> getPizzas() {
		return pS.findAll();
	}
}
