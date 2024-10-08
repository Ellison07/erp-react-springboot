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
} from '@mui/material';

export default function HistoryUser() {
  const { id } = useParams();
  const location = useLocation();
  const [histories, setHistoryDetails] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    if (id) {
      fetch(`http://localhost:8080/api/v2/employees/${id}/histories`)
        .then((response) => response.json())
        .then((data) => {
          setHistoryDetails(data);
          setLoading(false);
        })
        .catch((error) => {
          console.error('Error fetching history details:', error);
          setLoading(false);
        });
    } else {
      setLoading(false);
    }
  }, [location.state, id]);

  return (
    <div>
      <div style={{ display: 'flex', justifyContent: 'center' }}>
        <TableContainer component={Paper}>
          <Table>
            <TableHead>
              <TableRow>
                <TableCell>Dates</TableCell>
                <TableCell>Leaves</TableCell>
                <TableCell>Rating</TableCell>
                <TableCell>Salary</TableCell>
                <TableCell>Salary Based on Performance</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {histories.map((history, index) => (
                <TableRow key={index}>
                  <TableCell>{history.dates}</TableCell>
                  <TableCell>{history.leaves === null ? 'null' : history.leaves}</TableCell>
                  <TableCell>{history.rating === null ? 'null' : history.rating}</TableCell>
                  <TableCell>{history.salary === null ? 'null' : history.salary}</TableCell>
                  <TableCell>{history.salaryBaseOnPerformance === null ? 'null' : history.salaryBaseOnPerformance}</TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>
      </div>
    </div>
  );
}
