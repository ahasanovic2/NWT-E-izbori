import LoginPage from './components/LoginPage.js';


import './App.css';
import './css/SignUpPage.css';
import SignUpPage from "./components/SignUpPage";
import React, {useState} from "react";
import LandingPage from "./components/LandingPage";
import './css/LandingPage.css';
import HomePage from "./components/HomePage";
import {Login} from "@mui/icons-material";
import VotersPage from "./components/VotersPage";
import './css/VotersPage.css';
import Legislativa from "./components/Legislativa";
import './css/Legislativa.css';
import ElectionPage from "./components/ElectionPage";
import './css/ElectionPage.css';
import WhoVoter from "./components/WhoVoter";
import { BrowserRouter, Route, Switch } from 'react-router-dom';
import VotingPage from "./components/VotingPage";


function App() {
    const [currentForm, setCurrentForm] = useState("home");

    const toggleForm = (form) => {
        setCurrentForm(form);
    };

    const handleLogout = () => {
        setCurrentForm('home');
    };


    const handleSwitchToVoters = () => {
        setCurrentForm('voters');
    };

    const handleSwitchToLegislativa = () => {
        setCurrentForm('legislativa');
    };
    const handleElection = () => {
        setCurrentForm('election');
    };


    return (
        <div>
            {currentForm === 'home' && (
                <VotingPage
                    onFormSwitch={(formName) => toggleForm(formName)}
                    onLoginClick={() => toggleForm('login')}
                    onSignUpClick={() => toggleForm('signUp')}
                />
            )}
            {currentForm === 'election' && (
                <ElectionPage

                />
            )}

            {currentForm === 'signUp' && (
                <SignUpPage
                    onFormSwitch={(formName) => toggleForm(formName)}
                    onLoginClick={() => toggleForm('login')}
                />
            )}
            {currentForm === 'login' && (
                <LoginPage
                    onFormSwitch={(formName) => toggleForm(formName)}
                    onSignUpClick={() => toggleForm('signUp')}
                />
            )}
            {currentForm === 'landing' && (
                <LandingPage onLogout={handleLogout}/>
            )}
            {currentForm === 'voters' && (
                <VotersPage
                    onFormSwitch={(formName) => toggleForm(formName)}
                />
            )}

            {currentForm === 'landing' && (
                <LandingPage onLogout={handleLogout} onFormSwitch={toggleForm}/>
            )}

            {currentForm === 'legislativa' && (
                <Legislativa
                    onFormSwitch={(formName) => toggleForm(formName)}
                    onLogout={handleLogout}
                />
            )}
        </div>
    );
}
export default App;
