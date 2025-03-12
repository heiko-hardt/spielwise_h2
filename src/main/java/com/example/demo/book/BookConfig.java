package com.example.demo.book;
import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.author.Author;
import com.example.demo.author.AuthorRepository;

@Configuration
public class BookConfig {

    @Bean
    CommandLineRunner bookCommandLineRunner(BookRepository repository, AuthorRepository authorRepository) {
        return args -> {
            Author author = new Author(
                "J.R.R. Tolkien",
                LocalDate.of(1892, 1, 3),
                "British"
            );

            authorRepository.save(author);  // Erst speichere den Autor

            Book book1 = new Book(
				"Book 1", 
				LocalDate.of(1955, 7, 29),		
				author, 
				"Fantasy", 
				19.99
            );

            Book book2 = new Book(
				"Book 2", 
				LocalDate.of(1954, 7, 29),		
				author, 
				"Fantasy", 
				19.99
            );
            repository.saveAll(List.of(book1, book2));
        };
    }
}
