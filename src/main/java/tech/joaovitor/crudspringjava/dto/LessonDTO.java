package tech.joaovitor.crudspringjava.dto;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LessonDTO(
    @UUID
    String id,

    @NotNull
    @NotBlank
    @Length(min = 5, max = 100)
    String name,

    @NotNull
    @NotBlank
    @Length(min = 10, max = 11)
    String youtubeUrl 
) {
    
}
