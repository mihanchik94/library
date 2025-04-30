package org.example.library.service;

import lombok.RequiredArgsConstructor;
import org.example.library.exception.GenresNotFoundException;
import org.example.library.model.Genre;
import org.example.library.repository.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;

    @Override
    public Set<Genre> findAllGenresByIds(Set<Long> ids) {
        List<Genre> result = genreRepository.findAllByIds(ids);
        if (result.isEmpty()) {
            throw new GenresNotFoundException(String.format("Genres with ids: %s not found", ids));
        }
        return new HashSet<>(result);
    }
}
