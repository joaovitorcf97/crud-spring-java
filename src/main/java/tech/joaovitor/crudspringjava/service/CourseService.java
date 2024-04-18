package tech.joaovitor.crudspringjava.service;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.validator.constraints.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import tech.joaovitor.crudspringjava.dto.CourseDTO;
import tech.joaovitor.crudspringjava.dto.CoursePageDTO;
import tech.joaovitor.crudspringjava.dto.mapper.CourseMapper;
import tech.joaovitor.crudspringjava.exception.RecordNotFoundException;
import tech.joaovitor.crudspringjava.model.Course;
import tech.joaovitor.crudspringjava.repository.CourseRepository;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public CourseService(CourseRepository courseRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

    public CoursePageDTO list() {
        Page<Course> pageCourse = courseRepository.findAll(PageRequest.of(0, 10));
        List<CourseDTO> courses = pageCourse.get().map(courseMapper::toDTO).collect(Collectors.toList());
        return new CoursePageDTO(courses, pageCourse.getTotalElements(), pageCourse.getTotalPages());
    }

    public CourseDTO findById(String id) {
        return courseRepository
            .findById(id).map(courseMapper::toDTO)
            .orElseThrow(() -> new RecordNotFoundException(id));
    }
    
    public CourseDTO create(@Valid @NotNull CourseDTO course) {
        return courseMapper.toDTO(courseRepository.save(courseMapper.toEntity(course)));
    }


    public CourseDTO update(@NotNull @UUID String id, @Valid @NotNull CourseDTO courseDTO) {
        return courseRepository.findById(id)
            .map(recordFound -> {
                Course course = courseMapper.toEntity(courseDTO);
                    recordFound.setName(courseDTO.name());
                    recordFound.setCategory(courseMapper.convertCategoryValue(courseDTO.category()));
                    recordFound.getLessons().clear();
                    course.getLessons().forEach(recordFound.getLessons()::add);
                    return courseMapper.toDTO(courseRepository.save(recordFound));
            })
            .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@NotNull String id) {
        courseRepository.delete(courseRepository.findById(id)
            .orElseThrow(() -> new RecordNotFoundException(id)));
    }
}
