package com.example.demo.book;

import java.time.LocalDate;
import java.time.Period;
import java.util.Set;

import com.example.demo.author.Author;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.example.demo.member.Member;
import jakarta.validation.constraints.NotBlank;

import org.springframework.lang.Nullable;
import lombok.*;

import jakarta.persistence.*;

@Entity
@Getter
@NoArgsConstructor // notwendig für Hibernate
@AllArgsConstructor // Notwendig für @Builder (@Builder nutzt den AllArgsConstructor in Hintergrund)
@Builder // Erlaubt flexibles Erstellen von Objekten
@ToString(exclude = "favoritedBy") // favoritedBy ist ausgeschlossen -> verhindert rekursive Ausgabe
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
    
    @Setter
    @NotBlank
    private String title;
    
    private LocalDate publicationDate;
    
    @Setter
    @Nullable
    private String genre;
    
    @Setter
    @Nullable
    private Number price;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id", nullable = true) // Fremdschlüssel in der Book-Tabelle
    @JsonIgnoreProperties({"authorName", "birthDate", "nationality", "books"}) // Ignoriert alles außer ID
    @Setter
    private Author author;


    @ManyToMany(mappedBy = "favoriteBooks") // Verknüpfung mit Member-Tabelle
    @JsonIgnore // Verhindert JSON-Ausgabe der Many-to-Many-Beziehung
    private Set<Member> favoritedBy;

    // Speichert nicht in DB ab er per API abrufbar
    @Transient
    private LocalDate ageInYears;

    // "AgeInYears" wird über API ausgegeben aber nicht in DAtenbank gespeichert
    public int getAgeInYears() {
        return Period.between(this.publicationDate, LocalDate.now()).getYears();
    }   
}
