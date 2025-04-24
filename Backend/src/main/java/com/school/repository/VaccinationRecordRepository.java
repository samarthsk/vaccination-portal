package com.school.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.school.entity.VaccinationRecord;

@Repository
public interface VaccinationRecordRepository extends JpaRepository<VaccinationRecord, Long> {
	List<VaccinationRecord> findByVaccineName(String vaccineName);

	Page<VaccinationRecord> findByVaccineNameContainingIgnoreCase(String vaccineName, Pageable pageable);

}
