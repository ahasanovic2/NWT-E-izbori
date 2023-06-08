import React, { useState, useEffect, useContext } from 'react';
import '../css/VotingPage.css'
import loginImage from "../../../images/login5.png";
import { useHistory } from 'react-router-dom';
import { ElectionContext } from './ElectionContext';
import axios from 'axios';

function Candidate({ candidate, selectedVote, setSelectedVote }) {
    const handleClick = () => {
        setSelectedVote({ type: 'candidate', data: candidate });
    };

    const isSelected = selectedVote && selectedVote.type === 'candidate' && selectedVote.data.id === candidate.id;

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

function List({ list, selectedVote, setSelectedVote }) {
    const handleClick = () => {
        setSelectedVote({ type: 'list', data: list });
    };

    const isSelected = selectedVote && selectedVote.type === 'list' && selectedVote.data.id === list.id;

    return (
        <div className={`candidate ${isSelected ? 'selected' : ''}`} onClick={handleClick}>
            <div className="image-containerLogin">
                <img src={loginImage} alt="Login" />
            </div>
            <h2>{list.name}</h2>
            <p>{list.description}</p>
        </div>
    );
}

function VotingPage({ candidates, lists, selectedVote, setSelectedVote, clearSelection }) {
    const BASE_URL = process.env.REACT_APP_BASE_URL || 'http://localhost:8080';
    const token = localStorage.getItem('access_token');
    const history = useHistory();

    const handleSubmit = async () => {
        // Validation checks

        if (!selectedVote) {
            alert('You need to select a candidate or a list before voting.');
            return;
        }

        try {
            if (selectedVote.type === 'candidate') {
                const { firstName, lastName } = selectedVote.data;
                const url = `${BASE_URL}/voting-microservice/voting/vote-for-candidate?electionName=${localStorage.getItem('electionName')}&firstName=${firstName}&lastName=${lastName}`;
                await axios.post(url, {}, { headers: { Authorization: `Bearer ${token}` } });
                alert('Your vote for candidate has been casted!');
            } else if (selectedVote.type === 'list') {
                const { name } = selectedVote.data;
                const url = `${BASE_URL}/voting-microservice/voting/vote-for-list?electionName=${localStorage.getItem('electionName')}&name=${name}`;
                await axios.post(url, {}, { headers: { Authorization: `Bearer ${token}` } });
                alert('Your vote for list has been casted!');
            }

            clearSelection();  // Reset selection
            history.push(`/landing`);
        } catch (error) {
            console.error('Failed to cast vote:', error);
            alert('Failed to cast vote. Please try again.');
        }
    };

    return (
        <div>
            <div className="VotingPage">
                <h1>Voting in elections</h1>
                <div className="candidates">
                    {candidates.map(candidate => (
                        <Candidate
                            key={candidate.id}
                            candidate={candidate}
                            selectedVote={selectedVote}
                            setSelectedVote={setSelectedVote}
                        />
                    ))}
                </div>
                <div className="lists">
                    {lists.map(list => (
                        <List
                            key={list.id}
                            list={list}
                            selectedVote={selectedVote}
                            setSelectedVote={setSelectedVote}
                        />
                    ))}
                </div>
                <button onClick={clearSelection}>Clear selection</button>
                <button onClick={handleSubmit}>Confirm vote</button>
            </div>
        </div>
    );
}

function VotingPageFinal() {
    const [candidates, setCandidates] = useState([]);  // Add state for candidates
    const [lists, setLists] = useState([]);
    const [selectedVote, setSelectedVote] = useState(null);
    const selectedElection = useContext(ElectionContext); // Access the selected election from context

    const clearSelection = () => {
        setSelectedVote([]);
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

        const fetchLists = async () => {
            const token = localStorage.getItem('access_token');
            const BASE_URL = process.env.REACT_APP_BASE_URL || 'http://localhost:8080';
            try {
                const response = await axios.get(`${BASE_URL}/election-microservice/elections/election/lists?name=${localStorage.getItem('electionName')}`, {
                    headers: { Authorization: `Bearer ${token}` }
                });
                console.log("Lists fetched are: ");
                console.log(response.data);
                setLists(response.data);
            } catch (error) {
                console.error('Failed to fetch lists:', error);
            }
        }

        if (localStorage.getItem('electionName')) {
            fetchCandidates();
            fetchLists();
        }
    }, [selectedElection]);

    const handleSwitchToLanding = () => {
        localStorage.removeItem('electionName');
        history.push('/landing');
    };

    const handleSwitchToVoters = () => {
        localStorage.removeItem('electionName');
        history.push('/voters');
    };

    const handleSwitchToLegislativa = () => {
        localStorage.removeItem('electionName');
        history.push('/legislativa');
    };

    const handleSwitchToIzbori = () => {
        localStorage.removeItem('electionName');
        history.push('/election');
    }

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
                    <button onClick={handleSwitchToIzbori}>Izbori
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
                <h1>Information about candidates and lists</h1>
                <VotingPage
                    candidates={candidates} // Pass fetched candidates to the VotingPage
                    lists={lists} // Pass fetched lists to the VotingPage
                    selectedVote={selectedVote}
                    setSelectedVote={setSelectedVote}
                    clearSelection={clearSelection}
                />
            </div>
        </div>
    );
}

export default VotingPageFinal;