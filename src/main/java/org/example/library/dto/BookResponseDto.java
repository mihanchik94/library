package org.example.library.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class BookResponseDto {
    private String title;
    private String authorName;
    private Set<String> genreNames;

}
