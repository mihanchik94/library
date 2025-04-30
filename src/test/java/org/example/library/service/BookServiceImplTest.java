package org.example.library.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.library.dto.BookRequestDto;
import org.example.library.dto.BookResponseDto;
import org.example.library.mapper.BookMapper;
import org.example.library.model.Book;
import org.example.library.repository.BookRepository;
import org.example.library.testData.BookTestData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.example.library.testData.AuthorTestData.AUTHOR_ID;
import static org.example.library.testData.BookTestData.*;
import static org.example.library.testData.GenreTestData.GENRE_IDS;
import static org.example.library.testData.GenreTestData.GENRE_NAME_NOVEL;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {
    private static final Long BOOK_ID = BookTestData.BOOK_ID;
    private static final Book BOOK = createBookWithId1L();
    private static final BookRequestDto BOOK_REQUEST_DTO = createBookRequestDto();
    private static final BookResponseDto BOOK_RESPONSE_DTO = createBookResponseDto();
    private static final List<Book> BOOK_LIST = createBookList();
    private static final List<BookResponseDto> BOOK_RESPONSE_DTO_LIST = createBookResponseDtoList();


    @Mock
    private BookRepository bookRepository;
    @Mock
    private BookMapper bookMapper;
    @Mock
    private AuthorService authorService;
    @Mock
    private GenreService genreService;
    @InjectMocks
    private BookServiceImpl bookService;



    @Test
    public void whenSaveThenBookResponseDto() {
        when(bookMapper.fromBookRequestDtoToBook(BOOK_REQUEST_DTO)).thenReturn(BOOK);
        when(bookRepository.save(BOOK)).thenReturn(BOOK);
        when(bookMapper.fromBookToBookResponseDto(BOOK)).thenReturn(BOOK_RESPONSE_DTO);
        BookResponseDto actual = bookService.save(BOOK_REQUEST_DTO);
        assertEquals(BOOK_RESPONSE_DTO, actual);
    }

    @Test
    void whenDeleteThenCallRepositoryDeleteById() {
        bookService.delete(BOOK_ID);
        verify(bookRepository, times(1)).deleteById(BOOK_ID);
    }

    @Test
    void whenUpdateThenBookResponseDto() {
        when(bookRepository.findById(BOOK_ID)).thenReturn(Optional.of(BOOK));
        when(authorService.findById(AUTHOR_ID)).thenReturn(BOOK.getAuthor());
        when(genreService.findAllGenresByIds(GENRE_IDS)).thenReturn(BOOK.getGenres());
        when(bookMapper.fromBookToBookResponseDto(BOOK)).thenReturn(BOOK_RESPONSE_DTO);
        BookResponseDto actual = bookService.update(BOOK_ID, BOOK_REQUEST_DTO);
        assertEquals(BOOK_RESPONSE_DTO, actual);
    }

    @Test
    void whenUpdateNotExistingBookThenThrowException() {
        when(bookRepository.findById(BOOK_ID)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> bookService.update(BOOK_ID, BOOK_REQUEST_DTO));
    }


    @Test
    void whenFindByTitleThenBookResponseDtoList() {
        when(bookRepository.findAllByTitle(BOOK.getTitle())).thenReturn(BOOK_LIST);
        when(bookMapper.fromBookListToBookResponseDtoList(BOOK_LIST)).thenReturn(BOOK_RESPONSE_DTO_LIST);
        List<BookResponseDto> actual = bookService.findByTitle(BOOK.getTitle());
        assertEquals(BOOK_RESPONSE_DTO_LIST, actual);
    }

    @Test
    void whenFindByAuthorThenBookResponseDtoList() {
        when(bookRepository.findAllByAuthor(BOOK.getAuthor().getName())).thenReturn(BOOK_LIST);
        when(bookMapper.fromBookListToBookResponseDtoList(BOOK_LIST)).thenReturn(BOOK_RESPONSE_DTO_LIST);
        List<BookResponseDto> actual = bookService.findByAuthor(BOOK.getAuthor().getName());
        assertEquals(BOOK_RESPONSE_DTO_LIST, actual);
    }

    @Test
    void whenFindByGenreThenBookResponseDtoList() {
        when(bookRepository.findAllByGenre(GENRE_NAME_NOVEL)).thenReturn(BOOK_LIST);
        when(bookMapper.fromBookListToBookResponseDtoList(BOOK_LIST)).thenReturn(BOOK_RESPONSE_DTO_LIST);
        List<BookResponseDto> actual = bookService.findByGenre(GENRE_NAME_NOVEL);
        assertEquals(BOOK_RESPONSE_DTO_LIST, actual);
    }
}