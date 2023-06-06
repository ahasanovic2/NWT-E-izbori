import React from 'react';
import '../css/HowVoter.css'

const HowVoter = (props) => {
    return (
        <div className="landing-page">
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
                    <button>Legislativa
                        <br/>
                        <span className="small-text">Zakon o provođenju izbora</span>
                    </button>
                    <button onClick={props.onLogout}>Odjava</button>
                </div>
            </div>
            <div className="centrirano">
            <section>
                <h2>Kako glasati?</h2>
                <p>Da biste glasali na dan izbora:</p>
                <p>-morate biti registrovani na našoj aplikaciji,</p>
                <p>-prilikom registracije morate unijeti svoj JMBG i jedinstvenu email adresu.</p>
            </section>
            </div>
        </div>
    );
};

export default HowVoter;
