import React, {useEffect, useState} from 'react';
import '../css/ResultsHome.css'
import { useHistory } from 'react-router-dom';

const ResultsHome = () => {

    const [election, setElection] = useState('');
    const [list, setList] = useState('');
    const [candidateFirstName, setCandidateFirstName] = useState('');
    const [candidateLastName, setCandidateLastName] = useState('');
    const [pollingStation, setPollingStation] = useState('');
    const [results, setResults] = useState([]);
    const [error1, setError1] = useState('');
    const [error2, setError2] = useState('');
    const [error3, setError3] = useState('');
    const [error4, setError4] = useState('');
    const [error5, setError5] = useState('');
    const [error6, setError6] = useState('');
    const [showContactInfo, setShowContactInfo] = useState(false);
    const history = useHistory();

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

    const handleResultsOption = async (option) => {
        const token = localStorage.getItem('access_token');
        const BASE_URL = process.env.REACT_APP_BASE_URL || 'http://localhost:8080';
        switch (option) {
            case 1:
                try {
                    const response = await fetch(`${BASE_URL}/result-microservice/results/full-election?electionName=${election}`, {
                        headers: {
                            'Authorization': `Bearer ${token}`
                        }
                    });
                    if (!response.ok) { // check if HTTP status is not ok (200)
                        const errorData = await response.json();
                        setError1(errorData.message);
                        return;
                    }
                    const data = await response.json();
                    setResults(data);
                    setError1('');
                    setError2('');
                    setError3('');
                    setError4('');
                    setError5('');
                    setError6('');
                }
                catch (err) {
                    setError1('An unexpected error occurred');
                }
                
                break;
            case 2:
                try {
                    const response2 = await fetch(`${BASE_URL}/result-microservice/results/election/pollingStation?electionName=${election}&pollingStationName=${pollingStation}`, {
                        headers: {
                            'Authorization': `Bearer ${token}`
                        }
                    });
                    if (!response2.ok) { // check if HTTP status is not ok (200)
                        const errorData = await response2.json();
                        setError1(errorData.message);
                        return;
                    }
                    const data = await response2.json();
                    setResults(data);
                    setError1('');
                    setError2('');
                    setError3('');
                    setError4('');
                    setError5('');
                    setError6('');
                }
                catch (err) {
                    setError2('An unexpected error occured');
                }
                break;
            case 3:
                try {
                    const response3 = await fetch(`${BASE_URL}/result-microservice/results/election/candidate?electionName=${election}&candidateFirstName=${candidateFirstName}&candidateLastName=${candidateLastName}`, {
                        headers: {
                            'Authorization': `Bearer ${token}`
                        }
                    });
                    
                    if (!response3.ok) { // check if HTTP status is not ok (200)
                        const errorData = await response3.json();
                        setError3(errorData.message);
                        return;
                    }
                    const data3 = await response3.json();
                    setResults([data3]); // encapsulate data in array
                    setError1('');
                    setError2('');
                    setError3('');
                    setError4('');
                    setError5('');
                    setError6('');

                } catch (err) {
                    setError3('An unexpected error occurred');
                }
                break;
            case 4:
                try {
                    const response4 = await fetch(`${BASE_URL}/result-microservice/results/election/list?electionName=${election}&listaName=${list}`, {
                        headers: {
                            'Authorization': `Bearer ${token}`
                        }
                    });
                    
                    if (!response4.ok) { // check if HTTP status is not ok (200)
                        const errorData = await response4.json();
                        setError4(errorData.message);
                        return;
                    }
                    const data4 = await response4.json();
                    setResults([data4]); // encapsulate data in array
                    setError1('');
                    setError2('');
                    setError3('');
                    setError4('');
                    setError5('');
                    setError6('');

                } catch (err) {
                    setError4('An unexpected error occurred');
                }
                break;
            case 5:
                try {
                    const response5 = await fetch(`${BASE_URL}/result-microservice/results/election/pollingStation/candidate?electionName=${election}&candidateFirstName=${candidateFirstName}&candidateLastName=${candidateLastName}&pollingStationName=${pollingStation}`, {
                        headers: {
                            'Authorization': `Bearer ${token}`
                        }
                    });
                    
                    if (!response5.ok) { // check if HTTP status is not ok (200)
                        const errorData = await response5.json();
                        setError5(errorData.message);
                        return;
                    }
                    const data5 = await response5.json();
                    setResults([data5]); // encapsulate data in array
                    setError1('');
                    setError2('');
                    setError3('');
                    setError4('');
                    setError5('');
                    setError6('');

                } catch (err) {
                    setError5('An unexpected error occurred');
                }
                break;
            case 6:
                try {
                    const response6 = await fetch(`${BASE_URL}/result-microservice/results/election/pollingStation/list?electionName=${election}&listaName=${list}&pollingStationName=${pollingStation}`, {
                        headers: {
                            'Authorization': `Bearer ${token}`
                        }
                    });
                    
                    if (!response6.ok) { // check if HTTP status is not ok (200)
                        const errorData = await response6.json();
                        setError6(errorData.message);
                        return;
                    }
                    const data6 = await response6.json();
                    setResults([data6]); // encapsulate data in array
                    setError1('');
                    setError2('');
                    setError3('');
                    setError4('');
                    setError5('');
                    setError6('');

                } catch (err) {
                    setError6('An unexpected error occurred');
                }
                break;
            default:
                break;
        }
    };

    const handleResults = async () => {
        if (election && !list && !candidateFirstName && !candidateLastName && !pollingStation) {
            await handleResultsOption(1); // Full results for election
        } else if (election && !list && !candidateFirstName && !candidateLastName && pollingStation) {
            await handleResultsOption(2); // Results for election at certain polling station
        } else if (election && !list && candidateFirstName && candidateLastName && !pollingStation) {
            await handleResultsOption(3); // Full results for one candidate at certain election
        } else if (election && list && !candidateFirstName && !candidateLastName && !pollingStation) {
            await handleResultsOption(4); // Full results for one list at certain election
        } else if (election && !list && candidateFirstName && candidateLastName && pollingStation) {
            await handleResultsOption(5); // Results for one candidate at certain election at certain polling station
        } else if (election && list && !candidateFirstName && !candidateLastName && pollingStation) {
            await handleResultsOption(6); // Results for one list at certain election at certain polling station
        } else {
            // Handle the case where input fields are not in any of the expected combinations
            console.log("Invalid combination of input fields");
        }
    };

    return(
        <div className='results-home'>
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
            <div className='results-options'>
                <label>
                    Unesite ime izbora:
                    <input type="text" value={election} onChange={e => setElection(e.target.value)} />
                </label>
                <label>
                    Unesite ime liste:
                    <input type="text" value={list} onChange={e => setList(e.target.value)} />
                </label>
                <label>
                    Unesite ime kandidata:
                    <input type="text" value={candidateFirstName} onChange={e => setCandidateFirstName(e.target.value)} />
                </label>
                <label>
                    Unesite prezime kandidata:
                    <input type="text" value={candidateLastName} onChange={e => setCandidateLastName(e.target.value)} />
                </label>
                <label>
                    Unesite naziv izbornog mjesta:
                    <input type="text" value={pollingStation} onChange={e => setPollingStation(e.target.value)} />
                </label>
                <button onClick={handleResults}>Dohvati rezultate</button>
                {error1 && <p className="error-message">{error1}</p>}
                {error2 && <p className="error-message">{error2}</p>}
                {error3 && <p className="error-message">{error3}</p>}
                {error4 && <p className="error-message">{error4}</p>}
                {error5 && <p className="error-message">{error5}</p>}
                {error6 && <p className="error-message">{error6}</p>}
            </div>
            <div className='results-table'>
                <table>
                    <thead>
                        <tr>
                            <th>Lista</th>
                            <th>Kandidat</th>
                            <th>Izborno mjesto</th>
                            <th>Broj glasova</th>
                        </tr>
                    </thead>
                    <tbody>
                        {Array.isArray(results) 
                            ? results.map(result => (
                                <tr key={result.id}>
                                    <td>{result.listName || 'N/A'}</td>
                                    <td>{result.candidateFirstName ? `${result.candidateFirstName} ${result.candidateLastName}` : 'N/A'}</td>
                                    <td>{result.pollingStationName || 'N/A'}</td>
                                    <td>{result.voteCount || 0}</td>
                                </tr>
                            ))
                            : <tr key={results.id}>
                                    <td>{results.listName || 'N/A'}</td>
                                    <td>{results.candidateFirstName ? `${results.candidateFirstName} ${results.candidateLastName}` : 'N/A'}</td>
                                    <td>{results.pollingStationName || 'N/A'}</td>
                                    <td>{results.voteCount || 0}</td>
                            </tr>
                        }
                    </tbody>
                </table>
            </div>
        </div>
    );
};
export default ResultsHome;