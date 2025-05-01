package com.school.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "vaccination_record")
public class VaccinationRecord {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "student_name")
	private String studentName;

	@Column(name = "vaccine_name")
	private String vaccineName;

	@Column(name = "vaccination_date")
	private Date vaccinationDate;

	@Column(name = "vaccinated")
	private Boolean vaccinated;

	public VaccinationRecord() {
	}

	public VaccinationRecord(String studentName, String vaccineName, Date vaccinationDate, Boolean vaccinated) {
		this.studentName = studentName;
		this.vaccineName = vaccineName;
		this.vaccinationDate = vaccinationDate;
		this.vaccinated = vaccinated;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getVaccineName() {
		return vaccineName;
	}

	public void setVaccineName(String vaccineName) {
		this.vaccineName = vaccineName;
	}

	public Date getVaccinationDate() {
		return vaccinationDate;
	}

	public void setVaccinationDate(Date vaccinationDate) {
		this.vaccinationDate = vaccinationDate;
	}

	public Boolean getVaccinated() {
		return vaccinated;
	}

	public void setVaccinated(Boolean vaccinated) {
		this.vaccinated = vaccinated;
	}

	@Override
	public String toString() {
		return "VaccinationRecord [id=" + id + ", studentName=" + studentName + ", vaccineName=" + vaccineName
				+ ", vaccinationDate=" + vaccinationDate + ", vaccinated=" + vaccinated + "]";
	}
}
