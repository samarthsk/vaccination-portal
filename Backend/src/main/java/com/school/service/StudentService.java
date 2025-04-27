package com.school.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.school.entity.Student;
import com.school.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;

	public Student addStudent(Student student) {
		return studentRepository.save(student);
	}

	public void bulkUploadStudents(MultipartFile file) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));
		String line;
		while ((line = br.readLine()) != null) {
			String[] data = line.split(",");
			Student student = new Student(data[0], data[1], data[2], Boolean.parseBoolean(data[3]));
			studentRepository.save(student);
		}
		br.close();
	}

	public Student findByStudentId(String studentId) {
		return studentRepository.findByStudentId(studentId);
	}

	public boolean updateVaccinationStatus(String studentId, boolean vaccinated) {
		Student student = studentRepository.findByStudentId(studentId);
		if (student != null) {
			student.setVaccinated(vaccinated);
			studentRepository.save(student);
			return true;
		}
		return false;
	}

	public boolean updateStudentDetails(Student updatedStudent) {
		Student existing = studentRepository.findByStudentId(updatedStudent.getStudentId());
		if (existing != null) {
			existing.setName(updatedStudent.getName());
			existing.setStudentClass(updatedStudent.getStudentClass());
			existing.setVaccinated(updatedStudent.isVaccinated());
			studentRepository.save(existing);
			return true;
		}
		return false;
	}

}
