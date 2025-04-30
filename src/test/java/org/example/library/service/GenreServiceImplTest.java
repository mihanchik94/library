package org.example.library.service;

import org.example.library.exception.GenresNotFoundException;
import org.example.library.model.Genre;
import org.example.library.repository.GenreRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

import static org.example.library.testData.GenreTestData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GenreServiceImplTest {
    @Mock
    private GenreRepository genreRepository;

    @InjectMocks
    private GenreServiceImpl genreService;

    @Test
    public void whenFindAllGenresByExistingIdsThenReturnListOfGenres() {
        Set<Long> genreIds = GENRE_IDS;
        Set<Genre> expectedGenres = createGenresSet();
        when(genreRepository.findAllByIds(genreIds)).thenReturn(new ArrayList<>(expectedGenres));
        Set<Genre> actualGenres = genreService.findAllGenresByIds(genreIds);
        assertEquals(expectedGenres, actualGenres);
    }

    @Test
    public void whenFindAllGenresByNotExistingIdsThenThrowException() {
        when(genreRepository.findAllByIds(anySet())).thenReturn(Collections.emptyList());
        assertThrows(GenresNotFoundException.class, () -> genreService.findAllGenresByIds(GENRE_IDS));
    }
}