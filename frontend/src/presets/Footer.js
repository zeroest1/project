import React from 'react';
import './Footer.css';

function Footer() {
    return (
        <footer className="footer">
            <div className="footer-section">
                <h4>Curabitur</h4>
                <ul>
                    <li>Emauris</li>
                    <li>Kfringilla</li>
                    <li>Oin magna sem</li>
                    <li>Kelementum</li>
                </ul>
            </div>
            <div className="footer-section">
                <h4>Fusce</h4>
                <ul>
                    <li>Econsectetur</li>
                    <li>Ksollicitudin</li>
                    <li>Omvulpitate</li>
                    <li>Nunc fringilla tellu</li>
                </ul>
            </div>
            <div className="footer-section">
                <h4>Kontakt</h4>
                <p>Peakontor: Tallinnas<br />Väike-Ameerika 1, 11415 Tallinn<br />Telefon: 605 4450<br />Faks: 605 3186</p>
                <p>Harukontor: Võrus<br />Oja tn 7 (külastusaadress)<br />Telefon: 605 3330<br />Faks: 605 3155</p>
            </div>
        </footer>
    );
}

export default Footer;
