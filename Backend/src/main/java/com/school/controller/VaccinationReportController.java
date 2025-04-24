package com.school.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Stream;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.school.entity.VaccinationRecord;
import com.school.service.VaccinationReportService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/reports")
public class VaccinationReportController {

	@Autowired
	private VaccinationReportService reportService;

	@GetMapping("/all")
	public ResponseEntity<List<VaccinationRecord>> getAllRecords() {
		return ResponseEntity.ok(reportService.getAllRecords());
	}

	@GetMapping("/filtered")
	public ResponseEntity<Page<VaccinationRecord>> getFilteredReports(
			@RequestParam(required = false) String vaccineName, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		Pageable pageable = PageRequest.of(page, size);
		return ResponseEntity.ok(reportService.getFilteredRecords(vaccineName, pageable));
	}

	@GetMapping("/export/csv")
	public void exportCsv(HttpServletResponse response, @RequestParam(required = false) String vaccineName)
			throws IOException {
		response.setContentType("text/csv");
		String fileName = "vaccination_report.csv";
		response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);

		List<VaccinationRecord> records;
		if (vaccineName != null && !vaccineName.isEmpty()) {
			records = reportService.getRecordsByVaccineName(vaccineName);
		} else {
			records = reportService.getAllRecords();
		}

		PrintWriter writer = response.getWriter();
		writer.println("ID,Student Name,Vaccine Name,Date,Administered By");

		for (VaccinationRecord record : records) {
			writer.printf("%d,%s,%s,%s,%s\n", record.getId(), record.getStudentName(), record.getVaccineName(),
					record.getVaccinationDate(), record.getAdministeredBy());
		}
		writer.flush();
	}

	@GetMapping("/export/excel")
	public void exportExcel(HttpServletResponse response, @RequestParam(required = false) String vaccineName)
			throws IOException {
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		String fileName = "vaccination_report.xlsx";
		response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);

		List<VaccinationRecord> records = (vaccineName != null && !vaccineName.isEmpty())
				? reportService.getRecordsByVaccineName(vaccineName)
				: reportService.getAllRecords();

		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("Vaccinations");

		Row header = sheet.createRow(0);
		String[] columns = { "ID", "Student Name", "Vaccine Name", "Vaccination Date", "Administered By" };

		for (int i = 0; i < columns.length; i++) {
			header.createCell(i).setCellValue(columns[i]);
		}

		int rowNum = 1;
		for (VaccinationRecord record : records) {
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(record.getId());
			row.createCell(1).setCellValue(record.getStudentName());
			row.createCell(2).setCellValue(record.getVaccineName());
			row.createCell(3).setCellValue(record.getVaccinationDate().toString());
			row.createCell(4).setCellValue(record.getAdministeredBy());
		}

		workbook.write(response.getOutputStream());
		workbook.close();
	}

	@GetMapping("/export/pdf")
	public void exportPdf(HttpServletResponse response, @RequestParam(required = false) String vaccineName)
			throws Exception {
		response.setContentType("application/pdf");
		String fileName = "vaccination_report.pdf";
		response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);

		List<VaccinationRecord> records = (vaccineName != null && !vaccineName.isEmpty())
				? reportService.getRecordsByVaccineName(vaccineName)
				: reportService.getAllRecords();

		Document document = new Document();
		PdfWriter.getInstance(document, response.getOutputStream());
		document.open();

		Font titleFont = new Font(Font.HELVETICA, 16, Font.BOLD);
		Paragraph title = new Paragraph("Vaccination Report", titleFont);
		title.setAlignment(Element.ALIGN_CENTER);
		document.add(title);
		document.add(new Paragraph(" "));

		PdfPTable table = new PdfPTable(5);
		table.setWidthPercentage(100);
		Stream.of("ID", "Student Name", "Vaccine Name", "Date", "Administered By").forEach(header -> {
			PdfPCell cell = new PdfPCell();
			cell.setPhrase(new Phrase(header));
			table.addCell(cell);
		});

		for (VaccinationRecord record : records) {
			table.addCell(record.getId().toString());
			table.addCell(record.getStudentName());
			table.addCell(record.getVaccineName());
			table.addCell(record.getVaccinationDate().toString());
			table.addCell(record.getAdministeredBy());
		}

		document.add(table);
		document.close();
	}

}
