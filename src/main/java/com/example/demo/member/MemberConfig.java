package com.example.demo.member;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.demo.book.BookRepository;
import com.example.demo.author.Author;
import com.example.demo.author.AuthorRepository;
import com.example.demo.book.Book;
import java.time.LocalDate;
import org.springframework.boot.CommandLineRunner;

@Configuration
public class MemberConfig {

    @Bean
    CommandLineRunner memberCommandLineRunner(MemberRepository memberRepository, BookRepository bookRepository, AuthorRepository authorRepository) {
        return args -> {

            Author author = Author.builder() 
                .authorName("J.R.R. Tolkien")
                .birthDate(LocalDate.of(1892, 1, 3))
                .build();
            
            Book book1 = Book.builder()
                .title("Book 1")
                .publicationDate(LocalDate.of(1955, 7, 29))
                .author(author)
                .genre("Fantasy")
                .price(19.99)
                .build();

            Member member1 = Member.builder()
                .name("Mia")
                .email("member_1@web.de")
                .build();

            authorRepository.save(author);    
            bookRepository.save(book1);
            memberRepository.save(member1);
            System.out.println("------>" + member1); 
        };
    }
}
