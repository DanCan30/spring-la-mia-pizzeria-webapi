package org.generation.italy.main.service;

import java.util.List;

import org.generation.italy.main.pojo.Book;
import org.generation.italy.main.repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

	@Autowired
	private BookRepo bookRepo;
	
	public void save(Book book) {
		bookRepo.save(book);
	}
	
	public void delete(Book book) {
		bookRepo.delete(book);
	}
	
	public List<Book> findAll() {
		return bookRepo.findAll();
	}
}
