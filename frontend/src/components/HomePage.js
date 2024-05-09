import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';
import NavBar from "../presets/NavBar";
import '../styles/home.css'
import HomePageContent from '../presets/HomePageContent';
import Footer from "../presets/Footer";

function HomePage() {
    const [futureEvents, setFutureEvents] = useState([]);
    const [pastEvents, setPastEvents] = useState([]);

    function deleteEvent(eventId) {
        axios.delete(`/api/events/${eventId}`)
            .then(() => {
                const updatedEvents = futureEvents.filter(event => event.id !== eventId);
                setFutureEvents(updatedEvents);
            })
            .catch(error => {
                console.error('Error deleting event:', error);
                alert('Failed to delete event');
            });
    }

    useEffect(() => {
        axios.get('/api/events')
            .then(response => {
                const now = new Date();
                const future = [];
                const past = [];
                response.data.forEach(event => {
                    const eventDate = new Date(event.time);
                    if (eventDate > now) {
                        future.push(event);
                    } else {
                        past.push(event);
                    }
                });
                setFutureEvents(future);
                setPastEvents(past);
            })
            .catch(error => console.error('Error fetching events:', error));
    }, []);
    function formatDate(dateTimeString) {
        const date = new Date(dateTimeString);
        const day = String(date.getDate()).padStart(2, '0');
        const month = String(date.getMonth() + 1).padStart(2, '0');
        const year = date.getFullYear();
        return `${day}.${month}.${year}`;
    }
    return (
        <div className="main-container">
            <NavBar/>
            <HomePageContent/>
            <div className="events-container">
                <section className="events-section future-events">
                    <h2>Upcoming Events</h2>
                    <ul className="events-list">
                        {futureEvents.map(event => (
                            <li key={event.id} className="event-item">
                                <div className="event-info">
                                    <span className="event-name">{event.name}</span>
                                    <span className="event-date">{formatDate(event.time)}</span>
                                    <span className="participants-count">Participants: {event.participantCount}</span>
                                </div>
                                <div className="event-actions">
                                    <Link to={`/events/${event.id}/participants`} className="view-participants">View
                                        Participants</Link>
                                    <button onClick={() => deleteEvent(event.id)} className="delete-button">Delete
                                    </button>
                                </div>
                            </li>

                        ))}
                    </ul>
                </section>
                <section className="events-section past-events">
                    <h2>Past Events</h2>
                    <ul className="events-list">
                        {pastEvents.map(event => (
                            <li key={event.id} className="event-item">
                                <span className="event-name">{event.name}</span>
                                <span className="event-date">{formatDate(event.time)}</span>
                                <span className="participants-count">Participants: {event.participantCount}</span>
                                <Link to={`/events/${event.id}/participants`} className="view-participants">View
                                    Participants</Link>
                            </li>
                        ))}
                    </ul>
                </section>
            </div>
            <footer><Footer/></footer>
        </div>


    );

}

export default HomePage;
