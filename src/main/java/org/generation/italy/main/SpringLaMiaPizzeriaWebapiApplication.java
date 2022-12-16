package org.generation.italy.main;

import org.generation.italy.main.pojo.Book;
import org.generation.italy.main.service.BookService;
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
		
	}

}
