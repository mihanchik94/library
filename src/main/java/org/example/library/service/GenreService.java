package org.example.library.service;

import org.example.library.model.Genre;
import java.util.Set;

public interface GenreService {
    Set<Genre> findAllGenresByIds(Set<Long> ids);
}
