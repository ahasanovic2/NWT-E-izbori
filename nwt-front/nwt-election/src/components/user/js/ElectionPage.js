import React, {useEffect, useState} from 'react';
import "../css/ElectionPage.css"
import axios from 'axios';
import { useHistory } from 'react-router-dom';
import { ElectionContext } from './ElectionContext';
import moment from 'moment/moment';

const ElectionPage = () => {
    const [elections, setElections] = useState([]);

    const [selectedElection, setSelectedElection] = useState(null);
    const [showContactInfo, setShowContactInfo] = useState(false);

    const handleContactHover = () => {
        setShowContactInfo(true);
    };

    const handleContactLeave = () => {
        setShowContactInfo(false);
    };


    useEffect(() => {
        const fetchElections = async () => {
            const token = localStorage.getItem('access_token');
            try {
                const BASE_URL = process.env.REACT_APP_BASE_URL || 'http://localhost:8080';
                const response = await axios.get(`${BASE_URL}/voting-microservice/voting/elections`, {
                    headers: { Authorization: `Bearer ${token}` }
                });
                setElections(response.data);
            } catch (error) {
                console.error('Failed to fetch elections:', error);
            }
        };

        fetchElections();
    }, []);

    const handleElectionClick = (electionId) => {
        // Ovdje možete implementirati logiku za prikaz dodatnih informacija o izboru
        // i omogućiti glasanje ako je status aktivan
        console.log(`Kliknut izbor s ID-om: ${electionId}`);
    };

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
    
    const handleSwitchToVotingPage = (election) => {
        localStorage.setItem('electionName',election.name);
        console.log("Election name is ", localStorage.getItem('electionName'));
        history.push('/voting-page');
    };

    return (
        <ElectionContext.Provider value={selectedElection}>
        <div>
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
            <div className='container'>
                <div className="rezultati">
                    <p>
                        Na sljedećem linku možete vidjeti rezultate izbora 2022 godine:
                    </p>
                    <a href="https://www.izbori.ba/Rezultati_izbora/?resId=32&langId=1" target="_blank" rel="noopener noreferrer">
                        Rezultati 2022.
                    </a>
                </div>
                <h1>IZBOR U BOSNI I HERCEGOVINI</h1>
                <div className="election-list">
                    {elections.map(election => (
                        <div className="election-card" key={election.id} onClick={() => handleElectionClick(election.id)}>
                            <h2 className="title">{election.name}</h2>
                            <p>{election.description}</p>
                            <p>Vrijeme početka: {moment(election.startTime).format('MMMM Do YYYY, h:mm:ss a')}</p>
                            <p>Vrijeme kraja: {moment(election.endTime).format('MMMM Do YYYY, h:mm:ss a')}</p>
                            <p>Status: <span style={{fontWeight: 'bold', color: election.status === 'Active' ? 'green' : (election.status === 'Finished' ? 'red' : 'orange')}}>{election.status}</span></p>
                            {election.status === 'Active' && (
                                <button onClick={(event) => {
                                    event.stopPropagation();
                                    handleSwitchToVotingPage(election);
                                }}>                     
                            Glasaj
                            </button>
                        )}
                    </div>
                ))}
                </div>
            </div>
        </div>
        </ElectionContext.Provider>
    );
};

export default ElectionPage;