package com.school.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.school.entity.VaccinationRecord;
import com.school.repository.VaccinationRecordRepository;

@Service
public class VaccinationReportService {

	@Autowired
	private VaccinationRecordRepository recordRepository;

	public List<VaccinationRecord> getAllRecords() {
		return recordRepository.findAll();
	}

	public List<VaccinationRecord> getRecordsByVaccineName(String vaccineName) {
		return recordRepository.findByVaccineName(vaccineName);
	}

	public Page<VaccinationRecord> getFilteredRecords(String vaccineName, Pageable pageable) {
		if (vaccineName == null || vaccineName.isEmpty()) {
			return recordRepository.findAll(pageable);
		}
		return recordRepository.findByVaccineNameContainingIgnoreCase(vaccineName, pageable);
	}
}
