package com.school.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.school.entity.VaccinationDrive;
import com.school.repository.StudentRepository;
import com.school.repository.VaccinationDriveRepository;

@Service
public class DashboardService {

	private final StudentRepository studentRepo;
	private final VaccinationDriveRepository driveRepo;

	public DashboardService(StudentRepository studentRepo, VaccinationDriveRepository driveRepo) {
		this.studentRepo = studentRepo;
		this.driveRepo = driveRepo;
	}

	public Map<String, Object> getDashboardData() {
		long totalStudents = studentRepo.count();
		long vaccinated = studentRepo.countByVaccinated(true);

		LocalDate today = LocalDate.now();
		LocalDate thirtyDaysLater = today.plusDays(30);
		List<VaccinationDrive> upcoming = driveRepo.findByDriveDateBetween(today, thirtyDaysLater);

		Map<String, Object> data = new HashMap<>();
		data.put("totalStudents", totalStudents);
		data.put("vaccinatedStudents", vaccinated);
		data.put("percentageVaccinated", totalStudents > 0 ? (vaccinated * 100 / totalStudents) : 0);
		data.put("upcomingDrives", upcoming);
		return data;
	}
}
