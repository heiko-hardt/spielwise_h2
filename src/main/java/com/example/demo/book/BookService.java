package com.example.demo.book;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

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
}
