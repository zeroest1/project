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

    return (
        <form onSubmit={handleSubmit}>
            <h2>Create Event</h2>
            <label>Name:</label>
            <input type="text" name="name" value={event.name} onChange={handleChange} required={true} />
            <label>Location:</label>
            <input type="text" name="location" value={event.location} onChange={handleChange} required={true}/>
            <label>Time:</label>
            <input type="datetime-local" name="time" value={event.time} min={getMinDateTime()} onChange={handleChange} required={true}/>
            <label>Additional Information:</label>
            <textarea name="additionalInfo" value={event.additionalInfo} onChange={handleChange}></textarea>
            <button type="submit">Submit</button>
        </form>
    );
}

export default EventForm;
