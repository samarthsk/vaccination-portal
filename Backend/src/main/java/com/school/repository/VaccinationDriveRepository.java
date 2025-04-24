package com.school.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.school.entity.VaccinationDrive;

@Repository
public interface VaccinationDriveRepository extends JpaRepository<VaccinationDrive, Long> {
	List<VaccinationDrive> findByDriveDateBetween(LocalDate start, LocalDate end);
}
