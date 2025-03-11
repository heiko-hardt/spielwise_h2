package com.example.demo.book;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
// Responsible for Data-Access-Layer
@Repository
public interface BookRepository extends JpaRepository<Book, Long>{
    @Query("SELECT b FROM Book b WHERE b.title = ?1")
    Optional<Book> findBookByTitle(String title);
}
