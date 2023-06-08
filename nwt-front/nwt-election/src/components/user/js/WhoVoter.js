import React from 'react';
import '../css/WhoVoter.css';
import { useHistory } from 'react-router-dom';

const WhoVoter = () => {

    const history = useHistory();

    const handleSwitchToLanding = () => {
        history.push('/landing');
    };

    const handleSwitchToLegislativa = () => {
        history.push('/legislativa');
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
                    <button onClick={() => history.push('/voters')}>
                        Glasači
                        <br />
                        <span className="small-text">Sve što glasač treba da zna</span>
                    </button>
                    <button onClick={() => history.push('/izbori')}>
                        Izbori
                        <br />
                        <span className="small-text">Izbori 2024</span>
                        <br />
                        <span className="small-text">Rezultati 2022</span>
                    </button>
                    <button onClick={handleSwitchToLegislativa}>
                        Legislativa
                        <br/>
                        <span className="small-text">Zakon o provođenju izbora</span>
                    </button>
                    <button>Kontakt
                        <br/>
                        <span className="small-text">Kontaktirajte korisničku podršku ukoliko imate bilo kakvih pitanja</span>
                    </button>
                    <button onClick={handleLogout}>Odjava</button>
                </div>
            </div>
            <div className="centrirano">
                <section>
                    <h2>Ko može glasati?</h2>
                    <p>Da bi se registrovali i glasali na izborima morate:
                        <br/>
                        <p>-imati najmanje 18 godina,</p>
                        <p>-imati BH državljanstvo</p>
                    </p>
                </section>
            </div>
        </div>
    );
};

export default WhoVoter;
