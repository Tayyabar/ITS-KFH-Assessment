package com.example.education;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/allocations")
public class AllocationController {

	@Autowired
	private AllocationRepository allocationRepository;

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private StudentRepository studentRepository;

	@GetMapping
	public List<Allocation> findAll() {
		return allocationRepository.findAll();
	}

	@PostMapping("")
	public Allocation allocateStudentToCourse(@RequestParam Long courseId, @RequestParam Long studentId) {
		System.out.println("insideAllocationuurses:: " + courseId + "" + studentId);
		Course course = courseRepository.findById(courseId)
				.orElseThrow(() -> new CourseNotFoundException("Course not found with id: " + courseId));

		Student student = studentRepository.findById(studentId)
				.orElseThrow(() -> new StudentNotFoundException("Student not found with id: " + studentId));

		Allocation allocation = new Allocation();
		allocation.setCourse(course);
		allocation.setStudent(student);

		return allocationRepository.save(allocation);
	}
}
