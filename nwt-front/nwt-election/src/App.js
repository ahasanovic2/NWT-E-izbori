import LoginPage from './components/LoginPage.js';

import './App.css';
import './components/SignUpPage.css'
import SignUpPage from "./components/SignUpPage";
import {useState} from "react";
import LandingPage from "./components/LandingPage";


function App() {
    const [currentForm, setCurrentForm] = useState('login');

    const toggleForm = (form) => {
        setCurrentForm(form);
    }
  return (
      <div className="App">
          {
              currentForm == "login" ? <LoginPage onFormSwitch={toggleForm} /> : <SignUpPage onFormSwitch={toggleForm}/>
          }
      </div>
  );
}


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
