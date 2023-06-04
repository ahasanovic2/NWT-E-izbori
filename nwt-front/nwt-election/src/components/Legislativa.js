import React from 'react';
import './Legislativa.css';

const Legislativa = (props) => {
    const handleSwitchToLanding = () => {
        props.onFormSwitch('landing');
    };

    const handleSwitchToVoters = () => {
        props.onFormSwitch('voters');
    };

    return (
        <div className="landing-page">
            <div className="header">
                <h1>E-izbori</h1>
                <div className="nav-buttons">
                    <button onClick={handleSwitchToLanding}>
                        Početna
                        <br />
                        <span className="small-text">Povratak na početnu stranicu</span>
                    </button>
                    <button onClick={handleSwitchToVoters}>
                        Glasači
                        <br />
                        <span className="small-text">Sve što glasač treba da zna</span>
                    </button>
                    <button>
                        Izbori
                        <br />
                        <span className="small-text">Izbori 2024</span>
                        <br />
                        <span className="small-text">Rezultati 2022</span>
                    </button>
                    <button>
                        Legislativa
                        <br />
                        <span className="small-text">Zakon o provođenju izbora</span>
                    </button>
                    <button>
                        Kontakt
                        <br />
                        <span className="small-text">Kontaktirajte korisničku podršku ukoliko imate bilo kakvih pitanja</span>
                    </button>
                    <button onClick={props.onLogout}>Odjava</button>
                </div>
            </div>
            <div className="contentLegislativa">
                <a href="https://www.izbori.ba/Documents/documents/ZAKONI/Tehnicki_precisceni_tekst/Tehnicki_precisceni_tekst_IZ_BiH_11_2022-bos.pdf" target="_blank" rel="noopener noreferrer">
                    Izborni zakon Bosne i Hercegovine
                </a>
            </div>
            <div className="contentLegislativa">
                <a href="https://www.izbori.ba/Documents/documents/ZAKONI/IzborniZakonRS-18032013-1.PDF" target="_blank" rel="noopener noreferrer">
                    Izborni zakon Republike Srpske
                </a>
            </div>
            <div className="contentLegislativa">
                <a href="https://www.izbori.ba/Documents/documents/ZAKONI/IZ_BRCKO_DISTRIKT-BOS.PDF" target="_blank" rel="noopener noreferrer">
                    Izborni zakon Brčko Distrikta BiH
                </a>
            </div>
        </div>
    );
};

export default Legislativa;
