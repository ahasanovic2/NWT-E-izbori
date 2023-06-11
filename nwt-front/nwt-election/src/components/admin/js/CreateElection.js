import React, { useState } from "react";
import axios from "axios";
import { useHistory, Switch, Route, useRouteMatch, useLocation } from 'react-router-dom';
import '../css/CreateElection.css';


const CreateElection = (props) => {
     const history = useHistory();
    const [title, setTitle] = useState("");
    const [description, setDescription] = useState("");
    const [startDate, setStartDate] = useState("");
    const [endDate, setEndDate] = useState("");
   
    const handleSwitchToLanding = () => {
        history.push('/landing');
    };

    const handleSwitchToCreateCandidates = () => {
        history.push('/create-candidates');
    };

    const handleSwitchToLegislativa = () => {
        history.push('/legislativa');
    };

    const handleLogout = () => {
        localStorage.removeItem('access_token');
        localStorage.removeItem('refresh_token');
        history.push('/');
    };

    const handleSubmit = (event) => {
        event.preventDefault();
        const data = {
            title: title,
            description: description,
            startDate: startDate,
            endDate: endDate,
            status: "Active",
        };

            axios
                .post("http://localhost:8080/election-microservice/elections/create-election", data)
                .then((response) => {
                console.log("Election created successfully!");
                // Handle success response, such as displaying a success message
                })
            .catch((error) => {
                console.error("Failed to create election:", error);
                 // Handle error response, such as displaying an error message
            });
  
        };
        
   


    return (
        <div className="landing-page">
            <div className="header">
                <h1>E-izbori</h1>
                <div className="nav-buttons">
                    <button onClick={handleSwitchToLanding}>
                        Početna
                        <br/>
                        <span className="small-text">Početna stranica aplikacije</span>
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
            <div className="createElection">
            <h2>Kreiraj izbor</h2>
            <form onSubmit={handleSubmit}>
                <div>
                    <label>Ime izbora:</label>
                        <input
                        type="text"
                        value={title}
                        onChange={(e) => setTitle(e.target.value)}
                    />
                </div>
                <div>
                    <label>Dodatni opis:</label>
                    <input
                    type="text"
                    value={description}
                    onChange={(e) => setDescription(e.target.value)}
                    />
                </div>
                <div>
                    <label htmlFor="endDate">Početak izbora:</label>
                    <input
                        type="date"
                        id="startDate"
                        name="startDate"
                        value={startDate}
                        onChange={(e) => setStartDate(e.target.value)}
                    />
                </div>
                <div>
                    <label htmlFor="endDate">Kraj izbora:</label>
                    <input
                        type="date"
                        id="endDate"
                        name="endDate"
                        value={endDate}
                        onChange={(e) => setEndDate(e.target.value)}
                    />
                </div>
                <button onClick={handleSwitchToCreateCandidates}>Kreiraj</button>
            </form>
            </div>
        </div>
    );
};
export default CreateElection;