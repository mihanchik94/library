package org.example.library.service;

import org.example.library.dto.BookRequestDto;
import org.example.library.dto.BookResponseDto;

import java.util.List;

public interface BookService {
    BookResponseDto save(BookRequestDto bookRequestDto);
    void delete(long id);
    BookResponseDto update(long id, BookRequestDto bookRequestDto);
    List<BookResponseDto> findByTitle(String title);
    List<BookResponseDto> findByAuthor(String authorName);
    List<BookResponseDto> findByGenre(String genreName);
}
