package com.example.demo.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
// Responsible for Data-Access-Layer
@Repository
public interface BookRepository 
        extends JpaRepository<Book, Long>{

}
