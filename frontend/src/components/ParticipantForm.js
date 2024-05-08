import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate, useParams } from 'react-router-dom';

function ParticipantForm() {
    const { eventId } = useParams();
    const navigate = useNavigate();
    const [participant, setParticipant] = useState({
        firstName: '',
        lastName: '',
        companyLegalName: '',
        registrationCode: '',
        participantCount: '',
        paymentMethod: '',
        additionalInfo: ''
    });

    const handleChange = (e) => {
        setParticipant({ ...participant, [e.target.name]: e.target.value });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        axios.post(`/api/events/${eventId}/participants`, participant)
            .then(() => {
                navigate(`/events/${eventId}/participants`);
            })
            .catch(error => console.error('Error adding participant:', error));
    };

    return (
        <form onSubmit={handleSubmit}>
            <h2>Add Participant</h2>
            <label>First Name:</label>
            <input type="text" name="firstName" value={participant.firstName} onChange={handleChange} />
            <label>Last Name:</label>
            <input type="text" name="lastName" value={participant.lastName} onChange={handleChange} />
            <label>Company Legal Name:</label>
            <input type="text" name="companyLegalName" value={participant.companyLegalName} onChange={handleChange} />
            <label>Registration Code:</label>
            <input type="text" name="registrationCode" value={participant.registrationCode} onChange={handleChange} />
            <label>Participant Count:</label>
            <input type="number" name="participantCount" value={participant.participantCount} onChange={handleChange} />
            <label>Payment Method:</label>
            <select name="paymentMethod" value={participant.paymentMethod} onChange={handleChange}>
                <option value="bankTransfer">Bank Transfer</option>
                <option value="cash">Cash</option>
            </select>
            <label>Additional Info:</label>
            <textarea name="additionalInfo" value={participant.additionalInfo} onChange={handleChange}></textarea>
            <button type="submit">Add Participant</button>
        </form>
    );
}

export default ParticipantForm;
