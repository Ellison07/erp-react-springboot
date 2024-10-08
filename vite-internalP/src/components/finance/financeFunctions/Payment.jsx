import * as React from 'react';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import { useParams } from 'react-router-dom';
import { useState, useEffect } from 'react';

const defaultTheme = createTheme();

export default function Payment() {
  const { id } = useParams();

  const [employeeDetails, setEmployeeDetails] = useState({});
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    if (id) {
      fetchEmployeeDetails(id);
    } else {
      setLoading(false);
    }
  }, [id]);

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

  const handleSubmit = async (event) => {
    event.preventDefault();
    const data = new FormData(event.currentTarget);
    const historyData = {
      dates: data.get('dates'),
      leaves: data.get('leaves'),
      rating: data.get('rating'),
      salary: data.get('salary'),
      salaryBaseOnPerformance: data.get('salaryBaseOnPerformance')
    };

    try {
      const response = await fetch(`http://localhost:8080/api/v2/employees/${id}/histories`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(historyData),
      });

      if (response.ok) {
        console.log('History added successfully!');
        // Redirect or display success message as needed
      } else {
        console.error('Failed to add history.');
      }
    } catch (error) {
      console.error('Error:', error);
    }
  };

  return (
    <ThemeProvider theme={defaultTheme}>
      <Container component="main" maxWidth="xs">
        <Box
          sx={{
            marginTop: 8,
            marginBottom: 4,
            display: 'flex',
            flexDirection: 'column',
            alignItems: 'center',
          }}
        >
          <Typography component="h1" variant="h5">
            Payment of {employeeDetails.firstName} {employeeDetails.lastName}
          </Typography>
          <Box component="form" noValidate onSubmit={handleSubmit} sx={{ mt: 3 }}>
            <Grid container spacing={2}>
              <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  id="dates"
                  label="Date"
                  name="dates"
                  type="date"
                  InputLabelProps={{ shrink: true }}
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  id="leaves"
                  label="Leaves"
                  name="leaves"
                  type="number"
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  id="rating"
                  label="Rating"
                  name="rating"
                  type="number"
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  id="salary"
                  label="Salary"
                  name="salary"
                  type="number"
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  id="salaryBaseOnPerformance"
                  label="Salary Base On Performance"
                  name="salaryBaseOnPerformance"
                  type="number"
                />
              </Grid>
            </Grid>
            <Button
              type="submit"
              fullWidth
              variant="contained"
              sx={{ mt: 3, mb: 2 }}
            >
              Payment
            </Button>
          </Box>
        </Box>
      </Container>
    </ThemeProvider>
  );
}
