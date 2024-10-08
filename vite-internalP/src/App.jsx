import React from 'react';
import { BrowserRouter ,Routes, Route } from 'react-router-dom';
import Employee from './components/employee/Employee';
import Finance from './components/finance/Finance';
import Home from './components/home/Home';
import User from './components/user/User';
import Admin from './components/admin/Admin';
import SignIn from './components/signIn/SignIn';
import SignUp from './components/signUp/SignUp';
import AddSalary from './components/finance/financeFunctions/AddSalary';
import EditUser from './components/finance/financeFunctions/EditUser';
import HistoryUser from './components/finance/financeFunctions/HistoryUser';
import useAuth from './hooks/useAuth';
import EditProfile from './components/finance/financeFunctions/EditProfile';
import Payment from './components/finance/financeFunctions/Payment';
function App() {
  const isLogin =useAuth();
  return (
    <>
    
    <BrowserRouter>
  <Routes>
    <Route path='/' element={<Home/>}/>
    <Route path='/admin/:id' element={<Admin/>}/>
    <Route path='/user/:id' element={<User />} />
    <Route path='/employee' element={<Employee/>}/>
    <Route path='/finance' element={<Finance/>}/>
    <Route path='/signIn' element={<SignIn/>}/>
    <Route path='/signUp' element={<SignUp/>}/>
    <Route path='/addSalary/:id' element={<AddSalary/>}/>
    <Route path='/historyUser/:id' element={<HistoryUser/>}/>
    <Route path='/editUser/:id'element={<EditUser/>} />
    <Route path='/editProfile/:id'element={<EditProfile/>} />
    <Route path='/payment/:id'element={<Payment/>} />
  </Routes>
    </BrowserRouter>
    </>
  );
}

export default App
