package org.generation.italy.main.api.controller;

import java.util.List;

import org.generation.italy.main.pojo.Book;
import org.generation.italy.main.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/1")
public class BookApiController {

	@Autowired
	BookService bS;
	
	@GetMapping("/all")
	public List<Book> getBooks() {
		return bS.findAll();
	}
	
}
