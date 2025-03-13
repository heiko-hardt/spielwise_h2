package com.example.demo.book;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

// Responsible for Data-Access-Layer
@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

    Optional<Book> findByTitle(String title);

    // Gibt Anzahl der Bücher zurück, die von einem bestimmten Autor geschrieben wurden
    @Query("SELECT COUNT(b) FROM Book b WHERE b.author.id = ?1")
    long countBooksByAuthorId(Long authorId);

    // Löscht alle Bücher eines bestimten Autors
    @Modifying // Modifying benutzt man bei DELETE, UPDATE, INSERT weil Dtaen werden verändert (bei SELECT brauchts man @Modifying nicht)
    @Transactional // sogt dafür, dass DELETE als eine Einheit entweder komplett durchgeführt wird oder gar nicht
    @Query("DELETE FROM Book b WHERE b.author.id = :authorId")
    void deleteByAuthorId(@Param("authorId") Long authorId);
}
