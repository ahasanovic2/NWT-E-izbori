import React, {useState} from "react";
import '../css/CreatingElections.css';
import { useHistory } from "react-router-dom";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";


const CreatingElections = () => {
    const history = useHistory();
    const [name, setName] = useState("");
    const [description, setDescription] = useState("");
    const [startTime, setStartTime] = useState(new Date());
    const [endTime, setEndTime] = useState(new Date());
    const [errorMessageTime, setErrorMessageTime] = useState("");
    const [errorMessageDescription, setErrorMessageDescription] = useState("");
    const [errorMessage, setErrorMessage] = useState("");

    const handleSwitchToLanding = () => {
        history.push('/admin-landing');
    };

    const handleSwitchToCreatingElections = () => {
        history.push('/admin-create-elections')
    };

    const handleLogout = () => {
        localStorage.removeItem('access_token');
        localStorage.removeItem('refresh_token');
        history.push('/');
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setErrorMessageDescription("");
        setErrorMessageTime("");

        if (description.length < 20) {
            setErrorMessageDescription("Description must be at least 20 characters long");
            return;
        }
        if (startTime >= endTime) {
            setErrorMessageTime("Start time cannot be after end time");
            return;
        }

        setErrorMessageDescription("");
        setErrorMessageTime("");
        
        // Prepare request
        const token = localStorage.getItem('access_token');
        const BASE_URL = process.env.REACT_APP_BASE_URL || 'http://localhost:8080';
        const headers = new Headers();
        headers.append('Content-Type', 'application/json');
        headers.append('Authorization', `Bearer ${token}`);
    
        const body = JSON.stringify({
            name,
            description,
            startTime: startTime.toISOString(),
            endTime: endTime.toISOString()
        });
    
        // Send request
        const response = await fetch(`${BASE_URL}/election-microservice/elections/create-election`, {
            method: 'POST',
            headers,
            body
        });
    
        // Handle response
        if (!response.ok) {
            const errorData = await response.json();
            setErrorMessage(errorData[0].message);
            alert('Something went wrong');
        } else {
            alert('Successfuly added elections');
            setErrorMessage(""); // Clear the error message upon successful request
            history.push('/admin-landing');
        }
    };

    return (
        <div className="creating-elections">
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
                    <button onClick={handleLogout}>Odjava</button>
                </div>
            </div>
            <div className="content">
                <form onSubmit={handleSubmit}>
                    <label>
                        Naziv:
                        <input type="text" value={name} onChange={e => setName(e.target.value)} required/>
                    </label>
                    <br/>
                    <label>
                        Opis:
                        <textarea value={description} onChange={e => setDescription(e.target.value)} required minLength={20}/>
                    </label>
                    {errorMessageDescription && <p className="error-class">{errorMessageDescription}</p>}
                    <br/>
                    <label>
                        Početak izbora:
                        <DatePicker selected={startTime} onChange={date => setStartTime(date)} showTimeSelect dateFormat="Pp" minDate={new Date()} />
                    </label>
                    <label>
                        Kraj izbora:
                        <DatePicker selected={endTime} onChange={date => setEndTime(date)} showTimeSelect dateFormat="Pp" minDate={startTime} />
                    </label>
                    {errorMessageTime && <p className="error-class">{errorMessageTime}</p>}
                    <button type="submit">Potvrdi</button>
                    {errorMessage && <p className="error-class">{errorMessage}</p>}
                </form>
            </div>
        </div>
    );
};
export default CreatingElections;