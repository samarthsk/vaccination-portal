import React, { useState } from "react";
import axios from "axios";

const Students = () => {
  const [student, setStudent] = useState({
    name: "",
    studentId: "",
    studentClass: "",
    vaccinated: false,
  });

  const [searchId, setSearchId] = useState("");
  const [isEditMode, setIsEditMode] = useState(false);
  const [file, setFile] = useState(null);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setStudent((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleCheckboxChange = () => {
    setStudent((prev) => ({
      ...prev,
      vaccinated: !prev.vaccinated,
    }));
  };

  const handleSearch = () => {
    axios
      .get(`http://localhost:8080/api/students/find?studentId=${searchId}`)
      .then((response) => {
        setStudent(response.data);
        setIsEditMode(true);
      })
      .catch(() => {
        alert("Student not found");
      });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (isEditMode) {
      axios
        .put("http://localhost:8080/api/students/update-details", student)
        .then(() => {
          alert("Student updated successfully");
          setIsEditMode(false);
          setStudent({ name: "", studentId: "", studentClass: "", vaccinated: false });
          setSearchId("");
        })
        .catch((error) => {
          console.error("Error updating student:", error);
        });
    } else {
      axios
        .post("http://localhost:8080/api/students", student)
        .then(() => {
          alert("Student added successfully");
          setStudent({ name: "", studentId: "", studentClass: "", vaccinated: false });
        })
        .catch((error) => {
          console.error("Error adding student:", error);
        });
    }
  };

  const handleBulkUpload = (e) => {
    e.preventDefault();
    const formData = new FormData();
    formData.append("file", file);

    axios
      .post("http://localhost:8080/api/students/bulk-upload", formData)
      .then(() => {
        alert("Students uploaded successfully");
      })
      .catch((error) => {
        console.error("Error uploading file:", error);
      });
  };

  return (
    <div>
      <h1>Add/Manage Students</h1>

      <div>
        <h3>Search Student by ID to Edit</h3>
        <input
          type="text"
          placeholder="Enter Student ID"
          value={searchId}
          onChange={(e) => setSearchId(e.target.value)}
        />
        <button onClick={handleSearch}>Search</button>
      </div>

      <form onSubmit={handleSubmit}>
        <label>
          Student Name:
          <input
            type="text"
            name="name"
            value={student.name}
            onChange={handleChange}
            required
          />
        </label>
        <br />
        <label>
          Student ID:
          <input
            type="text"
            name="studentId"
            value={student.studentId}
            onChange={handleChange}
            required
            disabled={isEditMode}
          />
        </label>
        <br />
        <label>
          Student Class:
          <input
            type="text"
            name="studentClass"
            value={student.studentClass}
            onChange={handleChange}
            required
          />
        </label>
        <br />
        <label>
          Vaccinated:
          <input
            type="checkbox"
            name="vaccinated"
            checked={student.vaccinated}
            onChange={handleCheckboxChange}
          />
        </label>
        <br />
        <button type="submit">{isEditMode ? "Update Student" : "Add Student"}</button>
      </form>

      <hr />

      <form onSubmit={handleBulkUpload}>
        <label>
          Bulk Upload (CSV):
          <input type="file" onChange={(e) => setFile(e.target.files[0])} />
        </label>
        <br />
        <button type="submit">Upload</button>
      </form>
    </div>
  );
};

export default Students;
