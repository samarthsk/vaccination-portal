import React, { useState, useEffect } from "react";
import axios from "axios";

const VaccinationReport = () => {
  const [reports, setReports] = useState([]);
  const [vaccineName, setVaccineName] = useState("");
  const [page, setPage] = useState(0);
  const [size, setSize] = useState(10);
  const [searchVaccineName, setSearchVaccineName] = useState("");

  const username = 'admin';
  const password = 'admin123';

  const fetchData = async (name) => {
    try {
      const queryParam = name
        ? `?vaccineName=${name}&page=${page}&size=${size}`
        : `?page=${page}&size=${size}`;
        
      const response = await axios.get(`http://localhost:8080/api/reports/filtered${queryParam}`, {
        auth: { 
          username,
          password, 
        }
      });
      setReports(response.data.content || response.data); 
    } catch (error) {
      console.error("Error fetching vaccination reports", error);
    }
  };

  useEffect(() => {
    fetchData(searchVaccineName);
  }, [searchVaccineName, page, size]);

  const handleSearch = () => {
    setSearchVaccineName(vaccineName);
    setPage(0);
  };

  const downloadReport = async (format) => {
    try {
      const queryParam = searchVaccineName ? `?vaccineName=${searchVaccineName}` : "";
      const response = await axios.get(
        `http://localhost:8080/api/reports/export/${format}${queryParam}`,
        {
          responseType: "blob",
          auth: { 
            username,
            password, 
          }
        }
      );

      const blob = new Blob([response.data], { type: response.headers["content-type"] });
      const link = document.createElement("a");
      const fileExtension = format === "excel" ? "xlsx" : format;
      link.href = URL.createObjectURL(blob);
      link.download = `vaccination_report.${fileExtension}`;
      link.click();
    } catch (error) {
      console.error(`Error downloading ${format} report`, error);
    }
  };

  return (
    <div>
      <h1>Vaccination Reports</h1>
      <div>
        <input
          type="text"
          placeholder="Search by Vaccine Name"
          value={vaccineName}
          onChange={(e) => setVaccineName(e.target.value)}
        />
        <button onClick={handleSearch}>Search</button>
      </div>

      <div>
        <button onClick={() => downloadReport("csv")}>Download CSV</button>
        <button onClick={() => downloadReport("excel")}>Download Excel</button>
        <button onClick={() => downloadReport("pdf")}>Download PDF</button>
      </div>

      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>Student Name</th>
            <th>Vaccination Status</th>
            <th>Vaccine Name</th>
            <th>Vaccination Date</th>
          </tr>
        </thead>
          <tbody>
            {reports && reports.length > 0 ? (
              reports.map((report) => (
                <tr key={report.id}>
                  <td>{report.id}</td>
                  <td>{report.studentName}</td>
                  <td>{report.vaccinated ? "Vaccinated" : "Not Vaccinated"}</td>
                  <td>{report.vaccineName}</td>
                  <td>{report.vaccinationDate}</td>
                </tr>
              ))
            ) : (
              <tr>
                <td colSpan="5">No reports available</td>
              </tr>
            )}
          </tbody>
      </table>

      <div>
        <button onClick={() => setPage(page - 1)} disabled={page <= 0}>
          Previous
        </button>
        <span> Page {page + 1} </span>
        <button onClick={() => setPage(page + 1)}>Next</button>
      </div>
    </div>
  );
};

export default VaccinationReport;
