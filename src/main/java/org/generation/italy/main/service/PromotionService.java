package org.generation.italy.main.service;

import java.util.List;

import org.generation.italy.main.pojo.Promotion;
import org.generation.italy.main.repo.PromotionRepo;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class PromotionService {

	@Autowired
	private PromotionRepo promotionRepo;
	
	public List<Promotion> findAll() {
		return promotionRepo.findAll();
	}
	
	public Promotion findById(int id) {
		return promotionRepo.findById(id).get();
	}
	
	public void save(Promotion promotion) {
		promotionRepo.save(promotion);
	}
	
	public void delete(Promotion promotion) {
		promotionRepo.delete(promotion);
	}
	
	@Transactional
	public List<Promotion> findAllWithPizzas() {
		List<Promotion> promotions = promotionRepo.findAll();
		
		for (Promotion pr : promotions) {
			Hibernate.initialize(pr.getPizzas());
		}
		
		return promotions;
	}
}
