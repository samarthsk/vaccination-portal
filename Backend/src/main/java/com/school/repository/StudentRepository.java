package com.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.school.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	long countByVaccinated(boolean status);

	Student findByStudentId(String studentId);
}
