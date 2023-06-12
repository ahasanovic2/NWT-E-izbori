import React, {useState, useEffect} from "react";
import '../css/AssociatePollingStations.css';
import { useHistory } from "react-router-dom";

const AssociatePollingStations = () => {
    const history = useHistory();
    const [elections, setElections] = useState([]);
    const [pollingStations, setPollingStations] = useState([]);
    const [selectedElection, setSelectedElection] = useState("");
    const [selectedPollingStations, setSelectedPollingStations] = useState([]);
    const [errorMessage, setErrorMessage] = useState("");

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
    
      const fetchPollingStations = async () => {
        const BASE_URL = process.env.REACT_APP_BASE_URL || 'http://localhost:8080';
        const token = localStorage.getItem('access_token');
        const headers = new Headers();
        headers.append('Content-Type', 'application/json');
        headers.append('Authorization', `Bearer ${token}`);
    
        const response = await fetch(`${BASE_URL}/auth-service/pollingStations`, { headers });
        const data = await response.json();
        setPollingStations(data);
      };
    
      useEffect(() => {
        fetchElections();
        fetchPollingStations();
      }, []);

      const handlePollingStationChange = (e) => {
        if(e.target.checked) {
          setSelectedPollingStations([...selectedPollingStations, e.target.value]);
        } else {
          setSelectedPollingStations(selectedPollingStations.filter(ps => ps !== e.target.value));
        }
      };
    
      const handleSubmit = async (e) => {
        e.preventDefault();
        const token = localStorage.getItem('access_token');
        const headers = new Headers();
        headers.append('Content-Type', 'application/json');
        headers.append('Authorization', `Bearer ${token}`);
    
        const body = JSON.stringify(selectedPollingStations.map(ps => ps.id));
        const response = await fetch(`http://localhost:8080/election-microservice/elections/election/set-pollingstations?name=${selectedElection}`, {
          method: 'POST',
          headers,
          body
        });
    
        if (!response.ok) {
          const errorData = await response.json();
          setErrorMessage(errorData.message);
        } else {
          alert('Successfully added polling stations to election');
          setErrorMessage("");
          setSelectedElection("");
          setSelectedPollingStations([]);
        }
      };

    return (
        <div className="polling-stations-2">
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
            <div className="content-ps">
            <form onSubmit={handleSubmit}>
                <label>
                    Odaberi izbore:
                    <select value={selectedElection} onChange={(e) => setSelectedElection(e.target.value)}>
                    <option value="">--Molimo odaberite izbore--</option>
                    {elections.map(election => (
                        <option key={election.id} value={election.name}>{election.name}</option>
                    ))}
                    </select>
                </label>

                <table>
                    <thead>
                    <tr>
                        <th>Odaberi</th>
                        <th>Naziv</th>
                        <th>Adresa</th>
                        <th>Opcina</th>
                        <th>Kanton</th>
                        <th>Entitet</th>
                    </tr>
                    </thead>
                    <tbody>
                    {pollingStations.map(ps => (
                        <tr key={ps.id}>
                        <td>
                        <input 
                            type="checkbox" 
                            id={ps.name} 
                            name={ps.name} 
                            value={ps.id} 
                            checked={selectedPollingStations.includes(ps.id.toString())} 
                            onChange={handlePollingStationChange} 
                        />
                        </td>
                        <td>{ps.name}</td>
                        <td>{ps.address}</td>
                        <td>{ps.opcina}</td>
                        <td>{ps.kanton ? ps.kanton : "-"}</td>
                        <td>{ps.entitet}</td>
                        </tr>
                    ))}
                    </tbody>
                </table>

                <input className="dugme" type="submit" value="Submit" />
                </form>
                {errorMessage && <p>{errorMessage}</p>}
            </div>
        </div>
    );
};

export default AssociatePollingStations;