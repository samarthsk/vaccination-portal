import React, { useState } from "react";
import axios from "axios";

const UpdateStatus = () => {
  const [studentId, setStudentId] = useState("");
  const [vaccinated, setVaccinated] = useState(null);
  const [statusEditable, setStatusEditable] = useState(false);
  const [message, setMessage] = useState("");

  const handleSearch = async () => {
    setMessage("");
    try {
      const response = await axios.get(
        `http://localhost:8080/api/students/find?studentId=${studentId}`
      );
      setVaccinated(response.data.vaccinated);
      setStatusEditable(true);
    } catch (error) {
      setMessage("Student not found.");
      setVaccinated(null);
      setStatusEditable(false);
    }
  };

  const handleUpdate = async (e) => {
    e.preventDefault();
    try {
      await axios.put(`http://localhost:8080/api/students/update-status`, null, {
        params: {
          studentId: studentId,
          vaccinated: vaccinated,
        },
      });
      setMessage("Vaccination status updated successfully.");
    } catch (error) {
      setMessage("Error updating status.");
    }
  };

  return (
    <div>
      <h2>Update Vaccination Status</h2>
      <form onSubmit={handleUpdate}>
        <label>
          Student ID:
          <input
            type="text"
            value={studentId}
            onChange={(e) => setStudentId(e.target.value)}
            required
          />
          <button type="button" onClick={handleSearch} style={{ marginLeft: "10px" }}>
            Search
          </button>
        </label>

        {vaccinated !== null && (
          <>
            <div style={{ marginTop: "20px" }}>
              <label>
                Vaccination Status:
                <select
                  value={vaccinated ? "vaccinated" : "not_vaccinated"}
                  onChange={(e) =>
                    setVaccinated(e.target.value === "vaccinated")
                  }
                  disabled={!statusEditable}
                  style={{ marginLeft: "10px" }}
                >
                  <option value="vaccinated">Vaccinated</option>
                  <option value="not_vaccinated">Not Vaccinated</option>
                </select>
              </label>
              <button
                type="submit"
                style={{ marginLeft: "10px" }}
                disabled={!statusEditable}
              >
                Update Status
              </button>
            </div>
          </>
        )}

        {message && (
          <p style={{ marginTop: "20px", color: "blue" }}>{message}</p>
        )}
      </form>
    </div>
  );
};

export default UpdateStatus;
