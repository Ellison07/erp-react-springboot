import React, { useState, useEffect } from 'react';
import { useLocation } from 'react-router-dom';
import { useParams } from 'react-router-dom';
import {
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Paper,
  Button,
} from '@mui/material';

export default function User() {
  const { id } = useParams();

  const location = useLocation();
  const [employeeDetails, setEmployeeDetails] = useState({});
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    console.log('Location state:', location.state);
    // const id = location.state?.id; // Safely access id using the optional chaining operator
    // console.log('Received ID:', id);

    if (id) {
      fetch(`http://localhost:8080/api/v1/employees/${id}`)
        .then((response) => response.json())
        .then((data) => {
          setEmployeeDetails(data);
          setLoading(false);
        })
        .catch((error) => {
          console.error('Error fetching employee details:', error);
          setLoading(false);
        });
    } else {
      // Handle the case when id is not available in location.state
      setLoading(false);
    }
  }, [location.state]);

  return (
    <div className="container">
      <div className="py-4">
        {loading ? (
          <p>Loading employee details...</p>
        ) : (
          <TableContainer component={Paper}>
            <Table>
              <TableHead>
                <TableRow>
                  <TableCell>Attribute</TableCell>
                  <TableCell>Value</TableCell>
                </TableRow>
              </TableHead>
              <TableBody>
                <TableRow>
                  <TableCell>ID</TableCell>
                  <TableCell>{employeeDetails.id}</TableCell>
                </TableRow>
                <TableRow>
                  <TableCell>First Name</TableCell>
                  <TableCell>{employeeDetails.firstName}</TableCell>
                </TableRow>
                <TableRow>
                  <TableCell>Last Name</TableCell>
                  <TableCell>{employeeDetails.lastName}</TableCell>
                </TableRow>
                <TableRow>
                  <TableCell>Email</TableCell>
                  <TableCell>{employeeDetails.emailId}</TableCell>
                </TableRow>
                <TableRow>
                  <TableCell>Username</TableCell>
                  <TableCell>{employeeDetails.userName}</TableCell>
                </TableRow>
                <TableRow>
                  <TableCell>Role</TableCell>
                  <TableCell>{employeeDetails.role}</TableCell>
                </TableRow>
                <TableRow>
                  <TableCell>Department</TableCell>
                  <TableCell>{employeeDetails.department}</TableCell>
                </TableRow>
              </TableBody>
            </Table>
          </TableContainer>
        )}
      </div>
    </div>
  );
}
