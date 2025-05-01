
package com.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.school.entity.Student;
import com.school.service.StudentService;

@RestController
@RequestMapping("/api/students")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@PostMapping
	public ResponseEntity<Student> addStudent(@RequestBody Student student) {
		Student savedStudent = studentService.addStudent(student);
		return ResponseEntity.ok(savedStudent);
	}

	@PostMapping("/bulk-upload")
	public ResponseEntity<String> bulkUploadStudents(@RequestParam("file") MultipartFile file) {
		try {
			studentService.bulkUploadStudents(file);
			return ResponseEntity.ok("File uploaded and students added successfully.");
		} catch (Exception e) {
			return ResponseEntity.status(500).body("Error uploading file: " + e.getMessage());
		}
	}

	@GetMapping("/find")
	public ResponseEntity<Student> findStudentByStudentId(@RequestParam String studentId) {
		Student student = studentService.findByStudentId(studentId);
		if (student != null) {
			return ResponseEntity.ok(student);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/update-status")
	public ResponseEntity<String> updateVaccinationStatus(@RequestParam String studentId,
			@RequestParam boolean vaccinated) {
		boolean updated = studentService.updateVaccinationStatus(studentId, vaccinated);
		if (updated) {
			return ResponseEntity.ok("Vaccination status updated successfully.");
		} else {
			return ResponseEntity.status(404).body("Student not found.");
		}
	}

	@PutMapping("/update-details")
	public ResponseEntity<String> updateStudentDetails(@RequestBody Student updatedStudent) {
		boolean updated = studentService.updateStudentDetails(updatedStudent);
		if (updated) {
			return ResponseEntity.ok("Student details updated successfully.");
		} else {
			return ResponseEntity.status(404).body("Student not found.");
		}
	}

}
