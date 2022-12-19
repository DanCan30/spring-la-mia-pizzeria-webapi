package org.generation.italy.main;

import java.time.LocalDate;

import org.generation.italy.main.pojo.Book;
import org.generation.italy.main.pojo.Ingredient;
import org.generation.italy.main.pojo.Pizza;
import org.generation.italy.main.pojo.Promotion;
import org.generation.italy.main.service.BookService;
import org.generation.italy.main.service.IngredientService;
import org.generation.italy.main.service.PizzaService;
import org.generation.italy.main.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringLaMiaPizzeriaWebapiApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringLaMiaPizzeriaWebapiApplication.class, args);
	}

	 @Autowired
	 BookService bS;
	@Autowired
	PizzaService pizzaService;
	@Autowired
	PromotionService promotionService;
	@Autowired
	IngredientService ingredientService;
	 
	@Override
	public void run(String... args) throws Exception {
		
		
		Book b1 = new Book("book1", "author1");
		Book b2 = new Book("book2", "author2");
		Book b3 = new Book("book3", "author3");
		Book b4 = new Book("book4", "author4");
		Book b5 = new Book("book5", "author5");
		Book b6 = new Book("book6", "author6");
		
		bS.save(b1);
		bS.save(b2);
		bS.save(b3);
		bS.save(b4);
		bS.save(b5);
		bS.save(b6);
		
		Promotion pr1 = new Promotion("Promozione1", LocalDate.of(2022, 10, 10), LocalDate.of(2022, 10, 12));
		Promotion pr2 = new Promotion("Promozione2", LocalDate.of(2022, 10, 30), LocalDate.of(2022, 11, 12));
		Promotion pr3 = new Promotion("Promozione3", LocalDate.of(2020, 11, 20), LocalDate.of(2021, 1, 20));
		
		promotionService.save(pr1);
		promotionService.save(pr2);
		promotionService.save(pr3);
		
		Ingredient tomato = new Ingredient("tomato");
		Ingredient mozzarella = new Ingredient("mozzarella");
		Ingredient ham = new Ingredient("ham");
		Ingredient pepperoni = new Ingredient("pepperoni");
		Ingredient olives = new Ingredient("olives");
		Ingredient wurstel = new Ingredient("wurstel");
		Ingredient fries = new Ingredient("fries");
		Ingredient mushrooms = new Ingredient("mushrooms");
		Ingredient cheese = new Ingredient("cheese");
		
		ingredientService.save(tomato);
		ingredientService.save(mozzarella);
		ingredientService.save(ham);
		ingredientService.save(pepperoni);
		ingredientService.save(olives);
		ingredientService.save(wurstel);
		ingredientService.save(fries);
		ingredientService.save(mushrooms);
		ingredientService.save(cheese);		
		
		Pizza p1 = new Pizza("Pizza1", "Margherita", 6, null, tomato, mozzarella);
		Pizza p2 = new Pizza("Pizza2", "Diavola", 8, pr2, tomato, mozzarella, pepperoni);
		Pizza p3 = new Pizza("Pizza3", "Rossa", 5, pr1, tomato);
		Pizza p4 = new Pizza("Pizza4", "Capricciosa", 10, null, tomato, ham, pepperoni, olives, mushrooms);

		pizzaService.save(p1);
		pizzaService.save(p2);
		pizzaService.save(p3);
		pizzaService.save(p4);	
		
	}

}
