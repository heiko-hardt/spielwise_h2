package com.example.demo.member;
import com.example.demo.book.Book;
import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.Set;
import lombok.*;

import jakarta.persistence.*;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "favoriteBooks") // Verhindert rekursive Endlosschleife
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    @ManyToMany
    @JoinTable(
        name = "favorite_books", // Name der Join-Tabelle
        joinColumns = @JoinColumn(name = "member_id"), // Fremdschlüssel für Member
        inverseJoinColumns = @JoinColumn(name = "book_id") // Fremdschlüssel für Book
    )
    @JsonBackReference // verhindert eine rekursive Endlosschleife, wenn die Beziehung in JSON ausgegeben wird
    private Set<Book> favoriteBooks;
}