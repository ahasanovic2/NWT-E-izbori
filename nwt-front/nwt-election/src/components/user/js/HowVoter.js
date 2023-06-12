import React, {useState} from 'react';
import '../css/HowVoter.css'
import { useHistory } from 'react-router-dom';

const HowVoter = () => {
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
            <div className="centrirano">
            <section>
                <h2>Kako glasati?</h2>
                <p>Da biste glasali na dan izbora:</p>
                <p>-morate biti registrovani na našoj aplikaciji,</p>
                <p>-prilikom registracije morate unijeti svoj JMBG i jedinstvenu email adresu.</p>
            </section>
            </div>
        </div>
    );
};

export default HowVoter;
