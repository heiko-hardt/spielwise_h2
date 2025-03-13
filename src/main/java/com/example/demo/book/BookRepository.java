package com.example.demo.book;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

// Responsible for Data-Access-Layer
@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

    Optional<Book> findByTitle(String title);

    // Gibt Anzahl der Bücher zurück, die von einem bestimmten Autor geschrieben wurden
    @Query("SELECT COUNT(b) FROM Book b WHERE b.author.id = ?1")
    long countBooksByAuthorId(Long authorId);
}
