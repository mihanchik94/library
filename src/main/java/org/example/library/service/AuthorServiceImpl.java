package org.example.library.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.library.model.Author;
import org.example.library.repository.AuthorRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    @Override
    public Author findById(long id) {
        return authorRepository.findById(id)
                .orElseThrow(() ->
                    new EntityNotFoundException(String.format("Author with id: %d not found", id)));
    }
}
