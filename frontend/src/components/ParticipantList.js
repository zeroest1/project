import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Link, useParams, useNavigate } from 'react-router-dom';

function ParticipantList() {
    const { eventId } = useParams();
    const [individuals, setIndividuals] = useState([]);
    const [companies, setCompanies] = useState([]);
    const navigate = useNavigate(); // Hook to programmatically navigate

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
        navigate(`/${type}/${id}`); // Navigate to the details page of the participant
    };

    return (
        <div>
            <h2>Participants</h2>
            <Link to={`/events/${eventId}/add-participant`}>Add Participant</Link>
            <h3>Individuals</h3>
            <ul>
                {individuals.map(individual => (
                    <li key={individual.id}>
                        {individual.firstName} {individual.lastName}
                        <button onClick={() => handleDeleteIndividual(individual.id)}>Delete</button>
                        <button onClick={() => handleViewDetails(individual.id, 'individuals')}>View Details</button>
                    </li>
                ))}
            </ul>
            <h3>Companies</h3>
            <ul>
                {companies.map(company => (
                    <li key={company.id}>
                        {company.companyName} - {company.companyRegistrationCode}
                        <button onClick={() => handleDeleteCompany(company.id)}>Delete</button>
                        <button onClick={() => handleViewDetails(company.id, 'companies')}>View Details</button>
                    </li>
                ))}
            </ul>
            <Link to="/">Back to Events</Link>
        </div>
    );
}

export default ParticipantList;
