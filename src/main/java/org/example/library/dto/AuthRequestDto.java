package org.example.library.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "with")
public class AuthRequestDto {
    @NotBlank(message = "Email is mandatory!")
    @Email(message = "Invalid e-mail address")
    @Schema(defaultValue = "test@library.com", description = "Email address")
    private String email;

    @NotBlank(message = "Password is mandatory!")
    @Schema(defaultValue = "password", description = "Password")
    private String password;
}
