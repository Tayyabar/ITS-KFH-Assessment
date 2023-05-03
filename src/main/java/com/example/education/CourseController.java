package com.example.education;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/course")
public class CourseController {
	@Autowired
	CourseRepository course;

	@PostMapping
	public Course createCourse(@RequestBody Course c) {
		System.out.println("whatever is:: " + c);

		return course.save(c);
	}

	@GetMapping
	public List<Course> findAll() {
		return course.findAll();
	}

	@GetMapping("/{id}")
	public Optional<Course> getCourseById(@PathVariable Long id) {
		System.out.println("Id is:: " + id);
		Optional<Course> c = course.findById(id);
		System.out.println("Course:: " + c);
		if (c == null || c.isEmpty()) {
			System.out.println("not found here");
			throw new StudentNotFoundException("Course not found by id:: " + id);
		}
		return c;
	}

	@DeleteMapping("/{id}")
	public String deleteCourse(@PathVariable Long id) {
		course.deleteById(id);
		return "deleted";
	}

	@PostMapping("/{id}")
	public Course updateCourse(@PathVariable Long id, @RequestBody Course c) {
		System.out.println("inside post update:: "  + c);
		Optional<Course> optionalCourse = course.findById(id);
		if (optionalCourse.isEmpty()) {
			throw new StudentNotFoundException("Course not found with id: " + id);
		}
		Course existingCourse = optionalCourse.get();
		existingCourse.setName(c.getName());
		return course.save(existingCourse);
	}

	@ExceptionHandler(CourseNotFoundException.class)
	public String handleCourseNotFoundException(CourseNotFoundException ex) {
		return ex.getMessage();
	}
}
