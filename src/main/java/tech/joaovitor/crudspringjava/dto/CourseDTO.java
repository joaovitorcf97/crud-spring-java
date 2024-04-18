package tech.joaovitor.crudspringjava.dto;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import tech.joaovitor.crudspringjava.enums.Category;
import tech.joaovitor.crudspringjava.enums.validation.ValueOfEnum;

public record CourseDTO(
    String id,

    @NotBlank
    @NotNull
    @Length(min = 5, max = 100)
    String name,

    @NotNull
    @Length(max = 10)
    @ValueOfEnum(enumClass = Category.class)
    String category,
    
    @NotNull
    @NotEmpty
    @Valid
    List<LessonDTO> lessons
) {

}
