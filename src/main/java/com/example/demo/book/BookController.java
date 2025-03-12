package com.example.demo.book;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/book")
public class BookController {

    private final BookService bookService;

    // @Autowired -> auskommentiert, weil es unnötig ist
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    
    @GetMapping
	public List<Book> getBooks() {
        return bookService.getBooks();
    }

    @PostMapping
    public void registerNewBook(@RequestBody Book book) {
        bookService.addNewBook(book);
    }

    @DeleteMapping(path = "{id}")
    public void deleteBook(@PathVariable("id") Long id) {
        bookService.deleteBook(id);
    }

    @PutMapping
    public void updateBook(
        @RequestParam(required = false) Long id,
        @RequestParam(required = false) String title,
        @RequestParam(required = false) Long authorId,
        @RequestParam(required = false) String genre,
        @RequestParam(required = false) Number price) {
            bookService.updateBook(id, title, authorId, genre, price);
    }
    
}






