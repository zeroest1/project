import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Link, useParams } from 'react-router-dom';

function ParticipantList() {
    const { eventId } = useParams();
    const [participants, setParticipants] = useState([]);

    useEffect(() => {
        axios.get(`/api/events/${eventId}/participants`)
            .then(response => {
                setParticipants(response.data);
            })
            .catch(error => console.error('Error fetching participants:', error));
    }, [eventId]);

    const handleDelete = (participantId) => {
        axios.delete(`/api/participants/${participantId}`)
            .then(() => {
                setParticipants(participants.filter(p => p.id !== participantId));
            })
            .catch(error => console.error('Error deleting participant:', error));
    };

    return (
        <div>
            <h2>Participants</h2>
            <Link to={`/events/${eventId}/add-participant`}>Add Participant</Link>
            <ul>
                {participants.map(participant => (
                    <li key={participant.id}>
                        {participant.firstName} {participant.lastName} - {participant.companyLegalName}
                        <button onClick={() => handleDelete(participant.id)}>Delete</button>
                    </li>
                ))}
            </ul>
            <Link to="/">Back to Events</Link>
        </div>
    );
}

export default ParticipantList;
