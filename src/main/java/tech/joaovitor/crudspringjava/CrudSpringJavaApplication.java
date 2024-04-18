package tech.joaovitor.crudspringjava;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import tech.joaovitor.crudspringjava.enums.Category;
import tech.joaovitor.crudspringjava.enums.Status;
import tech.joaovitor.crudspringjava.model.Course;
import tech.joaovitor.crudspringjava.model.Lesson;
import tech.joaovitor.crudspringjava.repository.CourseRepository;

@SpringBootApplication
public class CrudSpringJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringJavaApplication.class, args);
	}

	@Bean
	CommandLineRunner initDataBase(CourseRepository courseRepository) {
		return args -> {
			courseRepository.deleteAll();

			for(int i = 0; i < 23; i++) {
				Course course = new Course();
				course.setName("Crud Spring");
				course.setCategory(Category.BACK_END);
				course.setStatus(Status.ACTIVE);
	
				Lesson lesson = new Lesson();
				lesson.setName("Introdução");
				lesson.setYoutubeUrl("Nb4uxLxdvxo");
				lesson.setCourse(course);
	
				course.getLessons().add(lesson);
	
				courseRepository.save(course);
			}
		};
	}
}
