package org.example.library.mapper;

import org.example.library.dto.BookRequestDto;
import org.example.library.dto.BookResponseDto;
import org.example.library.model.Author;
import org.example.library.model.Book;
import org.example.library.model.Genre;
import org.example.library.service.AuthorService;
import org.example.library.service.GenreService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.example.library.testData.AuthorTestData.AUTHOR_ID;
import static org.example.library.testData.AuthorTestData.createAuthorWithId1L;
import static org.example.library.testData.BookTestData.*;
import static org.example.library.testData.GenreTestData.GENRE_IDS;
import static org.example.library.testData.GenreTestData.createGenresSet;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookMapperTest {
    @Mock
    private AuthorService authorService;

    @Mock
    private GenreService genreService;

    @InjectMocks
    private BookMapperImpl bookMapper;

    @Test
    void whenFromBookRequestDtoToBookThenBook() {
        BookRequestDto bookRequestDto = createBookRequestDto();
        Author author = createAuthorWithId1L();
        Set<Genre> genres = createGenresSet();

        when(authorService.findById(AUTHOR_ID)).thenReturn(author);
        when(genreService.findAllGenresByIds(GENRE_IDS)).thenReturn(genres);

        Book result = bookMapper.fromBookRequestDtoToBook(bookRequestDto);

        assertEquals(bookRequestDto.getTitle(), result.getTitle());
        assertEquals(author, result.getAuthor());
        assertEquals(2, result.getGenres().size());
        verify(authorService).findById(AUTHOR_ID);
        verify(genreService).findAllGenresByIds(GENRE_IDS);
    }

    @Test
    void whenFromBookToBookResponseDtoThenBookResponseDto() {
        Book book = createBookWithId1L();
        Set<String> expectedGenreNames = book.getGenres().stream()
                .map(Genre::getName)
                .collect(Collectors.toSet());

        BookResponseDto dto = bookMapper.fromBookToBookResponseDto(book);

        assertEquals(book.getTitle(), dto.getTitle());
        assertEquals(book.getAuthor().getName(), dto.getAuthorName());
        assertEquals(expectedGenreNames, dto.getGenreNames());
    }

    @Test
    void whenFromBookListToBookResponseDtoListThenBookResponseDtoList() {
        List<Book> books = createBookList();

        List<BookResponseDto> dtos = bookMapper.fromBookListToBookResponseDtoList(books);

        assertEquals(books.size(), dtos.size());
        for (int index = 0; index < books.size(); index++) {
            assertEquals(books.get(index).getTitle(), dtos.get(index).getTitle());
        }
    }
}