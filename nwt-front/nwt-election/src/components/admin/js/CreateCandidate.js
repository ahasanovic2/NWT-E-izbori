import React, {useState} from 'react';

import { useHistory } from 'react-router-dom';

const CreateCandidate= (props) => {
    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [description, setDescription] = useState("");
    const history = useHistory();
    const handleSwitchToCreateElections = () => {
        history.push('/create-elections');
    };

    const handleSwitchToVoters = () => {
        history.push('/voters');
    };

    const handleSwitchToLegislativa = () => {
        history.push('/legislativa');
    };

    const handleSwitchToCreateVoter = () => {
        history.push('/createvoter');
    };
    const handleSubmit = (event) => {
        event.preventDefault();
        const data = {
            firstName: firstName,
            lastname: lastName,
            description: description,
        };
    };
    return (
        <div className="landing-page">
            <div className="header">
                <h1>E-izbori</h1>
                <div className="nav-buttons">
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
                    <button onClick={handleSwitchToCreateVoter}>Kreiraj glasača</button>
                    <button onClick={handleSwitchToCreateElections}>Kreiraj izbore</button>
                    <button onClick={props.onLogout}>Odjava</button>
                </div>
            </div>
            <div className="createElection">
            <h2>Kreiraj izbor</h2>
            <form onSubmit={handleSubmit}>
                <div>
                    <label>Ime kandidata:</label>
                        <input
                        type="text"
                        value={firstName}
                        onChange={(e) => setFirstName(e.target.value)}
                    />
                </div>
                <div>
                    <label>Prezime kandidata:</label>
                    <input
                    type="text"
                    value={lastName}
                    onChange={(e) => setLastName(e.target.value)}
                    />
                </div>
                <div>
                    <label>Opis kandidata:</label>
                    <input
                        type="text"
                        value={description}
                        onChange={(e) => setDescription(e.target.value)}
                    />
                </div>

                <button>Kreiraj</button>
            </form>
            </div>
        </div>
    );
};
export default CreateCandidate;