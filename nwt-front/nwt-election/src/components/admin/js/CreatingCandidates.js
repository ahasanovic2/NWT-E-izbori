import React, {useState, useEffect} from "react";
import '../css/CreatingCandidates.css';
import { useHistory } from "react-router-dom";

const CreatingCandidates = () => {
    const history = useHistory();
    const [electionName, setElectionName] = useState("");
    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [description, setDescription] = useState("");
    const [errorMessage, setErrorMessage] = useState("");
    const [errorMessageDescription, setErrorMessageDescription] = useState("");

    const [elections, setElections] = useState([]);

    const fetchElections = async () => {
        const BASE_URL = process.env.REACT_APP_BASE_URL || 'http://localhost:8080';
        const token = localStorage.getItem('access_token');
        const headers = new Headers();
        headers.append('Content-Type', 'application/json');
        headers.append('Authorization', `Bearer ${token}`);

        const response = await fetch(`${BASE_URL}/election-microservice/elections`, { headers });
        const data = await response.json();
        setElections(data);
    };

    useEffect(() => {
        fetchElections();
    }, []);


    const handleSwitchToLanding = () => {
        history.push('/admin-landing');
    };

    const handleSwitchToCreatingElections = () => {
        history.push('/admin-create-elections')
    };

    const handleSwitchToCreatingLists = () => {
        history.push('/admin-create-lists');
    };

    const handleSwitchToCreatingCandidates = () => {
        history.push('/admin-create-candidates');
    };

    const handleSwitchToCreatingPollingStations = () => {
        history.push('/admin-create-pollingstations');
    };

    const handleSwitchToAddPSToElection = () => {
        history.push('/admin-add-pollingstations');
    };

    const handleLogout = () => {
        localStorage.removeItem('access_token');
        localStorage.removeItem('refresh_token');
        history.push('/');
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setErrorMessage("");
        setErrorMessageDescription("");

        if (description.length < 20) {
            setErrorMessageDescription("Description must be at least 20 characters long");
            return;
        }
    
        const token = localStorage.getItem('access_token');
        const BASE_URL = process.env.REACT_APP_BASE_URL || 'http://localhost:8080';
        const headers = new Headers();
        headers.append('Content-Type', 'application/json');
        headers.append('Authorization', `Bearer ${token}`);
    
        const body = JSON.stringify([{
            firstName,
            lastName,
            description
        }]);
    
        const response = await fetch(`${BASE_URL}/election-microservice/elections/election/add-candidates?name=${electionName}`, {
            method: 'POST',
            headers,
            body
        });
    
        if (!response.ok) {
            const errorData = await response.json();
            // Check if errorData is an array or an object
            if(Array.isArray(errorData)) {
                // Handle array of errors
                setErrorMessage(errorData[0].message);
            } else {
                // Handle single error object
                setErrorMessage(errorData.message);
            }
        } else {
            alert('Successfully added candidate');
            setErrorMessage("");
            // Reset the input fields except for election name
            setFirstName("");
            setLastName("");
            setDescription("");
    
        }
    };

    const handleReset = () => {
        setElectionName("");
        setFirstName("");
        setLastName("");
        setDescription("");
        setErrorMessage("");
        setErrorMessageDescription("");
    };

    
    return(
        <div className="candidates">
            <div className="header">
                <h1>E-izbori</h1>
                <div className="nav-buttons">
                    <button onClick={handleSwitchToLanding}>Početna
                        <br/>
                        <span className="small-text">Početna stranica aplikacije</span>
                    </button>
                    <button onClick={handleSwitchToCreatingElections}>Kreiraj izbore
                        <br/>
                        <span className='small-text'></span>
                    </button>
                    <button onClick={handleSwitchToCreatingLists}>Kreiraj liste
                        <br/>
                        <span className='small-text'></span>
                    </button>
                    <button onClick={handleSwitchToCreatingCandidates}>Kreiraj kandidate
                        <br/>
                        <span className='small-text'></span>
                    </button>
                    <button onClick={handleSwitchToCreatingPollingStations}>Kreiraj izborne stanice
                        <br/>
                        <span className='small-text'></span>
                    </button>
                    <button onClick={handleSwitchToAddPSToElection}>Dodijeli izborne stanice izborima
                        <br/>
                        <span className='small-text'></span>
                    </button>                    
                    <button onClick={handleLogout}>Odjava</button>
                </div>
            </div>
            <div className="candidates-content">
                <form onSubmit={handleSubmit}>
                    <label>
                        Naziv izbora:
                        <select value={electionName} onChange={e => setElectionName(e.target.value)} required>
                        <option value="">--Molimo odaberite izbore--</option>
                            {elections.map(election => (
                                <option key={election.id} value={election.name}>{election.name}</option>
                            ))}
                        </select>
                    </label>
                    <label>
                        Ime kandidata:
                        <input type="text" value={firstName} onChange={e => setFirstName(e.target.value)} required/>
                    </label>
                    <label>
                        Prezime kandidata:
                        <input type="text" value={lastName} onChange={e => setLastName(e.target.value)} required/>
                    </label>
                    <label>
                        Opis:
                        <textarea value={description} onChange={e => setDescription(e.target.value)} required/>
                    </label>
                    {errorMessageDescription && <p className="error-class">{errorMessageDescription}</p>}
                    {errorMessage && <p className="error-class">{errorMessage}</p>}
                    <div className="dugmad">
                        <button type="button" id="clear-form" onClick={handleReset}>Očisti formu</button>
                        <button type="submit">Potvrdi</button>
                    </div>
                </form>
            </div>
        </div>
    );

};

export default CreatingCandidates;