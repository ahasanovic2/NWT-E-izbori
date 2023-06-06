import React from 'react';
import './Legislativa.css';
import { useHistory } from 'react-router-dom';

const Legislativa = (props) => {
    const history = useHistory();

    const handleSwitchToLanding = () => {
        history.push('/landing');
    };

    const handleSwitchToVoters = () => {
        history.push('/voters');
    };

    const handleSwitchToIzbori = () => {
        history.push('/election')
    };

    const handleLogout = () => {
        localStorage.removeItem('access_token');
        localStorage.removeItem('refresh_token');
        history.push('/');
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
                    <button onClick={handleSwitchToIzbori}>
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
                    <button onClick={handleLogout}>Odjava</button>
                </div>
            </div>
            <div className="contentLegislativa">
                <a href="https://www.izbori.ba/Documents/documents/ZAKONI/Tehnicki_precisceni_tekst/Tehnicki_precisceni_tekst_IZ_BiH_11_2022-bos.pdf" target="_blank" rel="noopener noreferrer">
                    Izborni zakon Bosne i Hercegovine
                </a>
                <br/>
                <br/>
                <a href="https://www.izbori.ba/Documents/documents/ZAKONI/IzborniZakonRS-18032013-1.PDF" target="_blank" rel="noopener noreferrer">
                    Izborni zakon Republike Srpske
                </a>
                <br/>
                <br/>
                <a href="https://www.izbori.ba/Documents/documents/ZAKONI/IZ_BRCKO_DISTRIKT-BOS.PDF" target="_blank" rel="noopener noreferrer">
                    Izborni zakon Brčko Distrikta BiH
                </a>
            </div>
        </div>
    );
};

export default Legislativa;
