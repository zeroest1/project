import React from 'react';
import './Banner.css';

const Banner = ({ text }) => {
    return (
        <div className="banner-container">
            <div className="text-section-banner">{text}</div>
            <div className="image-section-banner"></div>
        </div>
    );
}

export default Banner;
