import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate, useParams } from 'react-router-dom';

function EventForm() {
    const navigate = useNavigate();
    const [event, setEvent] = useState({
        name: '',
        location: '',
        time: '',
        additionalInfo: ''
    });

    const handleChange = (e) => {
        setEvent({ ...event, [e.target.name]: e.target.value });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        axios.post('/api/events', event)
            .then(() => {
                navigate('/');
            })
            .catch(error => console.error('Error creating event:', error));
    };

    return (
        <form onSubmit={handleSubmit}>
            <h2>Create Event</h2>
            <label>Name:</label>
            <input type="text" name="name" value={event.name} onChange={handleChange} />
            <label>Location:</label>
            <input type="text" name="location" value={event.location} onChange={handleChange} />
            <label>Time:</label>
            <input type="datetime-local" name="time" value={event.time} onChange={handleChange} />
            <label>Additional Information:</label>
            <textarea name="additionalInfo" value={event.additionalInfo} onChange={handleChange}></textarea>
            <button type="submit">Submit</button>
        </form>
    );
}

export default EventForm;
