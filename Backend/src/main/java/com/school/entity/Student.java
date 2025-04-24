package com.school.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "student")
public class Student {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "student_id")
	private String studentId;

	@Column(name = "student_class")
	private String studentClass;

	@Column(name = "vaccinated")
	private boolean vaccinated;

	public Student() {

	}

	public Student(String name, String studentId, String studentClass, boolean vaccinated) {
		this.name = name;
		this.studentId = studentId;
		this.studentClass = studentClass;
		this.vaccinated = vaccinated;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getStudentClass() {
		return studentClass;
	}

	public void setStudentClass(String studentClass) {
		this.studentClass = studentClass;
	}

	public boolean isVaccinated() {
		return vaccinated;
	}

	public void setVaccinated(boolean vaccinated) {
		this.vaccinated = vaccinated;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", studentId=" + studentId + ", studentClass=" + studentClass
				+ ", vaccinated=" + vaccinated + "]";
	}
}
