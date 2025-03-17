package com.example.demo.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByName(String name);

    // favorite_books Tabelle

    @Query(value = "SELECT member_id, book_id FROM favorite_books", nativeQuery = true)
    List<Object[]> findAllFavoriteBooks();    
}
