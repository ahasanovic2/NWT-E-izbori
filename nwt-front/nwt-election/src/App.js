import { BrowserRouter as Router, Route, Switch, Redirect } from 'react-router-dom';
import LoginPage from './components/LoginPage';
import './App.css';
import './components/SignUpPage.css';
import SignUpPage from "./components/SignUpPage";
import React from "react";
import LandingPage from "./components/LandingPage";
import './components/LandingPage.css';
import HomePage from "./components/HomePage";
import {Login} from "@mui/icons-material";
import VotersPage from "./components/VotersPage";
import './components/VotersPage.css';
import Legislativa from "./components/Legislativa";
import './components/Legislativa.css';
import WhoVoter from "./components/WhoVoter";
import AdminLandingPage from "./components/AdminLandingPage";
import CreateElections from './components/CreateElections';
import './components/AdminLandingPage.css';
import jwtDecode from 'jwt-decode';
import VotingPageFinal from './components/VotingPage';
import ElectionPage from './components/ElectionPage';

function App() {
    const handleLogout = () => {
        // Don't forget to clear the access tokens when logging out
        localStorage.removeItem('access_token');
        localStorage.removeItem('refresh_token');
    };

    // Higher-order component for protected routes
    const PrivateRoute = ({ component: Component, ...rest }) => (
        <Route
            {...rest}
            render={props =>
                localStorage.getItem('access_token') ? (
                    <Component {...props} />
                ) : (
                    <Redirect to="/login" />
                )
            }
        />
    );

    return (
        <Router>
            <Switch>
                <Route path="/home">
                    <HomePage />
                </Route>
                <Route path="/sign-up">
                    <SignUpPage />
                </Route>
                <Route path="/login">
                    <LoginPage />
                </Route>
                <PrivateRoute path="/election" component={ElectionPage} onLogout={handleLogout} />
                <PrivateRoute path="/voting-page" component={VotingPageFinal} onLogout={handleLogout} />
                <PrivateRoute path="/landing" component={LandingPage} onLogout={handleLogout} />
                <PrivateRoute path="/admin-landing" component={AdminLandingPage} onLogout={handleLogout} />
                <PrivateRoute path="/voters" component={VotersPage} />
                <PrivateRoute path="/legislativa" component={Legislativa} onLogout={handleLogout} />
                <PrivateRoute path="/create-elections" component={CreateElections} />
                <Route path="/">
                    {/* Default route */}
                    <HomePage />
                </Route>
            </Switch>
        </Router>
    );
}

export default App;
