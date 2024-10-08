import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import IconButton from '@mui/material/IconButton';
import InputAdornment from '@mui/material/InputAdornment';
import Visibility from '@mui/icons-material/Visibility';
import VisibilityOff from '@mui/icons-material/VisibilityOff';

const EditProfile = () => {
  const { id } = useParams(); // Get the id parameter from the URL
  const [account, setAccount] = useState({
    accountNo: '',
    branchName: '',
    ifsc: ''
  });
  const [showPassword, setShowPassword] = useState(false);

  useEffect(() => {
    // Fetch account data based on the employee id
    const fetchAccountData = async () => {
      try {
        const response = await fetch(`http://localhost:8080/api/v3/payments/employee/${id}`);
        if (response.ok) {
          const data = await response.json();
          setAccount(data); // Assuming data structure matches the account state
        } else {
          console.error('Failed to fetch account data');
        }
      } catch (error) {
        console.error('Error:', error);
      }
    };

    fetchAccountData();
  }, [id]);

  const handleChange = (e) => {
    setAccount({ ...account, [e.target.name]: e.target.value });
  };

  const handleShowPassword = () => {
    setShowPassword((prevShowPassword) => !prevShowPassword);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await fetch(`http://localhost:8080/api/v3/payments/${id}`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(account),
      });
      if (response.ok) {
        console.log('Account updated successfully!');
      } else {
        console.error('Failed to update account');
      }
    } catch (error) {
      console.error('Error:', error);
    }
  };

  return (
    <Box sx={{ maxWidth: 400, mx: 'auto', mt: 4 }}>
      <Typography variant="h5" mb={2}>Edit Account</Typography>
      <form onSubmit={handleSubmit}>
        <TextField
          label="Account Number"
          name="accountNo"
          value={account.accountNo}
          onChange={handleChange}
          fullWidth
          margin="normal"
          required
        />
        <TextField
          label="Branch Name"
          name="branchName"
          value={account.branchName}
          onChange={handleChange}
          fullWidth
          margin="normal"
          required
        />
        <TextField
          label="IFSC"
          name="ifsc"
          value={account.ifsc}
          onChange={handleChange}
          fullWidth
          margin="normal"
          required
        />
        <Button type="submit" variant="contained" color="primary" sx={{ mt: 2 }}>Update</Button>
      </form>
    </Box>
  );
};

export default EditProfile;
