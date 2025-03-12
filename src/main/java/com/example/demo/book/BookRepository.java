package com.example.demo.book;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

// Responsible for Data-Access-Layer
@Repository
public interface BookRepository extends JpaRepository<Book, Long>{
    
    @Query("SELECT b FROM Book b JOIN FETCH b.author")
    List<Book> findAllWithAuthors();

    @Query("SELECT b FROM Book b WHERE b.title = ?1")
    Optional<Book> findBookByTitle(String title);

    // GIbt Anuahl der Bücher zurück, die von einem bestimmten Autor geschrieben wurden
    @Query("SELECT COUNT(b) FROM Book b WHERE b.author.id = ?1")
    long countBooksByAuthorId(Long authorId);
}
