import React from "react";
import { Link } from "react-router-dom";

const Navbar = () => {
  return (
    <nav>
      <h2>School Vaccination Portal</h2>
      <ul>
        <li><Link to="/">Dashboard</Link></li>
        <li><Link to="/students">Add/Manage Students</Link></li>
        <li><Link to="/update-status">Update Vaccination Status</Link></li>
        <li><Link to="/reports">Generate Reports</Link></li>
        <li><Link to="/book-drive">Book Drive</Link></li>
      </ul>
    </nav>
  );
};

export default Navbar;
