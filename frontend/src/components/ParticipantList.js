import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useParams, useNavigate } from 'react-router-dom';
import NavBar from "../presets/NavBar";
import Banner from "./Banner";
import Footer from "../presets/Footer";
import '../styles/participantList.css';

function ParticipantList() {
    const { eventId } = useParams();
    const [eventDetails, setEventDetails] = useState({});
    const [individuals, setIndividuals] = useState([]);
    const [companies, setCompanies] = useState([]);
    const navigate = useNavigate(); // Hook to programmatically navigate
    useEffect(() => {
        axios.get(`/api/events/${eventId}`)
            .then(response => {
                setEventDetails(response.data);
            })
            .catch(error => console.error('Error fetching event details:', error));
    }, [eventId]);
    // Fetch individuals
    useEffect(() => {
        axios.get(`/api/participants/individuals/${eventId}`)
            .then(response => {
                setIndividuals(response.data);
            })
            .catch(error => console.error('Error fetching individuals:', error));
    }, [eventId]);

    // Fetch companies
    useEffect(() => {
        axios.get(`/api/participants/companies/${eventId}`)
            .then(response => {
                setCompanies(response.data);
            })
            .catch(error => console.error('Error fetching companies:', error));
    }, [eventId]);

    const handleDeleteIndividual = (id) => {
        axios.delete(`/api/participants/individuals/${id}`)
            .then(() => {
                setIndividuals(individuals.filter(ind => ind.id !== id));
            })
            .catch(error => console.error('Error deleting individual:', error));
    };

    const handleDeleteCompany = (id) => {
        axios.delete(`/api/participants/companies/${id}`)
            .then(() => {
                setCompanies(companies.filter(comp => comp.id !== id));
            })
            .catch(error => console.error('Error deleting company:', error));
    };

    const handleViewDetails = (id, type) => {
        navigate(`/participants/${type}/${id}`); // Navigate to the details page of the participant
    };

    function handleBack() {
        navigate(-1);
    }

    function handleAddParticipant() {
        navigate(`/events/${eventId}/add-participant`)
    }
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
            <Banner text="Participants"/>
            <div className="participant-list-container">
                <h1>{eventDetails.name}</h1>
                <p>Date: {formatDate(eventDetails.time)}</p>
                <p>Location: {eventDetails.location}</p>
                <h2>Participants</h2>
                <div className="form-actions">
                    <button type="button" onClick={handleAddParticipant}>Add Participant</button>
                </div>
                <div className="participants-section">
                    <h3>Individuals</h3>
                    <ul>
                        {individuals.map(individual => (
                            <li key={individual.id}>
                                {individual.firstName} {individual.lastName} {individual.personalId}
                                <div className="actions-part">
                                    <button onClick={() => handleViewDetails(individual.id, 'individuals')}>View Details</button>
                                    <button onClick={() => handleDeleteIndividual(individual.id)}>Delete</button>
                                </div>
                            </li>
                        ))}
                    </ul>
                    <h3>Companies</h3>
                    <ul>
                        {companies.map(company => (
                            <li key={company.id}>
                                {company.companyName} {company.companyRegistrationCode}
                                <div className="actions-part">
                                    <button onClick={() => handleViewDetails(company.id, 'companies')}>View Details</button>
                                    <button onClick={() => handleDeleteCompany(company.id)}>Delete</button>
                                </div>
                            </li>
                        ))}
                    </ul>
                </div>
                <div className="form-actions">
                    <button type="button" onClick={handleBack}>Back</button>
                </div>
            </div>
            <Footer/>
        </div>
    );
}

export default ParticipantList;
