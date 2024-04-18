package tech.joaovitor.crudspringjava.controller;

import org.hibernate.validator.constraints.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import tech.joaovitor.crudspringjava.dto.CourseDTO;
import tech.joaovitor.crudspringjava.dto.CoursePageDTO;
import tech.joaovitor.crudspringjava.service.CourseService;

@RestController
@RequestMapping(value = "/api/course")
@Validated
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public CoursePageDTO list() {
        return courseService.list();
    }

    @GetMapping("/{id}")
    public CourseDTO findById(
        @PathVariable("id") @NotNull @UUID String id
    ) {
        return courseService.findById(id);
    }
    
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public CourseDTO create(
        @RequestBody @Valid CourseDTO course
    ) {
        return courseService.create(course);
    }

    @PutMapping("/{id}")
    public CourseDTO update(
        @PathVariable("id") @NotNull @UUID String id,
        @RequestBody @Valid CourseDTO course
    ){  
        return courseService.update(id, course);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(
        @PathVariable("id") @NotNull String id
    ) {
        courseService.delete(id);
    }
}
