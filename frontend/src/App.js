import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import HomePage from './components/HomePage';
import EventForm from './components/EventForm';
import ParticipantList from './components/ParticipantList';
import ParticipantForm from './components/ParticipantForm';
import ParticipantDetails from './components/ParticipantDetails'

function App() {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<HomePage />} />
                <Route path="/create-event" element={<EventForm />} />
                <Route path="/events/:eventId/participants" element={<ParticipantList />} />
                <Route path="/events/:eventId/add-participant" element={<ParticipantForm />} />
                <Route path="/participants/:type/:id" element={<ParticipantDetails />} />
            </Routes>
        </Router>
    );
}

export default App;
