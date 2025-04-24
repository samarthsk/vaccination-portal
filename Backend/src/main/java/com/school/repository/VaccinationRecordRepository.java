package com.school.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.school.entity.VaccinationRecord;

public interface VaccinationRecordRepository extends JpaRepository<VaccinationRecord, Long> {
	List<VaccinationRecord> findByVaccineName(String vaccineName);

	Page<VaccinationRecord> findByVaccineNameContainingIgnoreCase(String vaccineName, Pageable pageable);

}
