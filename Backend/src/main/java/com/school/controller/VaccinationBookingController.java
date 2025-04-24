package com.school.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.entity.VaccinationBooking;
import com.school.service.VaccinationBookingService;

@RestController
@RequestMapping("/api/drive-bookings")
public class VaccinationBookingController {

	@Autowired
	private VaccinationBookingService service;

	@PostMapping
	public VaccinationBooking createDrive(@RequestBody VaccinationBooking booking) {
		return service.createDrive(booking);
	}

	@GetMapping
	public List<VaccinationBooking> getAllDrives() {
		return service.getAllDrives();
	}

	@PutMapping("/{id}")
	public VaccinationBooking updateDrive(@PathVariable Long id, @RequestBody VaccinationBooking booking) {
		return service.updateDrive(id, booking);
	}

}
