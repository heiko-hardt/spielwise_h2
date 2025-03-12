package com.example.demo.author;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.example.demo.book.BookRepository;


@Service
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public AuthorService(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public List<Author> getAuthors() {
		return authorRepository.findAll();
	}

    public void addNewAuthor(Author author) {
		Optional<Author> bookOptional = authorRepository.findAuthorByAuthorName(author.getAuthorName());
		if (bookOptional.isPresent()) {
			throw new IllegalStateException("AuthorName bereits vergeben");
		}
		authorRepository.save(author); // Author in die Datenbank speichern
		//System.out.println("Speichere Author" + author);	
	}

    public void deleteAuthor(Long id) {
		boolean exists = authorRepository.existsById(id);
		if (!exists) {
			throw new IllegalStateException("Author mit ID " + id + " existiert nicht");
		}

        // Prüfen, ob noch Bücher mit diesem Autor existieren
        long bookCount = bookRepository.countBooksByAuthorId(id);

        if (bookCount > 0) {
            throw new IllegalStateException("Author mit ID " + id + " kann nicht gelöscht werden, weil er noch Bücher hat.");
        }
        
		authorRepository.deleteById(id);
	}




}
