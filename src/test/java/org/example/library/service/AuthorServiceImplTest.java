package org.example.library.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.library.model.Author;
import org.example.library.repository.AuthorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.example.library.testData.AuthorTestData.AUTHOR_ID;
import static org.example.library.testData.AuthorTestData.createAuthorWithId1L;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthorServiceImplTest {
    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorServiceImpl authorService;

    @Test
    public void whenFindByIdAndAuthorExistsThenReturnAuthor() {
        Author expected = createAuthorWithId1L();
        when(authorRepository.findById(expected.getId())).thenReturn(Optional.of(expected));
        Author actual = authorService.findById(expected.getId());
        assertEquals(expected, actual);
    }

    @Test
    public void whenFindByIdAndAuthorNotExistThenThrowException() {
        when(authorRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> authorService.findById(AUTHOR_ID));
    }
}