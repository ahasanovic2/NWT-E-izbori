import React, {useEffect, useState} from 'react';
import "../css/ElectionPage.css"

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

    return (
        <div className="container">
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
    );
};

export default ElectionPage;
