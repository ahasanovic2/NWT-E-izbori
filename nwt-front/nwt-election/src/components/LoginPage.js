import React, { useState } from 'react';
import loginImage from '../images/login5.png';

import {FiEye, FiEyeOff} from 'react-icons/fi';

import './LoginPage.css'

function LoginPage(props) {

    const [showPassword, setShowPassword] = useState(false);

    const togglePasswordVisibility = () => {
        setShowPassword(!showPassword);
    };

    return (
        <div className="login-page">
            <div className="frameLogIn">
                <h2 className="fontLogin">Prijava korisnika</h2>
                <div className="image-containerLogin">
                    <img src={loginImage} alt="Login" />
                </div>
                <div className="input-containerLogin">
                    <input className="input-field-emailLogin" type="email" id="email" placeholder="E-mail adresa" />
                </div>
                <div className="input-containerLogin">
                    <input className="input-fieldLogin" type={showPassword ? 'text' : 'password'} id="password" placeholder="Lozinka" />
                    <span style={{ marginTop: '4px', marginBottom: '-15px', fontSize: '1.5em'  }}>
                    {showPassword ? (
                        <FiEyeOff onClick={togglePasswordVisibility} />
                    ) : (
                        <FiEye onClick={togglePasswordVisibility} />
                    )}
                    </span>
                </div>
                <button className="login-button" onClick={()=>props.onFormSwitch('landing')}>Prijava</button>
                <div className="links">
                    <a className="link" href="#">Izgubljena lozinka?</a>
                    <button className="buttonLink" onClick={()=>props.onFormSwitch('signUp')}>Nemaš nalog? <b>Registruj se.</b></button>
                </div>

            </div>
        </div>
    );

}

export default LoginPage;
