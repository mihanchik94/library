package org.example.library.service;

import org.example.library.model.Author;

public interface AuthorService {
    Author findById(long id);
}
