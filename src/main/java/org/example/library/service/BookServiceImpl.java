package org.example.library.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.library.dto.BookRequestDto;
import org.example.library.dto.BookResponseDto;
import org.example.library.mapper.BookMapper;
import org.example.library.model.Author;
import org.example.library.model.Book;
import org.example.library.model.Genre;
import org.example.library.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final AuthorService authorService;
    private final GenreService genreService;

    @Transactional
    @Override
    public BookResponseDto save(BookRequestDto bookRequestDto) {
        log.info("Attempting to save new book: {}", bookRequestDto.getTitle());
        Book book = bookMapper.fromBookRequestDtoToBook(bookRequestDto);
        return bookMapper.fromBookToBookResponseDto(bookRepository.save(book));
    }

    @Override
    public void delete(long id) {
        log.debug("Deleting book with ID: {}", id);
        bookRepository.deleteById(id);
    }


    @Transactional
    @Override
    public BookResponseDto update(long id, BookRequestDto bookRequestDto) {
        log.info("Updating book with ID: {}", id);
        Book updatingBook = findById(id);
        updateBookFields(updatingBook, bookRequestDto);
        return bookMapper.fromBookToBookResponseDto(updatingBook);
    }

    @Override
    public List<BookResponseDto> findByTitle(String title) {
        log.debug("Searching books by title: {}", title);
        return bookMapper.fromBookListToBookResponseDtoList(bookRepository.findAllByTitle(title));
    }

    @Override
    public List<BookResponseDto> findByAuthor(String authorName) {
        log.debug("Searching books by author: {}", authorName);
        return bookMapper.fromBookListToBookResponseDtoList(bookRepository.findAllByAuthor(authorName));
    }

    @Override
    public List<BookResponseDto> findByGenre(String genreName) {
        log.debug("Searching books by genre: {}", genreName);
        return bookMapper.fromBookListToBookResponseDtoList(bookRepository.findAllByGenre(genreName));
    }

    private Book findById(long id) {
        log.debug("Looking for book with ID: {}", id);
        return bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Book with id: %d not found", id)));
    }

    private void updateBookFields(Book book, BookRequestDto bookRequestDto) {
        log.debug("Updating fields for book ID: {}", book.getId());
        Author author = authorService.findById(bookRequestDto.getAuthorId());
        Set<Genre> genres = genreService.findAllGenresByIds(bookRequestDto.getGenreIds());
        book.setTitle(bookRequestDto.getTitle());
        book.setAuthor(author);
        book.setGenres(genres);
    }

}
