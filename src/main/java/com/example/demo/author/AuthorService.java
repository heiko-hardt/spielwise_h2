package com.example.demo.author;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;


@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
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




}
