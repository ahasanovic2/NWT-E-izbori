import React from 'react';
import './LandingPage.css';

const LandingPage = (props) => {
    return (
        <div className="landing-page">
            <div className="header">
                <h1>E-izbori</h1>
                <div className="nav-buttons">
                    <button>Glasači
                        <br/>
                        <span className="small-text">Sve što glasač treba da zna</span>
                    </button>
                    <button>Izbori
                        <br/>
                        <span className="small-text">Izbori 2024</span>
                        <br/>
                        <span className="small-text">Rezultati 2022</span>
                    </button>
                    <button>Legislativa
                        <br/>
                        <span className="small-text">Zakon o provođenju izbora</span>
                    </button>
                    <button onClick={props.onLogout}>Odjava</button>
                </div>
            </div>
        </div>
    );
};

export default LandingPage;