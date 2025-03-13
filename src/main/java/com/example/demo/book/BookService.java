package com.example.demo.book;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.example.demo.author.Author;
import com.example.demo.author.AuthorRepository;

import jakarta.transaction.Transactional;

@Service
public class BookService {

	private final BookRepository bookRepository;
	private final AuthorRepository authorRepository;

	public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
	}
	public List<Book> getBooks() {
		return bookRepository.findAll();
	}

	public void addNewBook(Book book) {
		Optional<Book> bookOptional = bookRepository.findByTitle(book.getTitle());
		if (bookOptional.isPresent()) {
			throw new IllegalStateException("Titel bereits vergeben");
		}
		bookRepository.save(book); // Buch in die Datenbank speichern
		//System.out.println("Speichere Buch" + book);	
	}

	public void deleteBook(Long id) {
		boolean exists = bookRepository.existsById(id);
		if (!exists) {
			throw new IllegalStateException("Buch mit ID " + id + " existiert nicht");
		}
		bookRepository.deleteById(id);
	}

	@Transactional // dank @Transactional brauchen wir hier keine @Query zu definieren
	public void updateBook( Long id, 
							String title, 
							Long authorId, 
							String genre, 
							Number price) {

		Book book = bookRepository.findById(id)
				.orElseThrow(() -> new IllegalStateException("Buch mit ID " + id + " existiert nicht"));

		
		if (authorId != null) {
			Author author = authorRepository.findById(authorId)
				.orElseThrow(() -> new IllegalStateException("Autor mit ID " + authorId + " existiert nicht"));
			book.setAuthor(author);
		}
		if (title != null && title.length() > 0 && !title.equals(book.getTitle())) {
			book.setTitle(title);
		}
		
		if (genre != null && genre.length() > 0 && !genre.equals(book.getGenre())) {
			book.setGenre(genre);
		}
		if (price != null && !price.equals(book.getPrice())) {
			book.setPrice(price);
		}
	}
}
