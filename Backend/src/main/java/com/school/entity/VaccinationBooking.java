package com.school.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "vaccination_drive_booking")
public class VaccinationBooking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "date")
	private LocalDate date;

	@Column(name = "vaccines_available")
	private int vaccinesAvailable;

	@Column(name = "vaccine_name")
	private String vaccineName;

	public VaccinationBooking() {

	}

	public VaccinationBooking(LocalDate date, int vaccinesAvailable, String vaccineName) {
		this.date = date;
		this.vaccinesAvailable = vaccinesAvailable;
		this.vaccineName = vaccineName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public int getVaccinesAvailable() {
		return vaccinesAvailable;
	}

	public void setVaccinesAvailable(int vaccinesAvailable) {
		this.vaccinesAvailable = vaccinesAvailable;
	}

	public String getVaccineName() {
		return vaccineName;
	}

	public void setVaccineName(String vaccineName) {
		this.vaccineName = vaccineName;
	}

	@Override
	public String toString() {
		return "VaccinationBooking [id=" + id + ", date=" + date + ", vaccinesAvailable=" + vaccinesAvailable
				+ ", vaccineName=" + vaccineName + "]";
	}
}
