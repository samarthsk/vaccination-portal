import React, { useState, useEffect } from "react";
import { Route, Routes, Navigate } from "react-router-dom";
import Navbar from "./components/Navbar";
import Dashboard from "./components/Dashboard";
import Students from "./components/Students";
import UpdateStatus from "./components/UpdateStatus";
import VaccinationReport from "./components/VaccinationReport";
import VaccinationDriveBookingForm from "./pages/VaccinationDriveBookingForm";
import Login from "./components/Login";

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  useEffect(() => {
    const loggedIn = localStorage.getItem("isLoggedIn") === "true";
    setIsLoggedIn(loggedIn);
  }, []);

  const handleLogout = () => {
    localStorage.removeItem("isLoggedIn");
    setIsLoggedIn(false);
  };

  if (!isLoggedIn) {
    return <Login setIsLoggedIn={setIsLoggedIn} />;
  }

  return (
    <div>
      <Navbar handleLogout={handleLogout} />
      <div className="container">
        <Routes>
          <Route path="/" element={<Dashboard />} />
          <Route path="/students" element={<Students />} />
          <Route path="/update-status" element={<UpdateStatus />} />
          <Route path="/reports" element={<VaccinationReport />} />
          <Route path="/book-drive" element={<VaccinationDriveBookingForm />} />
          <Route path="*" element={<Navigate to="/" />} />
        </Routes>
      </div>
    </div>
  );
}

export default App;
