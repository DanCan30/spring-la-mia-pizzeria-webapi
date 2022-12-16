package org.generation.italy.main.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String title;
	
	@Column
	private String author;
	
	public Book() {}
	
	public Book(String title, String author) {
		setTitle(title);
		setAuthor(author);
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getId() {
		return this.id;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTitle() {
		return this.title; 
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getAuthor() {
		return this.author;
	}
}
