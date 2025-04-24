import React, { useEffect, useState } from "react";
import axios from "axios";

const Dashboard = () => {
  const [dashboardData, setDashboardData] = useState({});
  const [upcomingDrives, setUpcomingDrives] = useState([]);
  const [error, setError] = useState("");

  const fetchDashboardData = () => {
    // Fetch data for dashboard overview
    axios
      .get("http://localhost:8080/api/dashboard/data")
      .then((response) => {
        setDashboardData(response.data);
      })
      .catch((error) => {
        setError("Error loading dashboard data.");
        console.error("Error fetching dashboard: ", error);
      });

    // Fetch upcoming vaccination drive bookings
    axios
      .get("http://localhost:8080/api/drive-bookings")
      .then((response) => {
        setUpcomingDrives(response.data); // Store drive bookings
      })
      .catch((error) => {
        setError("Error loading upcoming vaccination drives.");
        console.error("Error fetching vaccination drives: ", error);
      });
  };

  useEffect(() => {
    fetchDashboardData();
  }, []);

  const percentageVaccinated = dashboardData.percentageVaccinated || 0;
  const totalStudents = dashboardData.totalStudents || 0;
  const vaccinatedStudents = dashboardData.vaccinatedStudents || 0;

  return (
    <div style={{ padding: "20px" }}>
      <h1>Dashboard</h1>

      {error && <p style={{ color: "red" }}>{error}</p>}

      {totalStudents > 0 ? (
        <div>
          <h2>Vaccination Overview</h2>
          <p><strong>Total Students:</strong> {totalStudents}</p>
          <p><strong>Vaccinated Students:</strong> {vaccinatedStudents}</p>
          <p><strong>Percentage Vaccinated:</strong> {percentageVaccinated}%</p>

          <h2>Upcoming Vaccination Drives</h2>
          {upcomingDrives.length > 0 ? (
            <ol>
              {upcomingDrives.map((drive, index) => (
                <li key={index} style={{ marginBottom: "20px" }}>
                  <p><strong>Vaccine Name:</strong> {drive.vaccineName}</p>
                  <p><strong>Drive Date:</strong> {new Date(drive.date).toLocaleDateString()}</p>
                  <p><strong>Available Doses:</strong> {drive.vaccinesAvailable}</p>
                </li>
              ))}
            </ol>
          ) : (
            <p>No upcoming vaccination drives.</p>
          )}
        </div>
      ) : (
        <p>{totalStudents === 0 ? "No data available." : "Loading..."}</p>
      )}
    </div>
  );
};

export default Dashboard;
