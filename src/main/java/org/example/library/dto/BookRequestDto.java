package org.example.library.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "with")
public class BookRequestDto {
    @NotBlank(message = "The book title is mandatory!")
    @Schema(description = "The book title")
    private String title;

    @NotNull(message ="The author id is mandatory!")
    @Min(value = 1L, message = "Min author id must not be less than 1")
    @Schema(example = "1", description = "The author id")
    private Long authorId;

    @NotEmpty(message = "Set of genres must have at least 1 genre")
    @Size(max = 6, message = "Amount of genres should not be more than 6")
    @Schema(description = "The genres ids", example = "[1]")
    private Set<@Min(value = 1L, message = "Min genre id must not be less than 1") Long> genreIds;

}
