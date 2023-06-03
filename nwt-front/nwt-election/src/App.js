import LoginPage from './components/LoginPage.js';

import './App.css';
import './components/SignUpPage.css'
import SignUpPage from "./components/SignUpPage";
import {useState} from "react";
import LandingPage from "./components/LandingPage";
import './components/LandingPage.css'
import HomePage from "./components/HomePage";
import {Login} from "@mui/icons-material";
import VotersPage from "./components/VotersPage";


/*function App() {
    return(
    <div>
        <VotersPage/>
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
