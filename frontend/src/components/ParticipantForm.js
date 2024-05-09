import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate, useParams } from 'react-router-dom';
import { isValidEstonianPersonalId } from '../utils/validation';
import NavBar from "../presets/NavBar";
import Banner from "../presets/Banner";
import Footer from "../presets/Footer";
import '../styles/AddParticipants.css';

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
            .then(() => navigate(`/`))
            .catch(error => console.error('Error adding participant:', error));
    };

    function handleBack() {
        navigate(-1);
    }

    return (
        <div className="main-container">
            <NavBar/>
            <Banner text="Add Participant"/>
            <div className="form-container">
                <form onSubmit={handleSubmit} className="participant-form">
                    <div className="form-section">
                        <label>Participant Type:</label>
                        <select name="type" value={participantType} onChange={handleTypeChange}
                                className="form-control">
                            <option value="individual">Individual</option>
                            <option value="company">Company</option>
                        </select>

                        {participantType === 'individual' ? (
                            <>
                                <input type="text" placeholder="Eesnimi" name="firstName"
                                       value={participantData.firstName} onChange={handleChange} required/>
                                <input type="text" placeholder="Perenimi" name="lastName"
                                       value={participantData.lastName} onChange={handleChange} required/>
                                <input type="text" placeholder="Isikukood" name="personalId"
                                       value={participantData.personalId} onChange={handleChange} required/>
                            </>
                        ) : (
                            <>
                                <input type="text" placeholder="Company Name" name="companyName"
                                       value={participantData.companyName} onChange={handleChange} required/>
                                <input type="text" placeholder="Registration Code" name="companyRegistrationCode"
                                       value={participantData.companyRegistrationCode} onChange={handleChange}
                                       required/>
                                <input type="number" placeholder="Number of Participants" name="numberOfParticipants"
                                       value={participantData.numberOfParticipants} onChange={handleChange} min={1}
                                       required/>
                            </>
                        )}

                        <select name="paymentMethod" value={participantData.paymentMethod} onChange={handleChange}
                                required>
                            <option value="">Select a payment method</option>
                            <option value="bankTransfer">Bank Transfer</option>
                            <option value="cash">Cash</option>
                        </select>
                        <textarea placeholder="Additional Info" name="additionalInfo"
                                  value={participantData.additionalInfo} onChange={handleChange}></textarea>
                        <button type="submit">Save</button>
                        <div className="form-actions">
                            <button type="button" onClick={handleBack}>Back</button>
                        </div>
                    </div>
                </form>
            </div>
            <Footer/>
        </div>
    );
}

export default ParticipantForm;
