import React from "react";
import { Link } from "react-router-dom";

const Navbar = ({ handleLogout }) => {
  return (
    <nav>
      <ul>
        <li><Link to="/">Dashboard</Link></li>
        <li><Link to="/students">Students</Link></li>
        <li><Link to="/update-status">Update Status</Link></li>
        <li><Link to="/reports">Reports</Link></li>
        <li><Link to="/book-drive">Book Drive</Link></li>
        <li><button onClick={handleLogout}>Logout</button></li>
      </ul>
    </nav>
  );
};

export default Navbar;
