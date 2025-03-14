package com.example.demo.member;
import com.example.demo.book.Book;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashSet;
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
    @JsonIgnore // Verhindert JSON-Ausgabe der Many-to-Many-Beziehung
    @Builder.Default // Sorgt dafür, dass Lombok das Set initialisiert, weil wir oben @Builder verwenden und der überschreibt es ohne einen defaultWert, sondern mit null. 
    // Daher benötigen wir hier @Builder.Default, um das Set default Wert [] zu geben
    // Und wenn wir Bücher hinzufügen, dann wird das Set automatisch initialisiert udn kommt nicht zu einem Nullpointer
    private Set<Book> favoriteBooks = new HashSet<>();
}