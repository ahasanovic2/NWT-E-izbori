import React from 'react';
import './HomePage.css';

import backgroundImage from '../images/election-pic9-bl.jpg';
import imageSrc from '../images/grb-bh.png'

const HomePage = (props) => {
    return (
        <div className="photo-container-Homepage">
            <div className="container-Homepage">
                <img src={imageSrc} className="logo-image-Homepage" alt="Logo" />
                <h1 className="fontHome">Dobrodošli na E-izbore!</h1>
                <div className="buttonHomePage">
                    <button className="buttHome" onClick={()=>props.onFormSwitch('login')}> Prijava </button>
                    <button className="buttHome" onClick={()=>props.onFormSwitch('signUp')}>Registracija</button>
                </div>

                <div className="text-container-Homepage">
                    <p className="right-upper-text-Home"><b>Glasaj!</b> Tvoj glas je bitan.</p>
                </div>
            </div>
        </div>
    );
};

export default HomePage;
