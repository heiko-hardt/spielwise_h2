package com.example.demo.book;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Aktiviert Mockito in JUnit 5
public class BookControllerTest {

    private MockMvc mockMvc; // Manuelles MockMvc statt @WebMvcTest

    @Mock
    private BookService bookService; // Ersetzt @MockBean mit @Mock

    @InjectMocks
    private BookController bookController; // Fügt den Mock Service in den Controller ein

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build(); // Manuelles Setup von MockMvc
    }

    // TEST 1: GET /api/v1/book (Bücher abrufen)
    @Test
    void testGetBooks() throws Exception {
        when(bookService.getBooks()).thenReturn(Collections.emptyList()); // Simuliert eine leere Liste

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/book"))
                .andExpect(MockMvcResultMatchers.status().isOk()) // Erwartet HTTP 200 OK
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(0)); // Erwartet eine leere Liste
    }

    // TEST 2: POST /api/v1/book (Neues Buch speichern)
    @Test
    void testRegisterNewBook() throws Exception {
        String newBookJson = """
                {
                    "id": 1,
                    "title": "Spring Boot für Anfänger",
                    "authorId": 100,
                    "genre": "IT",
                    "price": 29.99
                }
                """;

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newBookJson))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(bookService, times(1)).addNewBook(any(Book.class)); // Prüft, ob Service aufgerufen wurde
    }

    // TEST 3: DELETE /api/v1/book/{id} (Buch löschen)
    @Test
    void testDeleteBook() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/book/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(bookService, times(1)).deleteBook(1L); // Prüft, ob deleteBook aufgerufen wurde
    }
}
