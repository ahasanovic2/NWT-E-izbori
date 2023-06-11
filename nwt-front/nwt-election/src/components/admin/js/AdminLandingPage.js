import React, {useState} from 'react';
import '../css/AdminLandingPage.css';
import '../../user/js/WhoVoter';
import { useHistory } from 'react-router-dom';

const AdminLandingPage = () => {
    
    const history = useHistory();

    const handleSwitchToLanding = () => {
        history.push('/admin-landing');
    };

    const handleSwitchToCreatingElections = () => {
        history.push('/admin-create-elections')
    }

    const handleLogout = () => {
        localStorage.removeItem('access_token');
        localStorage.removeItem('refresh_token');
        history.push('/');
    };



    return (
        <div className="landing-page">
            <div className="header">
                <h1>E-izbori</h1>
                <div className="nav-buttons">
                    <button onClick={handleSwitchToLanding}>Početna
                        <br/>
                        <span className="small-text">Početna stranica aplikacije</span>
                    </button>
                    <button onClick={handleSwitchToCreatingElections}>Kreiraj izbore
                        <br/>
                        <span className='small-text'></span>
                    </button>
                    <button onClick={handleLogout}>Odjava</button>
                </div>
            </div>
            <div className="landing-content">
                <h2>Početna stranica za administratora</h2>
                <p>
                    Online glasanje na izborima u Bosni i Hercegovini vam omogućava da udobno i sigurno glasate iz udobnosti svog doma.
                    Bez obzira gdje se nalazite, možete iskoristiti svoje biračko pravo i doprinijeti demokratskom procesu.
                </p>
                <p>
                    Naša platforma omogućava jednostavno registrovanje, sigurno provođenje izbora i transparentno prikazivanje rezultata.
                    Uz pomoć naše aplikacije, možete pristupiti informacijama o kandidatima, glasati za svoje favorite i pratiti izborne rezultate u realnom vremenu.
                </p>
                <p>
                    <b>Vaš glas je važan! Pridružite se online glasanju i izaberite budućnost Bosne i Hercegovine.</b>
                </p>
            </div>
        </div>
    );
};

export default AdminLandingPage;
