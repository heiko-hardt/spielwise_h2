package com.example.demo.author;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AuthorConfig {

    @Bean
    CommandLineRunner authorCommandLineRunner(AuthorRepository repository) {
        return args -> {
            Author author1 = new Author(
				"Author 1", 
				LocalDate.of(1955, 7, 29),		
				"german"
            );
            repository.saveAll(List.of(author1));
        };
    }
}
