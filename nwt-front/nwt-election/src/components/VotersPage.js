import React, {useState} from 'react';
import './VotersPage.css';
import HowVoter from './HowVoter';
import WhoVoter from './WhoVoter';

const VotersPage = (props) => {

    const [selectedComponent, setSelectedComponent] = useState(null);

    const handleButtonClick = (componentName) => {
        setSelectedComponent(componentName);
    };

    return (
        <div className="landing-page">
            <div className="header">
                <h1>E-izbori</h1>
                <div className="nav-buttons">
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
                    <button>Legislativa
                        <br/>
                        <span className="small-text">Zakon o provođenju izbora</span>
                    </button>
                    <button onClick={props.onLogout}>Odjava</button>
                </div>
            </div>
            <div className="left-sidebar">
                <button
                    className={`sidebar-button ${selectedComponent === 'whoVoter' ? 'active' : ''}`}
                    onClick={() => handleButtonClick('whoVoter')}
                >
                    Ko može glasati?
                </button>
                <button
                    className={`sidebar-button ${selectedComponent === 'howVoter' ? 'active' : ''}`}
                    onClick={() => handleButtonClick('howVoter')}
                >
                    Kako glasati?
                </button>
                {selectedComponent === 'whoVoter' && <WhoVoter />}
                {selectedComponent === 'howVoter' && <HowVoter />}
            </div>
        </div>
    );
};

export default VotersPage;
