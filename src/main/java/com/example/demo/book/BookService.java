package com.example.demo.book;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class BookService {

	private final BookRepository bookRepository;

	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	public List<Book> getBooks() {
		return bookRepository.findAll();
	}

	public void addNewBook(Book book) {
		Optional<Book> bookOptional = bookRepository.findBookByTitle(book.getTitle());
		if (bookOptional.isPresent()) {
			throw new IllegalStateException("Titel bereits vergeben");
		}
		bookRepository.save(book);
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
							String author, 
							String genre, 
							Number price) {
		Book book = bookRepository.findById(id)
				.orElseThrow(() -> new IllegalStateException("Buch mit ID " + id + " existiert nicht"));

		if (title != null && 
				title.length() > 0 && 
				!book.getTitle().equals(title)) {
			book.setTitle(title);
		}
		if (author != null && 
				author.length() > 0 && 
				!book.getAuthor().equals(author)) {
			book.setAuthor(author);
		}
		if (genre != null && 
				genre.length() > 0 && 
				!book.getGenre().equals(genre)) {
			book.setGenre(genre);
		}
		if (price != null && 
				!book.getPrice().equals(price)) {
			book.setPrice(price);
		}
	}
}
