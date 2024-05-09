import React from 'react';
import './nav.css';
import Logo from '../assets/logo.svg';
import Symbol from "../assets/symbol.svg";
import { NavLink } from 'react-router-dom';

function NavBar() {
    return (
        <nav className="navbar">
            <div className="nav-logo">
                <img src={Logo} alt="Nullam Logo" />
            </div>
            <div className="nav-links">
                <NavLink exact to="/" activeClassName="active-link">Homepage</NavLink>
                <NavLink to="/create-event" activeClassName="active-link">Add Event</NavLink>
            </div>
            <div className="nav-logo-right">
                <img src={Symbol} alt="Settings" />
            </div>
        </nav>
    );
}

export default NavBar;
