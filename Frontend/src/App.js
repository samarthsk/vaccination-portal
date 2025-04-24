import React from "react";
import { Route, Routes } from "react-router-dom";
import Navbar from "./components/Navbar";
import Dashboard from "./components/Dashboard";
import Students from "./components/Students";
import UpdateStatus from "./components/UpdateStatus";
import VaccinationReport from "./components/VaccinationReport";
import VaccinationDriveBookingForm from "./pages/VaccinationDriveBookingForm";

function App() {
  return (
    <div>
      <Navbar />
      <div className="container">
        <Routes>
          <Route path="/" element={<Dashboard />} />
          <Route path="/students" element={<Students />} />
          <Route path="/update-status" element={<UpdateStatus />} />
          <Route path="/reports" element={<VaccinationReport />} />
          <Route path="/book-drive" element={<VaccinationDriveBookingForm />} />
        </Routes>
      </div>
    </div>
  );
}

export default App;
