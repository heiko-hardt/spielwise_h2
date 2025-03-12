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

            Author author = Author.builder() 
                .authorName("J.R.R. Tolkien")
                .birthDate(LocalDate.of(1892, 1, 3))
                .build();
            
            authorRepository.save(author);  // Erst wird Author gespeichert

            Book book1 = Book.builder()
                .title("Book 1")
                .publicationDate(LocalDate.of(1955, 7, 29))
                .author(author)
                .genre("Fantasy")
                .price(19.99)
                .build();

            Book book2 = Book.builder()
                .title("Book 2")
                .publicationDate(LocalDate.of(1954, 7, 29))
                .author(author)
                .genre("Fantasy")
                .price(19.99)
                .build();
           
            repository.saveAll(List.of(book1, book2)); // und dann die Bücher
        };
    }
}
