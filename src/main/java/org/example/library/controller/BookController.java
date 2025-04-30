package org.example.library.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.library.dto.BookRequestDto;
import org.example.library.dto.BookResponseDto;
import org.example.library.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Book Controller", description = "API for working with 'book' information")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/books")
@Validated
public class BookController {

    private final BookService bookService;

    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Save Book (only ADMIN)")
    public ResponseEntity<BookResponseDto> saveBook(@RequestBody @Valid BookRequestDto bookRequestDto) {
        return new ResponseEntity<>(bookService.save(bookRequestDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete Book by id (only ADMIN)")
    public ResponseEntity<Void> deleteBook(@PathVariable(name = "id") Long id) {
        bookService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Update Book by id (only ADMIN)")
    public ResponseEntity<BookResponseDto> updateBook(@PathVariable(name = "id") Long id,
                                                      @RequestBody @Valid BookRequestDto bookRequestDto) {
        return new ResponseEntity<>(bookService.update(id, bookRequestDto), HttpStatus.OK);
    }

    @GetMapping("/allByTitle")
    @Operation(summary = "Get all Book by title")
    public ResponseEntity<List<BookResponseDto>> getBookByTitle(@RequestParam("title") String title) {
        List<BookResponseDto> result = bookService.findByTitle(title);
        return result.isEmpty() ? new ResponseEntity<>(result, HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/allByAuthor")
    @Operation(summary = "Get all Book by author name")
    public ResponseEntity<List<BookResponseDto>> getBookByAuthor(@RequestParam("authorName") String authorName) {
        List<BookResponseDto> result = bookService.findByAuthor(authorName);
        return result.isEmpty() ? new ResponseEntity<>(result, HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(result, HttpStatus.OK);

    }

    @GetMapping("/allByGenre")
    @Operation(summary = "Get all Book by genre name")
    public ResponseEntity<List<BookResponseDto>> getBookByGenre(@RequestParam("genre") String genre) {
        List<BookResponseDto> result = bookService.findByGenre(genre);
        return result.isEmpty() ? new ResponseEntity<>(result, HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(result, HttpStatus.OK);
    }

}
