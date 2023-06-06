import React, {useEffect, useState} from 'react';
import "./ElectionPage.css"
import { useHistory } from 'react-router-dom';

const ElectionPage = () => {
    // Hardkodirane vrijednosti izbora
    const elections = [
        {
            id: 1,
            name: 'Izbori 2023',
            description: 'Opis izbora 2023',
            startTime: '01.01.2023',
            endTime: '31.12.2023',
            status: 'active',
        },
        {
            id: 2,
            name: 'Izbori 2024',
            description: 'Opis izbora 2024',
            startTime: '01.01.2024',
            endTime: '31.12.2024',
            status: 'not started',
        },
        {
            id:3,
            name: 'Izbori 2024',
            description: 'Opis izbora 2025',
            startTime: '01.01.2025',
            endTime: '31.12.2025',
            status: 'not started',
        },

    ];

    //ovu logiku mozemo koristiti kad budemo mogli stvarne podatke imati
    // const ElectionPage = () => {
    // const [elections, setElections] = useState([]);
    //
    // useEffect(() => {
    //     // Pozovi backend rutu za dohvat izbora
    //     fetch('/api/elections/pollingStations')
    //         .then(response => response.json())
    //         .then(data => {
    //          ovdje se moze dodat uslov da li ima pravo na glasanje
    //             setElections(data);
    //         })
    //         .catch(error => {
    //             console.error('Greška prilikom dohvata izbora:', error);
    //         });
    // }, []);

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
                            <h2 className={"title"}>{election.title}</h2>
                            <p>{election.description}</p>
                            <p>Start Time: {election.startTime}</p>
                            <p>End Time: {election.endTime}</p>
                        <p>Status: {election.status}</p>
                        {election.status === 'active' && (
                            <button>Glasaj</button>
                        )}
                    </div>
                ))}
                </div>
            </div>
        </div>
    );
};

export default ElectionPage;