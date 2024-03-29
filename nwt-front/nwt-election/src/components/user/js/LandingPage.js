import React, {useState} from 'react';
import '../css/LandingPage.css';
import './WhoVoter';
import { useHistory } from 'react-router-dom';

const LandingPage = (props) => {
    
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
            <div className="user-content">
                <h2>Dobrodošli na online glasanje na izborima u BiH</h2>
                <p>
                    Online glasanje na izborima u Bosni i Hercegovini vam omogućava da udobno i sigurno glasate iz udobnosti svog doma.
                    Bez obzira gdje se nalazite, možete iskoristiti svoje biračko pravo i doprinijeti demokratskom procesu.
                </p>
                <p>
                    Naša platforma omogućava jednostavno registrovanje, sigurno provođenje izbora i transparentno prikazivanje rezultata.
                    Uz pomoć naše aplikacije, možete pristupiti informacijama o kandidatima, glasati za svoje favorite i pratiti izborne rezultate u realnom vremenu.
                </p>
                <p>
                    <b>Vaš glas je važan! Pridružite se online glasanju i izaberite budućnost Bosne i Hercegovine.</b>
                </p>
            </div>
        </div>
    );
};

export default LandingPage;
