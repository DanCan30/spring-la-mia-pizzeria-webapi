package org.generation.italy.main.service;

import java.util.List;
import java.util.Optional;

import org.generation.italy.main.pojo.Drink;
import org.generation.italy.main.repo.DrinkRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DrinkService {

	@Autowired
	private DrinkRepo drinkRepo;
	
	public Optional<Drink> findById(int id) {
		return drinkRepo.findById(id);
	}
	
	public List<Drink> findAll() {
		return drinkRepo.findAll();
	}
	
	public void save(Drink drink) {
		drinkRepo.save(drink);
	}
	
	public void delete(Drink drink) {
		drinkRepo.delete(drink);
	}
	
	public List<Drink> findByName(String name) {
		return drinkRepo.findByNameContainingIgnoreCase(name);
	}
}
