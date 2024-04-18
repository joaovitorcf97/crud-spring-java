package tech.joaovitor.crudspringjava.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tech.joaovitor.crudspringjava.model.Course;

public interface CourseRepository extends JpaRepository<Course, String> {
    
}
