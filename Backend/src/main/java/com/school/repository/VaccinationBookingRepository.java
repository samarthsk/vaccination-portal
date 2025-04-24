package com.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.entity.VaccinationBooking;

public interface VaccinationBookingRepository extends JpaRepository<VaccinationBooking, Long> {
}
