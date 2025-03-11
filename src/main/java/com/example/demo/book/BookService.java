package com.example.demo.book;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class BookService {
    public List<Book> getBooks() {
		return List.of(
			new Book(
				1L, 
				"The Lord of the Rings", 
				LocalDate.of(1954, 7, 29),		
				"J.R.R. Tolkien", 
				"Fantasy", 
				19.99
			)
		);
	}
}
