import LoginPage from './components/LoginPage.js';

import './App.css';
import './components/SignUpPage.css';
import SignUpPage from "./components/SignUpPage";
import React, {useState} from "react";
import LandingPage from "./components/LandingPage";
import './components/LandingPage.css';
import HomePage from "./components/HomePage";
import {Login} from "@mui/icons-material";
import VotersPage from "./components/VotersPage";
import './components/VotersPage.css';
import Legislativa from "./components/Legislativa";
import './components/Legislativa.css';
import WhoVoter from "./components/WhoVoter";


/*function App() {
    return(
    <div>
        <Legislativa/>
    </div>
    );
}*/

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

    return (
        <div>
            {currentForm === 'home' && (
                <HomePage
                    onFormSwitch={(formName) => toggleForm(formName)}
                    onLoginClick={() => toggleForm('login')}
                    onSignUpClick={() => toggleForm('signUp')}
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
                <LandingPage onLogout={handleLogout} />
            )}
            {currentForm === 'voters' && (
                <VotersPage
                    onFormSwitch={(formName) => toggleForm(formName)}
                />
            )}

            {currentForm === 'landing' && (
                <LandingPage onLogout={handleLogout} onFormSwitch={toggleForm} />
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


/*function App() {
    const [currentForm, setCurrentForm] = useState('login');

    const toggleForm = (form) => {
        setCurrentForm(form);
    }
  return (/*
      <div className="App">
          {
              currentForm == "login" ? <LoginPage onFormSwitch={toggleForm} /> : <SignUpPage onFormSwitch={toggleForm}/>
          }
      </div>*/
    /*<div>
        <HomePage/>
    </div>
  );
}*/


/*function App() {
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>
    </div>
  );
}*/

export default App;
