import React from 'react';
import { Routes, Route } from 'react-router-dom';
import SignUp from './User/SignUp';

function App() {
    return (
        <div className="App">
            <Routes>
            <Route path="/signup" element={<SignUp />}></Route>
            </Routes>
        </div>
    );
}

export default App;