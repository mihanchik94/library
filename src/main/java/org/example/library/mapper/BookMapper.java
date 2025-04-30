package org.example.library.mapper;

import org.example.library.dto.BookRequestDto;
import org.example.library.dto.BookResponseDto;
import org.example.library.model.Book;
import org.example.library.model.Genre;
import org.example.library.service.AuthorService;
import org.example.library.service.GenreService;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        builder = @Builder(disableBuilder = true))
public abstract class BookMapper {
    @Autowired
    protected AuthorService authorService;
    @Autowired
    protected GenreService genreService;


    @Mapping(target = "author", expression = "java(authorService.findById(bookRequestDto.getAuthorId()))")
    @Mapping(target = "genres", expression = "java(genreService.findAllGenresByIds(bookRequestDto.getGenreIds()))")
    public abstract Book fromBookRequestDtoToBook(BookRequestDto bookRequestDto);

    @Mapping(source = "author.name", target = "authorName")
    @Mapping(target = "genreNames", expression = "java(fromGenresToGenreNames(book.getGenres()))")
    public abstract BookResponseDto fromBookToBookResponseDto(Book book);

    public abstract List<BookResponseDto>  fromBookListToBookResponseDtoList(List<Book> books);

    protected Set<String> fromGenresToGenreNames(Set<Genre> genres) {
        return genres.stream()
                .map(Genre::getName)
                .collect(Collectors.toSet());
    }
}
