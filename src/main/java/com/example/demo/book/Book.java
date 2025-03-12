package com.example.demo.book;
import java.time.LocalDate;
import java.time.Period;
import com.example.demo.author.Author;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.lang.Nullable;
import lombok.Setter;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Table
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @SequenceGenerator(
            name = "book_sequence",
            sequenceName = "book_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "book_sequence"
    )
    private Long id;
    
    private String title;

    private LocalDate publicationDate;
    
    @Nullable
    private String genre;
    
    @Nullable
    private Number price;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id", nullable = false) // Fremdschlüssel in der Book-Tabelle
    @JsonIgnoreProperties({"authorName", "birthDate", "nationality", "books"}) // Ignoriert alles außer ID
    private Author author;

    // Speichert nicht in DB ab er per API abrufbar
    @Transient
    private LocalDate ageInYears;

    public Book(String title, LocalDate publicationDate, Author author, String genre, Number price) {
        this.title = title;
        this.publicationDate = publicationDate;
        this.author = author;
        this.genre = genre;
        this.price = price;
    }

    // "AgeInYears" wird über API ausgegeben aber nicht in DAtenbank gespeichert
    public int getAgeInYears() {
        return Period.between(this.publicationDate, LocalDate.now()).getYears();
    }   

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", publicationDate=" + publicationDate +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", price=" + price +
                '}';
    } 
}
