import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import NavBar from "../presets/NavBar";
import '../styles/addEvent.css'
import Footer from "../presets/Footer";
import Banner from "./Banner";

function EventForm() {
    const navigate = useNavigate();
    const [event, setEvent] = useState({
        name: '',
        location: '',
        time: '',
        additionalInfo: ''
    });

    // Function to generate the current date-time in the right format for the `min` attribute
    const getMinDateTime = () => {
        const now = new Date();
        const local = new Date(now.getTime() - now.getTimezoneOffset() * 60000);
        return local.toISOString().slice(0, 16); // "YYYY-MM-DDThh:mm" format
    };

    const handleChange = (e) => {
        setEvent({ ...event, [e.target.name]: e.target.value });
    };

    const handleSubmit = (e) => {
        e.preventDefault();

        // Make sure the event time is in the future before submitting
        const now = new Date();
        const eventTime = new Date(event.time);
        if (eventTime <= now) {
            alert('Please choose a future time for the event.');
            return;
        }

        axios.post('/api/events', event)
            .then(() => {
                navigate('/');  // Navigate to home or list page after successful creation
            })
            .catch(error => console.error('Error creating event:', error));
    };

    function handleBack() {
        navigate(-1);
    }

    return (
        <div className="main-container">
            <NavBar/>
            <Banner text="Add Event"/>
            <form onSubmit={handleSubmit} className="event-form">
                <div className="form-group">
                    <label htmlFor="name">Event Name:</label>
                    <input type="text" id="name" name="name" value={event.name} onChange={handleChange} required/>
                </div>
                <div className="form-group">
                    <label htmlFor="time">Time:</label>
                    <input type="datetime-local" id="time" name="time" value={event.time} min={getMinDateTime()}
                           onChange={handleChange} required/>
                </div>
                <div className="form-group">
                    <label htmlFor="location">Location:</label>
                    <input type="text" id="location" name="location" value={event.location} onChange={handleChange}
                           required/>
                </div>
                <div className="form-group">
                    <label htmlFor="additionalInfo">Additional Info:</label>
                    <textarea id="additionalInfo" name="additionalInfo" value={event.additionalInfo}
                              onChange={handleChange}></textarea>
                </div>
                <div className="form-actions">
                    <button type="button" onClick={handleBack}>Back</button>
                    <button type="submit">Submit</button>
                </div>
            </form>
            <Footer/>
        </div>

    );
}

export default EventForm;
