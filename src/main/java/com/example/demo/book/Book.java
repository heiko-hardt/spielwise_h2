package com.example.demo.book;
import java.time.LocalDate;
import java.time.Period;
import com.example.demo.author.Author;

import io.micrometer.common.lang.Nullable;
import lombok.Setter;
import lombok.Getter;

import jakarta.persistence.*;

@Entity
@Table
@Setter
@Getter
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
    
    @Nullable // anschauen
    private String title;

    private LocalDate publicationDate;
    private String genre;
    private Number price;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id", nullable = false) // Fremdschlüssel in der Book-Tabelle
    private Author author;

    // Speichert nicht in DB ab er per API abrufbar
    @Transient
    private LocalDate ageInYears;

    public Book() {}

    public Book(Long id, String title, LocalDate publicationDate, Author author, String genre, Number price) {
        this.id = id;
        this.title = title;
        this.publicationDate = publicationDate;
        this.author = author;
        this.genre = genre;
        this.price = price;
    }

    public Book(String title, LocalDate publicationDate, Author author, String genre, Number price) {
        this.title = title;
        this.publicationDate = publicationDate;
        this.author = author;
        this.genre = genre;
        this.price = price;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
    
    public Number getPrice() {
        return price;
    }

    public void setPrice(Number price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setAgeInYears(LocalDate ageInYears) {
        this.ageInYears = ageInYears;
    }   

    // "AgeInYears" wird über API ausgegeben aber nicht in DAtenbank gespeichert
    public int getAgeInYears() {
        return Period.between(this.publicationDate, LocalDate.now()).getYears();
    }   

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
