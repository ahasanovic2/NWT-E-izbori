import React, { useState } from 'react';
import '../css/VotingPage.css'
import loginImage from "../images/login5.png";

const candidates = [
    {
        id: 1,
        name: 'Kandidat 1',
        party: 'Stranka 1',
        description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis gravida est non elit feugiat, vitae aliquam ipsum bibendum. Sed mollis euismod feugiat. Phasellus in dolor non risus finibus pretium a id nunc. In ut tellus et turpis consectetur rhoncus. Vestibulum blandit risus nec metus vestibulum, in semper mauris tristique.',
        list: 'Lista A',
    },
    {
        id: 2,
        name: 'Kandidat 2',
        party: 'Stranka 2',
        description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis gravida est non elit feugiat, vitae aliquam ipsum bibendum. Sed mollis euismod feugiat. Phasellus in dolor non risus finibus pretium a id nunc. In ut tellus et turpis consectetur rhoncus. Vestibulum blandit risus nec metus vestibulum, in semper mauris tristique.',
        list: 'Lista B',
    },
    {
        id: 3,
        name: 'Kandidat 3',
        party: 'Stranka 3',
        description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis gravida est non elit feugiat, vitae aliquam ipsum bibendum. Sed mollis euismod feugiat. Phasellus in dolor non risus finibus pretium a id nunc. In ut tellus et turpis consectetur rhoncus. Vestibulum blandit risus nec metus vestibulum, in semper mauris tristique.',
        list: 'Lista A',
    },
    {
        id: 4,
        name: 'Kandidat 4',
        party: 'Stranka 4',
        description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis gravida est non elit feugiat, vitae aliquam ipsum bibendum. Sed mollis euismod feugiat. Phasellus in dolor non risus finibus pretium a id nunc. In ut tellus et turpis consectetur rhoncus. Vestibulum blandit risus nec metus vestibulum, in semper mauris tristique.',
        list: 'Lista B',
    },
];

function Candidate({ candidate, selectedCandidates, setSelectedCandidates }) {
    const handleClick = () => {
        const newList = [...selectedCandidates];
        const candidateIndex = newList.findIndex(c => c.list === candidate.list);

        if (candidateIndex !== -1) {
            newList.splice(candidateIndex, 1);
        }

        newList.push(candidate);
        setSelectedCandidates(newList);
    };

    const isSelected = selectedCandidates.some(c => c.id === candidate.id);

    return (
        <div className={`candidate ${isSelected ? 'selected' : ''}`} onClick={handleClick}>
            <div className="image-containerLogin">
                <img src={loginImage} alt="Login" />
            </div>
            <h2>{candidate.name}</h2>
            <h3>{candidate.party}</h3>
            <p>{candidate.description}</p>
        </div>
    );
}

function VotingPage({ selectedCandidates, setSelectedCandidates, clearSelection }) {
    const handleSubmit = () => {
        // Validacija izbora

        //da li je user glasao za više od jedne liste
        const selectedLists = selectedCandidates.map(c => c.list);
        const uniqueLists = Array.from(new Set(selectedLists));

        if (uniqueLists.length > 1) {
            alert('Možete glasati samo za jednu listu.');
            return;
        }

        //  da li je user glasao za listu A i bilo kojeg kandidata iz liste B
        const selectedCandidatesNames = selectedCandidates.map(c => c.name);
        const listASelected = selectedCandidates.some(c => c.list === 'Lista A');
        const listBSelected = selectedCandidates.some(c => c.list === 'Lista B');

        if (listASelected && listBSelected) {
            alert('Ne možete glasati za kandidate iz obje liste.');
            return;
        }

        //da li je user glasao za nezavisnu listu
        const independentListSelected = selectedCandidates.some(c => c.list === 'Nezavisna lista');

        if (independentListSelected) {
            alert('Možete glasati samo za kandidate unutar nezavisne liste.');
            return;
        }

        //  dodatne provjere ako treba jos dodat

        // spasit glas u bazu podataka

        // Resetuj izbor
        clearSelection();

        // Preusmjerite korisnika na početnu stranicu (to dodati u app.js kad se prebaci na koristenje switch-a
    };

    return (
        <div className="VotingPage">
            <h1>Glasanje na izborima</h1>
            <div className="candidates">
                {candidates.map(candidate => (
                    <Candidate
                        key={candidate.id}
                        candidate={candidate}
                        selectedCandidates={selectedCandidates}
                        setSelectedCandidates={setSelectedCandidates}
                    />
                ))}
            </div>
            <button onClick={clearSelection}>Izbriši unos</button>
            <button onClick={handleSubmit}>Potvrdi glas</button>
        </div>
    );
}

function App() {
    const [selectedCandidates, setSelectedCandidates] = useState([]);

    const clearSelection = () => {
        setSelectedCandidates([]);
    };

    return (
        <div className="App">
            <h1>Informacije o kandidatima</h1>
            <VotingPage
                selectedCandidates={selectedCandidates}
                setSelectedCandidates={setSelectedCandidates}
                clearSelection={clearSelection}
            />
        </div>
    );
}

export default App;
