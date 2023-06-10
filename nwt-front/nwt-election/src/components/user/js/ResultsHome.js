import React, {useEffect, useState} from 'react';
import '../css/ResultsHome.css'

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

    const handleResultsOption = async (option) => {
        const token = localStorage.getItem('access_token');
        switch (option) {
            case 1:
                try {
                    const response = await fetch(`http://localhost:8080/result-microservice/results/full-election?electionName=${election}`, {
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
                const response2 = await fetch(`http://localhost:8080/result-microservice/results/election/pollingStation?electionName=${election}&pollingStationName=${pollingStation}`, {
                    headers: {
                        'Authorization': `Bearer ${token}`
                    }
                });
                const data2 = await response2.json();
                setResults(data2);
                break;
            case 3:
                try {
                    const response3 = await fetch(`http://localhost:8080/result-microservice/results/election/candidate?electionName=${election}&candidateFirstName=${candidateFirstName}&candidateLastName=${candidateLastName}`, {
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
            default:
                break;
        }
    };

    return(
        <div className='results-home'>
            <div className="header">
                <h1>E-izbori</h1>
                <div className="nav-buttons">
                    <button>Početna
                        <br/>
                        <span className="small-text">Početna stranica aplikacije</span>
                    </button>
                    <button>Glasači
                        <br/>
                        <span className="small-text">Sve što glasač treba da zna</span>
                    </button>
                    <button>Izbori
                        <br/>
                        <span className="small-text">Izbori 2024</span>
                        <br/>
                        <span className="small-text">Rezultati 2022</span>
                    </button>
                    <button>Rezultati
                        <br/>
                        <span className='small-text'>Rezultati 2024</span>
                    </button>
                    <button>Legislativa
                        <br/>
                        <span className="small-text">Zakon o provođenju izbora</span>
                    </button>
                    <button>Kontakt
                        <br/>
                        <span className="small-text">Kontaktirajte korisničku podršku ukoliko imate bilo kakvih pitanja</span>
                    </button>
                    <button>Odjava</button>
                </div>
            </div>
            <div className='results-options'>
                <label>
                    Input election:
                    <input type="text" value={election} onChange={e => setElection(e.target.value)} />
                </label>
                <label>
                    Input list:
                    <input type="text" value={list} onChange={e => setList(e.target.value)} />
                </label>
                <label>
                    Input candidate first name:
                    <input type="text" value={candidateFirstName} onChange={e => setCandidateFirstName(e.target.value)} />
                </label>
                <label>
                    Input candidate last name:
                    <input type="text" value={candidateLastName} onChange={e => setCandidateLastName(e.target.value)} />
                </label>
                <label>
                    Input polling station:
                    <input type="text" value={pollingStation} onChange={e => setPollingStation(e.target.value)} />
                </label>
                <button onClick={() => handleResultsOption(1)}>Get full results for election</button>
                {error1 && <p className="error-message">{error1}</p>}
                <button onClick={() => handleResultsOption(2)}>Get results for election at certain polling station</button>
                <button onClick={() => handleResultsOption(3)}>Get full results for one candidate at certain election</button>
                {error3 && <p className="error-message">{error3}</p>}
                <button >Get full results for one list at certain election</button>
                <button >Get results for one candidate at certain election at certain polling station</button>
                <button >Get results for one list at certain election at certain polling station</button>
            </div>
            <div className='results-table'>
                <table>
                    <thead>
                        <tr>
                            <th>List Name</th>
                            <th>Candidate</th>
                            <th>Polling Station Name</th>
                            <th>Vote Count</th>
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