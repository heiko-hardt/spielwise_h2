package com.example.demo.book;
import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BookConfig {

    @Bean
    CommandLineRunner bookCommandLineRunner(BookRepository repository) {
        return args -> {
            Book book1 = new Book(
				"Book 1", 
				LocalDate.of(1955, 7, 29),		
				"J.R.R. Tolkien", 
				"Fantasy", 
				19.99
            );

            Book book2 = new Book(
				"Book 2", 
				LocalDate.of(1954, 7, 29),		
				"J.R.R. Tolkien", 
				"Fantasy", 
				19.99
            );
            repository.saveAll(List.of(book1, book2));
        };
    }
}
