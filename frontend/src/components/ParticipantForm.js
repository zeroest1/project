import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate, useParams } from 'react-router-dom';
import { isValidEstonianPersonalId } from '../utils/validation';

function ParticipantForm() {
    const { eventId } = useParams();
    const navigate = useNavigate();
    const [participantType, setParticipantType] = useState('individual'); // Default to 'individual'
    const [participantData, setParticipantData] = useState({
        firstName: '',
        lastName: '',
        personalId: '',
        companyName: '',
        companyRegistrationCode: '',
        numberOfParticipants: '',
        paymentMethod: '',
        additionalInfo: ''
    });

    const handleChange = (e) => {
        setParticipantData({ ...participantData, [e.target.name]: e.target.value });
    };

    const handleTypeChange = (e) => {
        setParticipantType(e.target.value);
        setParticipantData({ // Resetting fields to ensure clean form
            firstName: '',
            lastName: '',
            personalId: '',
            companyName: '',
            companyRegistrationCode: '',
            numberOfParticipants: '',
            paymentMethod: participantData.paymentMethod, // Retain payment method if needed
            additionalInfo: ''
        });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        // Check if the participant type is individual and validate the Personal ID
        if (participantType === 'individual' && !isValidEstonianPersonalId(participantData.personalId)) {
            alert("Invalid Estonian Personal ID.");
            return;
        }
        const endpoint = participantType === 'individual' ? 'individuals' : 'companies';
        const payload = participantType === 'individual' ? {
            firstName: participantData.firstName,
            lastName: participantData.lastName,
            personalId: participantData.personalId,
            paymentMethod: participantData.paymentMethod,
            additionalInfo: participantData.additionalInfo
        } : {
            companyName: participantData.companyName,
            companyRegistrationCode: participantData.companyRegistrationCode,
            numberOfParticipants: participantData.numberOfParticipants,
            paymentMethod: participantData.paymentMethod,
            additionalInfo: participantData.additionalInfo
        };

        axios.post(`/api/participants/${endpoint}/${eventId}`, payload)
            .then(() => navigate(`/events/${eventId}/participants`))
            .catch(error => console.error('Error adding participant:', error));
    };

    return (
        <form onSubmit={handleSubmit}>
            <h2>Add Participant</h2>
            <label>Participant Type:</label>
            <select name="type" value={participantType} onChange={handleTypeChange}>
                <option value="individual">Individual</option>
                <option value="company">Company</option>
            </select>

            {participantType === 'individual' ? (
                <>
                    <label>First Name:</label>
                    <input type="text" name="firstName" value={participantData.firstName} onChange={handleChange}
                           required={true}/>
                    <label>Last Name:</label>
                    <input type="text" name="lastName" value={participantData.lastName} onChange={handleChange}
                           required={true}/>
                    <label>Personal ID:</label>
                    <input type="text" name="personalId" value={participantData.personalId} onChange={handleChange}
                           required={true}/>
                </>
            ) : (
                <>
                    <label>Company Name:</label>
                    <input type="text" name="companyName" value={participantData.companyName} onChange={handleChange}
                           required={true}/>
                    <label>Registration Code:</label>
                    <input type="text" name="companyRegistrationCode" value={participantData.companyRegistrationCode}
                           onChange={handleChange} required={true}/>
                    <label>Number of Participants:</label>
                    <input type="number" name="numberOfParticipants" value={participantData.numberOfParticipants}
                           onChange={handleChange} required={true}/>
                </>
            )}
            <label>Payment Method:</label>
            <select name="paymentMethod" value={participantData.paymentMethod} onChange={handleChange} required>
                <option value="">Select a payment method</option>
                <option value="bankTransfer">Bank Transfer</option>
                <option value="cash">Cash</option>
            </select>
            <label>Additional Info:</label>
            <textarea name="additionalInfo" value={participantData.additionalInfo} onChange={handleChange}></textarea>
            <button type="submit">Add Participant</button>
        </form>
    );
}

export default ParticipantForm;
