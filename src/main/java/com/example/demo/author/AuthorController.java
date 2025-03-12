package com.example.demo.author;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.book.Book;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public void registerNewBook(@RequestBody Author author) {
        authorService.addNewAuthor(author);
    }

}
