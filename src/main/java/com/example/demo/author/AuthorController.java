package com.example.demo.author;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping(path = "api/v1/author")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
	public List<Author> getAuthors() {
        return authorService.getAuthors();
    }

    @PostMapping
    public void registerNewAuthor(@RequestBody Author author) {
        authorService.addNewAuthor(author);
    }

    @DeleteMapping(path = "{id}")
    public void deleteAuthor(
        @PathVariable("id") Long id) {
            authorService.deleteAuthor(id);
    }

    @PutMapping(path = "{id}")
    public void updateAuthor(
        @PathVariable(required = true) Long id,
        @RequestParam(required = false) String authorName,
        @RequestParam(required = false) LocalDate birthDate,
        @RequestParam(required=false) String nationality
        ) {
            authorService.updateAuthor(id, authorName, birthDate, nationality);
    }

}
