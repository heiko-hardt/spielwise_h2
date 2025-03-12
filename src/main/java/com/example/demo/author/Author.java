package com.example.demo.author;

import jakarta.persistence.*;
import java.time.LocalDate;


@Entity
@Table
public class Author {
    @Id
    @SequenceGenerator(
            name = "author_sequence",
            sequenceName = "author_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "author_sequence"
    )

    private Long id;
    private String authorName;
    private LocalDate birthDate;
    private String nationality;
    
    public Author() {
    
    }

    public Author(Long id, String authorName, LocalDate birthDate, String nationality) {
        this.id = id;
        this.authorName = authorName;
        this.birthDate = birthDate;
        this.nationality = nationality;
    }

    public Author(String authorName, LocalDate birthDate, String nationality) {
        this.authorName = authorName;
        this.birthDate = birthDate;
        this.nationality = nationality;
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setName(String authorName) {
        this.authorName = authorName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String toString() {
        return "Author{" +
                "id=" + id +
                ", authorName='" + authorName + '\'' +
                ", birthDate=" + birthDate +
                ", nationality='" + nationality + '\'' +
                '}';
    }
}
