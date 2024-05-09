import React from 'react';
import './HomePage.css';
import myImage from '../assets/pilt.jpg';

function HomePageContent() {
    return (
        <div className="content-container">
            <div className="text-section">
                <h1>Sed nec elit vestibulum, tincidunt orci et, sagittis ex.</h1>
                <p>Vestibulum rutrum neque suscipit ante mattis maximus. Nulla non sapien viverra, lobortis lorem non, accumsan metus.</p>
            </div>
            <div className="image-section">
                <img src={myImage} alt="Descriptive Alt Text" />
            </div>
        </div>
    );
}

export default HomePageContent;
