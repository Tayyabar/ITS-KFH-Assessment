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
@RequestMapping("/student")
public class StudentController {
	@Autowired
	StudentRepository student;

	@PostMapping
	public Student createStudent(@RequestBody Student st) {
		System.out.println("whatever is:: " + st);

		return student.save(st);
	}

	@GetMapping
	public List<Student> findAll() {
		return student.findAll();
	}

	@GetMapping("/{id}")
	public Optional<Student> getStudentById(@PathVariable Long id) {
		System.out.println("Id is:: " + id);
		Optional<Student> st = student.findById(id);
		System.out.println("student:: " + st);
		if (st == null || st.isEmpty()) {
			System.out.println("not found here");
			throw new StudentNotFoundException("Student not found by id:: " + id);
		}
		return st;
	}

	@DeleteMapping("/{id}")
	public String deleteStudent(@PathVariable Long id) {
		student.deleteById(id);
		return "deleted";
	}

	@PostMapping("/{id}")
	public Student updateStudentCourse(@PathVariable Long id, @RequestBody Student st) {
		Optional<Student> optionalStudent = student.findById(id);
		if (optionalStudent.isEmpty()) {
			throw new StudentNotFoundException("Employee not found with id: " + id);
		}
		Student existingStudent = optionalStudent.get();
		existingStudent.setName(st.getName());
		return student.save(existingStudent);
	}

	@ExceptionHandler(StudentNotFoundException.class)
	public String handleStudentNotFoundException(StudentNotFoundException ex) {
		return ex.getMessage();
	}
}
