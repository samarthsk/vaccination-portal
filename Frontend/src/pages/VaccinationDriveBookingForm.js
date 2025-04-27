import React, { useState, useEffect } from "react";
import axios from "axios";

const VaccinationDriveBookingForm = () => {
  const [formData, setFormData] = useState({ date: "", vaccineName: "", vaccinesAvailable: "" });
  const [drives, setDrives] = useState([]);
  const [editingId, setEditingId] = useState(null);

  const username = 'admin';
  const password = 'admin123';

  const fetchDrives = async () => {
    try {
      const res = await axios.get("http://localhost:8080/api/drive-bookings", {
        auth: {
          username,
          password,
        },
      });
      setDrives(res.data);
    } catch (error) {
      console.error("Error fetching drives:", error);
      alert("Error fetching drives. Please login if required.");
    }
  };

  useEffect(() => {
    fetchDrives();
  }, []);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      if (editingId) {
        await axios.put(`http://localhost:8080/api/drive-bookings/${editingId}`, formData, {
          auth: {
            username,
            password,
          },
        });
        alert("Drive updated successfully!");
        setEditingId(null);
      } else {
        await axios.post("http://localhost:8080/api/drive-bookings", formData, {
          auth: {
            username,
            password,
          },
        });
        alert("Drive booked successfully!");
      }
      setFormData({ date: "", vaccineName: "", vaccinesAvailable: "" });
      fetchDrives();
    } catch (err) {
      console.error("Error saving drive:", err);
      alert("Error saving drive. Please login if required.");
    }
  };

  const handleEdit = (drive) => {
    setFormData({
      date: drive.date,
      vaccineName: drive.vaccineName,
      vaccinesAvailable: drive.vaccinesAvailable,
    });
    setEditingId(drive.id);
  };

  const isPastDrive = (date) => new Date(date) < new Date();

  return (
    <div className="container mt-5">
      <h2>{editingId ? "Edit Vaccination Drive" : "Book a Vaccination Drive"}</h2>

      <form onSubmit={handleSubmit}>
        <div className="mb-3">
          <label>Date:</label>
          <input
            type="date"
            name="date"
            value={formData.date}
            onChange={handleChange}
            required
            className="form-control"
          />
        </div>

        <div className="mb-3">
          <label>Vaccine Name:</label>
          <input
            type="text"
            name="vaccineName"
            value={formData.vaccineName}
            onChange={handleChange}
            required
            className="form-control"
          />
        </div>

        <div className="mb-3">
          <label>Available Doses:</label>
          <input
            type="number"
            name="vaccinesAvailable"
            value={formData.vaccinesAvailable}
            onChange={handleChange}
            required
            className="form-control"
          />
        </div>

        <button type="submit" className="btn btn-primary">
          {editingId ? "Update Drive" : "Book Drive"}
        </button>
      </form>

      <h3 className="mt-5">Upcoming Drives</h3>

      {drives.length > 0 ? (
        <ol>
          {drives.map((drive) => (
            <li key={drive.id} className="border p-3 mt-3">
              <p><strong>Date:</strong> {new Date(drive.date).toLocaleDateString()}</p>
              <p><strong>Vaccine:</strong> {drive.vaccineName}</p>
              <p><strong>Doses:</strong> {drive.vaccinesAvailable}</p>
              <button
                disabled={isPastDrive(drive.date)}
                onClick={() => handleEdit(drive)}
                className="btn btn-secondary"
              >
                {isPastDrive(drive.date) ? "Drive Completed" : "Edit"}
              </button>
            </li>
          ))}
        </ol>
      ) : (
        <p>No upcoming drives.</p>
      )}
    </div>
  );
};

export default VaccinationDriveBookingForm;
