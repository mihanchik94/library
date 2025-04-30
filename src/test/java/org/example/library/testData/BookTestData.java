package org.example.library.testData;

import org.example.library.dto.BookRequestDto;
import org.example.library.dto.BookResponseDto;
import org.example.library.model.Book;

import java.util.List;
import java.util.Set;

import static org.example.library.testData.AuthorTestData.*;
import static org.example.library.testData.GenreTestData.*;

public final class BookTestData {

    public static final Long BOOK_ID = 1L;
    public static final String BOOK_TITLE_WAR_AND_PEACE = "War and Peace";

    private BookTestData() {
        throw new UnsupportedOperationException("Cannot create an instance");
    }

    public static BookRequestDto createBookRequestDto() {
        return BookRequestDto.builder()
                .withTitle(BOOK_TITLE_WAR_AND_PEACE)
                .withAuthorId(AUTHOR_ID)
                .withGenreIds(GENRE_IDS)
                .build();
    }

    public static Book createBookWithId1L() {
        return Book.builder()
                .withId(BOOK_ID)
                .withTitle(BOOK_TITLE_WAR_AND_PEACE)
                .withAuthor(createAuthorWithId1L())
                .withGenres(createGenresSet())
                .build();
    }


    public static BookResponseDto createBookResponseDto() {
        return BookResponseDto.builder()
                .withTitle(BOOK_TITLE_WAR_AND_PEACE)
                .withAuthorName(AUTHOR_NAME_LEV_TOLSTOY)
                .withGenreNames(Set.of(GENRE_NAME_NOVEL))
                .build();

    }

    public static List<Book> createBookList() {
        return List.of(createBookWithId1L());
    }

    public static List<BookResponseDto> createBookResponseDtoList() {
        return List.of(createBookResponseDto());
    }


}
