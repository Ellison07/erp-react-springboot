import * as React from 'react';
import { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';

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

export default function Finance() {
  const navigate = useNavigate();
  const [employees, setEmployees] = useState([]);
   //const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetch('http://localhost:8080/api/v1/employees')
      .then((response) => response.json())
      .then((data) => {
        setEmployees(data);
        setLoading(false);
      })
      .catch((error) => {
        console.error('Error fetching employee data:', error);
        setLoading(false);
      });
  }, []);

  const deleteUser = (id) => {
    fetch(`http://localhost:8080/api/v1/employees/${id}`, {
      method: 'DELETE',
    })
      .then((response) => {
        if (response.status === 204) {
          // Employee deleted successfully
          // You can also update the employees state to remove the deleted employee
          window.location.reload();
          fetchEmployees();
        } else {
          console.error('Error deleting employee');
        }
      })
      .catch((error) => {
        console.error('Error deleting employee:', error);
      });
  };
  const viewUser = (id) => {
    navigate(`/user/${id}`)
  };
  const editUser = (id) => {
    navigate(`/editProfile/${id}`)
  };
  const addSalary = (id) => {
    navigate(`/addSalary/${id}`);
  };
  const  historyUser= (id) => {
    navigate(`/historyUser/${id}`);
  };
  
  return (
    <div className="container">
      <div className="py-4">
        
        <TableContainer component={Paper}>
          <Table>
            <TableHead>
              <TableRow>
                <TableCell>S.N</TableCell>
                <TableCell>First Name</TableCell>
                <TableCell>Last Name</TableCell>
                <TableCell>Email</TableCell>
                <TableCell>Role</TableCell>
                <TableCell>Department</TableCell>
                <TableCell>Action</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {employees.map((user, index) => (
                <TableRow key={user.id}>
                  <TableCell>{index + 1}</TableCell>
                  <TableCell>{user.firstName}</TableCell>
                  <TableCell>{user.lastName}</TableCell>
                  <TableCell>{user.emailId}</TableCell>
                  <TableCell>{user.role}</TableCell>
                  <TableCell>{user.department}</TableCell>
                  <TableCell>
                  <Button
                      variant="contained"
                      color="primary"
                      onClick={() => viewUser(user.id)}
                      size="small"
                    >
                      View
                    </Button>
                    <Button
                      variant="contained"
                      color="secondary"
                      onClick={() => editUser(user.id)}
                      size="small"
                    >
                      Edit
                    </Button>
                    <Button
                      variant="contained"
                      color="warning"
                      onClick={() => addSalary(user.id)}
                      size="small"
                    >
                      Add Salary
                    </Button>
                    <Button
                      variant="contained"
                      color="success"
                      onClick={() => historyUser(user.id)}
                      size="small"
                    >
                      History
                    </Button>
                    <Button
                      variant="contained"
                      color="error"
                      onClick={() => deleteUser(user.id)}
                      size="small"
                    >
                      Delete
                    </Button>
                  </TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>
      </div>
    </div>
  );
}
