import React, {useState} from 'react';
import '../css/Legislativa.css';
import { useHistory } from 'react-router-dom';

const Legislativa = (props) => {
    const history = useHistory();
    const [showContactInfo, setShowContactInfo] = useState(false);

    const handleContactHover = () => {
        setShowContactInfo(true);
    };

    const handleContactLeave = () => {
        setShowContactInfo(false);
    };

    const handleSwitchToLanding = () => {
        history.push('/landing');
    };

    const handleSwitchToVoters = () => {
        history.push('/voters');
    };

    const handleSwitchToLegislativa = () => {
        history.push('/legislativa');
    };

    const handleSwitchToIzbori = () => {
        history.push('/election')
    };

    const handleSwitchToResults = () => {
        history.push('/results')
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
                    <button onClick={handleSwitchToLanding}>Početna
                        <br/>
                        <span className="small-text">Početna stranica aplikacije</span>
                    </button>
                    <button onClick={handleSwitchToVoters}>Glasači
                        <br/>
                        <span className="small-text">Sve što glasač treba da zna</span>
                    </button>
                    <button onClick={handleSwitchToIzbori}>Izbori
                        <br/>
                        <span className="small-text">Izbori 2024</span>
                        <br/>
                        <span className="small-text">Rezultati 2022</span>
                    </button>
                    <button onClick={handleSwitchToResults}>Rezultati
                        <br/>
                        <span className='small-text'>Rezultati 2024</span>
                    </button>
                    <button onClick={handleSwitchToLegislativa}>Legislativa
                        <br/>
                        <span className="small-text">Zakon o provođenju izbora</span>
                    </button>
                    <button
                        onMouseEnter={handleContactHover}
                        onMouseLeave={handleContactLeave}
                    >
                        Kontakt
                        <br/>
                        <span className="small-text">Kontaktirajte korisničku podršku ukoliko imate bilo kakvih pitanja</span>
                    </button>
                    <div className="contact-info">
                        <p>Broj telefona: 123-456-789</p>
                        <p>Email: info@eizbori.com</p>
                    </div>
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
