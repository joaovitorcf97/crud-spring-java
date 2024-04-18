package tech.joaovitor.crudspringjava.dto;

import java.util.List;

public record CoursePageDTO(
    List<CourseDTO> courses,
    long totalElements,
    int totalPages
) {
    
}
