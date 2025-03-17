package com.example.demo.author;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AuthorConfig {

    @Bean
    CommandLineRunner authorCommandLineRunner(AuthorRepository repository) {
        return args -> {
            Author author = Author.builder() 
                .authorName("Author 1")
                .birthDate(LocalDate.of(1955, 7, 29))
                .nationality("english")
                .build();
         
            repository.save(author);
            // System.out.println("------>" + author);
        };
    }
}
