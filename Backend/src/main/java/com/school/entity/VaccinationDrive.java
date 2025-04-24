package com.school.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "vaccination_drive")
public class VaccinationDrive {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "vaccine_name")
	private String vaccineName;

	@Column(name = "drive_date")
	private LocalDate driveDate;

	@Column(name = "available_doses")
	private int availableDoses;

	@Column(name = "applicable_classes")
	private String applicableClasses;

	public VaccinationDrive() {

	}

	public VaccinationDrive(String vaccineName, LocalDate driveDate, int availableDoses, String applicableClasses) {
		this.vaccineName = vaccineName;
		this.driveDate = driveDate;
		this.availableDoses = availableDoses;
		this.applicableClasses = applicableClasses;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVaccineName() {
		return vaccineName;
	}

	public void setVaccineName(String vaccineName) {
		this.vaccineName = vaccineName;
	}

	public LocalDate getDriveDate() {
		return driveDate;
	}

	public void setDriveDate(LocalDate driveDate) {
		this.driveDate = driveDate;
	}

	public int getAvailableDoses() {
		return availableDoses;
	}

	public void setAvailableDoses(int availableDoses) {
		this.availableDoses = availableDoses;
	}

	public String getApplicableClasses() {
		return applicableClasses;
	}

	public void setApplicableClasses(String applicableClasses) {
		this.applicableClasses = applicableClasses;
	}

	@Override
	public String toString() {
		return "VaccinationDrive [id=" + id + ", vaccineName=" + vaccineName + ", driveDate=" + driveDate
				+ ", availableDoses=" + availableDoses + ", applicableClasses=" + applicableClasses + "]";
	}
}
