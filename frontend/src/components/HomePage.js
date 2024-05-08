import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';

function HomePage() {
    const [events, setEvents] = useState([]);

    useEffect(() => {
        axios.get('/api/events')
            .then(response => {
                setEvents(response.data);
            })
            .catch(error => console.error('Error fetching events:', error));
    }, []);

    return (
        <div>
            <h1>Events</h1>
            <Link to="/create-event">Create New Event</Link>
            <ul>
                {events.map(event => (
                    <li key={event.id}>
                        {event.name} - {event.time}
                        <Link to={`/events/${event.id}/participants`}>View Participants</Link>
                    </li>
                ))}
            </ul>
        </div>
    );
}

export default HomePage;
