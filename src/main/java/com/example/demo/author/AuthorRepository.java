package com.example.demo.author;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long>{
    @Query("SELECT b FROM Author b WHERE b.authorName = ?1")
    Optional<Author> findAuthorByAuthorName(String authorName);
}
