import { BrowserRouter as Router, Route, Switch, Redirect } from 'react-router-dom';
import LoginPage from './components/user/js/LoginPage';
import './App.css';
import './components/user/css/SignUpPage.css';
import SignUpPage from "./components/user/js/SignUpPage";
import React from "react";
import LandingPage from "./components/user/js/LandingPage";
import './components/user/css/LandingPage.css';
import HomePage from "./components/user/js/HomePage";
import {Login} from "@mui/icons-material";
import VotersPage from "./components/user/js/VotersPage";
import './components/user/css/VotersPage.css';
import Legislativa from "./components/user/js/Legislativa";
import './components/user/css/Legislativa.css';
import WhoVoter from "./components/user/js/WhoVoter";
import './components/admin/css/AdminLandingPage.css';
import jwtDecode from 'jwt-decode';
import VotingPageFinal from './components/user/js/VotingPage';
import ElectionPage from './components/user/js/ElectionPage';
import ChoosePSPage from './components/user/js/ChoosePollingStation';
import ResultsHome from './components/user/js/ResultsHome';
import CreateElection from './components/admin/js/CreateElection';
import CreateCandidate from './components/admin/js/CreateCandidate';
import AdminLandingPage from './components/admin/js/AdminLandingPage';

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
                    <Redirect to={{ pathname: "/login", state: { from: props.location, message: 'You need to log in to access this page' } }} />
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
                <Route path="/login" render={props => <LoginPage {...props} />} />
                <PrivateRoute path="/results" component={ResultsHome} onLogout={handleLogout} />
                <PrivateRoute path="/choose-pollingstation" component={ChoosePSPage} onLogout={handleLogout}/>
                <PrivateRoute path="/election" component={ElectionPage} onLogout={handleLogout} />
                <PrivateRoute path="/voting-page" component={VotingPageFinal} onLogout={handleLogout} />
                <PrivateRoute path="/landing" component={LandingPage} onLogout={handleLogout} />
                <PrivateRoute path="/voters" component={VotersPage} />
                <PrivateRoute path="/legislativa" component={Legislativa} onLogout={handleLogout} />
                <Route path="/create-elections" component={CreateElection} onLogout={handleLogout} />
                <Route path="/create-candidates" component={CreateCandidate} onLogout={handleLogout} />
                <Route path="/admin-landing" component={AdminLandingPage} onLogout={handleLogout} />
                <Route path="/">
                    {/* Default route */}
                    <HomePage />
                </Route>
            </Switch>
        </Router>
    );
}

export default App;
