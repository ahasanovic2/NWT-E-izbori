import React, {useState} from 'react';
import '../css/VotersPage.css';
import HowVoter from './HowVoter';
import WhoVoter from './WhoVoter';
import { useHistory, Switch, Route, useRouteMatch, useLocation } from 'react-router-dom';

const VotersPage = () => {

    const match = useRouteMatch();
    const location = useLocation();
    const history = useHistory();

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

    const handleButtonClick = (route) => {
        history.push(`/voters/${route}`);
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
                    <button>Kontakt
                        <br/>
                        <span className="small-text">Kontaktirajte korisničku podršku ukoliko imate bilo kakvih pitanja</span>
                    </button>
                    <button onClick={handleLogout}>Odjava</button>
                </div>
            </div>
            <div className="left-sidebar">
                <button
                    className={`sidebar-button ${location.pathname === '/voters/whoVoter' ? 'active' : ''}`}
                    onClick={() => handleButtonClick('whoVoter')}
                >
                    Ko može glasati?
                </button>
                <button
                    className={`sidebar-button ${location.pathname === '/voters/howVoter' ? 'active' : ''}`}
                    onClick={() => handleButtonClick('howVoter')}
                >
                    Kako glasati?
                </button>
                <Switch>
                    <Route path='/voters/whoVoter' component={WhoVoter} />
                    <Route path='/voters/howVoter' component={HowVoter} />
                </Switch>
            </div>
        </div>
    );
};

export default VotersPage;
