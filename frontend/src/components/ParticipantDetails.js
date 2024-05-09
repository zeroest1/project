import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import axios from 'axios';
import {isValidEstonianPersonalId} from "../utils/validation";
import NavBar from "../presets/NavBar";
import Banner from "./Banner";
import Footer from "../presets/Footer";

function ParticipantDetails() {
    const { type, id: participantId } = useParams();
    const [participant, setParticipant] = useState({});
    const navigate = useNavigate();

    useEffect(() => {
        const endpoint = type === 'companies' ? `/api/participants/companies/details/${participantId}` : `/api/participants/individuals/details/${participantId}`;

        axios.get(endpoint)
            .then(response => {
                setParticipant(response.data);
            })
            .catch(error => {
                console.error('Error fetching participant details:', error);
                navigate('/');
            });
    }, [type, participantId, navigate]);

    const handleChange = (e) => {
        setParticipant({ ...participant, [e.target.name]: e.target.value });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        if (type === 'individuals' && !isValidEstonianPersonalId(participant.personalId)) {
            alert("Invalid Estonian Personal ID.");
            return;
        }
        const endpoint = type === 'companies' ? `/api/participants/companies/update/${participantId}` : `/api/participants/individuals/update/${participantId}`;
        axios.put(endpoint, participant)
            .then(() => {
                alert('Participant updated successfully!');
                navigate('/');
            })
            .catch(error => console.error('Error updating participant:', error));
    };

    function handleBack() {
        navigate(-1);
    }

    return (
        <div className='main-container'>
            <NavBar/>
            <Banner text="Add Event"/>
            <h2>Participant Details</h2>
            <form onSubmit={handleSubmit}>
                {type === 'individuals' && (
                    <>
                        <label>
                            First Name:<input type="text" name="firstName" value={participant.firstName || ''}
                                              onChange={handleChange}/>
                        </label>
                        <label>
                            Last Name:<input type="text" name="lastName" value={participant.lastName || ''}
                                             onChange={handleChange}/>
                        </label>
                        <label>
                            Personal ID:<input type="text" name="personalId" value={participant.personalId || ''}
                                               onChange={handleChange}/>
                        </label>
                    </>
                )}
                {type === 'companies' && (
                    <>
                        <label>
                            Company Legal Name:<input type="text" name="companyName"
                                                      value={participant.companyName || ''} onChange={handleChange}/>
                        </label>
                        <label>
                            Registration Code:<input type="text" name="companyRegistrationCode"
                                                     value={participant.companyRegistrationCode || ''}
                                                     onChange={handleChange}/>
                        </label>
                        <label>
                            Number of Participants:<input type="number" name="numberOfParticipants"
                                                          value={participant.numberOfParticipants || ''} min={1}
                                                          onChange={handleChange}/>
                        </label>
                    </>
                )}
                <label>
                    Payment Method:
                    <select name="paymentMethod" value={participant.paymentMethod || ''} onChange={handleChange}>
                        <option value="cash">Cash</option>
                        <option value="bankTransfer">Bank Transfer</option>
                    </select>
                </label>
                <label>
                    Additional Info:<textarea name="additionalInfo" value={participant.additionalInfo || ''}
                                              onChange={handleChange}></textarea>
                </label>
                <button type="submit">Update Participant</button>
                <div className="form-actions">
                    <button type="button" onClick={handleBack}>Back</button>
                </div>
            </form>
            <Footer/>
        </div>
    );
}

export default ParticipantDetails;
