import React, { useState, useEffect, useContext } from 'react';
import './VotingPage.css'
import loginImage from "../images/login5.png";
import { useHistory } from 'react-router-dom';
import { ElectionContext } from './ElectionContext';
import axios from 'axios';

function Candidate({ candidate, selectedCandidates, setSelectedCandidates }) {
    const handleClick = () => {
        const newList = [...selectedCandidates];
        const candidateIndex = newList.findIndex(c => c.list === candidate.list);

        if (candidateIndex !== -1) {
            newList.splice(candidateIndex, 1);
        }

        newList.push(candidate);
        setSelectedCandidates(newList);
    };

    const isSelected = selectedCandidates.some(c => c.id === candidate.id);

    return (
        <div className={`candidate ${isSelected ? 'selected' : ''}`} onClick={handleClick}>
            <div className="image-containerLogin">
                <img src={loginImage} alt="Login" />
            </div>
            <h2>{candidate.firstName}</h2>
            <h2>{candidate.lastName}</h2>
            <p>{candidate.description}</p>
        </div>
    );
}

function VotingPage({ candidates, selectedCandidates, setSelectedCandidates, clearSelection }) {
    const handleSubmit = () => {
        // Validacija izbora

        //da li je user glasao za više od jedne liste
        const selectedLists = selectedCandidates.map(c => c.list);
        const uniqueLists = Array.from(new Set(selectedLists));

        if (uniqueLists.length > 1) {
            alert('Možete glasati samo za jednu listu.');
            return;
        }

        //  da li je user glasao za listu A i bilo kojeg kandidata iz liste B
        const selectedCandidatesNames = selectedCandidates.map(c => c.name);
        const listASelected = selectedCandidates.some(c => c.list === 'Lista A');
        const listBSelected = selectedCandidates.some(c => c.list === 'Lista B');

        if (listASelected && listBSelected) {
            alert('Ne možete glasati za kandidate iz obje liste.');
            return;
        }

        //da li je user glasao za nezavisnu listu
        const independentListSelected = selectedCandidates.some(c => c.list === 'Nezavisna lista');

        if (independentListSelected) {
            alert('Možete glasati samo za kandidate unutar nezavisne liste.');
            return;
        }

        //  dodatne provjere ako treba jos dodat

        // spasit glas u bazu podataka

        // Resetuj izbor
        clearSelection();

        // Preusmjerite korisnika na početnu stranicu (to dodati u app.js kad se prebaci na koristenje switch-a
    };

    return (
        <div>
            <div className="VotingPage">
                <h1>Glasanje na izborima</h1>
                <div className="candidates">
                    {candidates.map(candidate => (
                        <Candidate
                            key={candidate.id}
                            candidate={candidate}
                            selectedCandidates={selectedCandidates}
                            setSelectedCandidates={setSelectedCandidates}
                        />
                    ))}
                </div>
                <button onClick={clearSelection}>Izbriši unos</button>
                <button onClick={handleSubmit}>Potvrdi glas</button>
            </div>
        </div>
    );
}

function VotingPageFinal() {
    const [selectedCandidates, setSelectedCandidates] = useState([]);
    const [candidates, setCandidates] = useState([]);  // Add state for candidates
    const selectedElection = useContext(ElectionContext); // Access the selected election from context

    const clearSelection = () => {
        setSelectedCandidates([]);
    };

    const history = useHistory();

    useEffect(() => {
        const fetchCandidates = async () => {
            const token = localStorage.getItem('access_token');
            const BASE_URL = process.env.REACT_APP_BASE_URL || 'http://localhost:8080';
            try {
                const response = await axios.get(`${BASE_URL}/election-microservice/elections/election/candidates?name=${localStorage.getItem('electionName')}`, {
                    headers: { Authorization: `Bearer ${token}` }
                });
                console.log("Kandidati koji su dohvaceni su: ");
                console.log(response.data);
                setCandidates(response.data);
            } catch (error) {
                console.error('Failed to fetch candidates:', error);
            }
        };

        if (localStorage.getItem('electionName')) {
            fetchCandidates();
        }
    }, [selectedElection]);

    const handleSwitchToLanding = () => {
        history.push('/landing');
    };

    const handleSwitchToVoters = () => {
        history.push('/voters');
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
                    <button>Izbori
                        <br/>
                        <span className="small-text">Izbori 2024</span>
                        <br/>
                        <span className="small-text">Rezultati 2022</span>
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
            <div className="App">
                <h1>Informacije o kandidatima</h1>
                <VotingPage
                    candidates={candidates} // Pass fetched candidates to the VotingPage
                    selectedCandidates={selectedCandidates}
                    setSelectedCandidates={setSelectedCandidates}
                    clearSelection={clearSelection}
                />
            </div>
        </div>
    );
}

export default VotingPageFinal;