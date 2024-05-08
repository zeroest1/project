import React, { useState, useEffect } from 'react';
import { useParams, useNavigate, Link } from 'react-router-dom';
import axios from 'axios';

function ParticipantDetails() {
    const { participantId } = useParams();
    const [participant, setParticipant] = useState({});
    const navigate = useNavigate();

    useEffect(() => {
        axios.get(`/api/participants/details/${participantId}`)
            .then(response => {
                setParticipant(response.data);
            })
            .catch(error => {
                console.error('Error fetching participant details:', error);
            });
    }, [participantId]);

    const handleChange = (e) => {
        setParticipant({ ...participant, [e.target.name]: e.target.value });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        axios.put(`/api/participants/update/${participantId}`, participant)
            .then(() => {
                alert('Participant updated successfully!');
                navigate(-1); // Navigate back to the previous page
            })
            .catch(error => {
                console.error('Error updating participant:', error);
            });
    };

    return (
        <div>
            <h2>Participant Details</h2>
            <form onSubmit={handleSubmit}>
                <label>
                    First Name:
                    <input type="text" name="firstName" value={participant.firstName || ''} onChange={handleChange} />
                </label>
                <label>
                    Last Name:
                    <input type="text" name="lastName" value={participant.lastName || ''} onChange={handleChange} />
                </label>
                <label>
                    Company Legal Name:
                    <input type="text" name="companyLegalName" value={participant.companyLegalName || ''} onChange={handleChange} />
                </label>
                <button type="submit">Update Participant</button>
            </form>
            <Link to="/">Back to Participants</Link>
        </div>
    );
}

export default ParticipantDetails;
