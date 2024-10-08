import React, { useState, useEffect } from 'react';
import { Navigate, useLocation } from 'react-router-dom';
import { useParams } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';
import {
  Typography,
  TextField,
  Button,
  Paper,
  Grid,
  Container,
} from '@mui/material';

function AddSalary() {
  const navigate = useNavigate();
  const [payment, setPayment] = useState({
    accountNo: '',
    branchName: '',
    ifsc: '',
  });
  const { id } = useParams();
  const location = useLocation();
  const [employee, setEmployeeDetails] = useState({});
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    if (id) {
      fetchEmployeeDetails(id);
      fetchPaymentDetails(id);
    } else {
      setLoading(false);
    }
  }, [location.state]);

  const fetchEmployeeDetails = async (employeeId) => {
    try {
      const response = await fetch(`http://localhost:8080/api/v1/employees/${employeeId}`);
      if (response.ok) {
        const data = await response.json();
        setEmployeeDetails(data);
      } else {
        console.error('Failed to fetch employee details');
      }
    } catch (error) {
      console.error('Error:', error);
    }
  };

  const fetchPaymentDetails = async (employeeId) => {
    try {
      const response = await fetch(`http://localhost:8080/api/v3/payments/employee/${employeeId}`);
      if (response.ok) {
        const data = await response.json();
        setPayment(data);
      } else {
        console.error('Failed to fetch payment details');
      }
    } catch (error) {
      console.error('Error:', error);
    } finally {
      setLoading(false);
    }
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    navigate(`/payment/${id}`);
  };

  return (
    <Container maxWidth="sm">
      <Paper elevation={3} style={{ padding: '20px' }}>
        <Typography variant="h4" align="center" gutterBottom>
          Add Salary Payment
        </Typography>

        {/* Employee Details */}
        <Typography variant="h5" gutterBottom>
          Employee Details
        </Typography>
        <Grid container spacing={2}>
          <Grid item xs={12}>
            <Typography>
              <strong>Name:</strong> {employee.firstName} {employee.lastName}
            </Typography>
          </Grid>
          <Grid item xs={12}>
            <Typography>
              <strong>Email:</strong> {employee.emailId}
            </Typography>
          </Grid>
          <Grid item xs={12}>
            <Typography>
              <strong>Role:</strong> {employee.role}
            </Typography>
          </Grid>
          <Grid item xs={12}>
            <Typography>
              <strong>Department:</strong> {employee.department}
            </Typography>
          </Grid>
        </Grid>

        {/* Payment Details Form */}
        <form onSubmit={handleSubmit}>
          <Typography variant="h5" gutterBottom>
            Payment Details
          </Typography>
          <TextField
            label="Account Number"
            type="text"
            value={payment.accountNo}
            onChange={(e) => setPayment({ ...payment, accountNo: e.target.value })}
            name="accountNo"
            variant="outlined"
            fullWidth
            margin="normal"
            required
          />
          <TextField
            label="Branch Name"
            type="text"
            value={payment.branchName}
            onChange={(e) => setPayment({ ...payment, branchName: e.target.value })}
            name="branchName"
            variant="outlined"
            fullWidth
            margin="normal"
            required
          />
          <TextField
            label="IFSC Code"
            type="text"
            value={payment.ifsc}
            onChange={(e) => setPayment({ ...payment, ifsc: e.target.value })}
            name="ifsc"
            variant="outlined"
            fullWidth
            margin="normal"
            required
          />
          <Button type="submit" variant="contained" color="primary">
            Confirm
          </Button>
        </form>
      </Paper>
    </Container>
  );
}

export default AddSalary;
