package com.school.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.entity.VaccinationBooking;
import com.school.repository.VaccinationBookingRepository;

@Service
public class VaccinationBookingService {

	@Autowired
	private VaccinationBookingRepository repository;

	public VaccinationBooking createDrive(VaccinationBooking booking) {
		return repository.save(booking);
	}

	public List<VaccinationBooking> getAllDrives() {
		return repository.findAll();
	}

	public VaccinationBooking updateDrive(Long id, VaccinationBooking updatedBooking) {
		VaccinationBooking existing = repository.findById(id)
				.orElseThrow(() -> new RuntimeException("Drive not found"));

		// Disallow edits for past drives
		if (existing.getDate().isBefore(LocalDate.now())) {
			throw new RuntimeException("Cannot edit completed drive.");
		}

		existing.setDate(updatedBooking.getDate());
		existing.setVaccinesAvailable(updatedBooking.getVaccinesAvailable());
		existing.setVaccineName(updatedBooking.getVaccineName());
		return repository.save(existing);
	}

}
