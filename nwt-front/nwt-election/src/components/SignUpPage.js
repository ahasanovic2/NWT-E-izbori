import React, { useState, useRef } from 'react';
import { useHistory } from 'react-router-dom';
import './SignUpPage.css';

function SignUpPage() {
    const history = useHistory();
    const [selectedOption, setSelectedOption] = useState('');
    const inputNumber = useRef(null);
    const inputEmail = useRef(null);

    function validateInput(input) {
        const id = input.id;
        const value = input.value;
        const errorText = document.getElementById(`errorText-${id}`);

        if (id === 'email') {
            const emailRegex = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;

            if (!value.match(emailRegex)) {
                errorText.style.display = 'block';
                inputEmail.current.classList.add('error-field');
                return false;
            } else {
                errorText.style.display = 'none';
                inputEmail.current.classList.remove('error-field');
                return true;
            }
        }
    }

    const handleSignUp = () => {
        const isNumberValid = validateInput(inputNumber.current);
        const isEmailValid = validateInput(inputEmail.current);

        if (isNumberValid && isEmailValid) {
            const number = inputNumber.current.value;
            const email = inputEmail.current.value;

            // Rest of the registration processing code

            history.push('/landing');
        }
    };

    return (
        <div className="signUp-page">
            <div className="frameSignUp">
        <span style={{ marginTop: '5px', marginBottom: '30px' }}>
          <h2 className="font-signUp">Registracija korisnika</h2>
        </span>
                <div className="input-container-signUp">
                    <input className={`input-field-signUp ${selectedOption === 'firstName' && 'error-field'}`} type="text" id="firstName" placeholder="Ime" />
                </div>
                <div className="input-container-signUp">
                    <input className={`input-field-signUp ${selectedOption === 'lastName' && 'error-field'}`} type="text" id="lastName" placeholder="Prezime" />
                </div>
                <div className="input-container-signUp">
                    <input className={`input-field-signUp ${selectedOption === 'email' && 'error-field'}`} type="email" id="email" placeholder="E-mail adresa" ref={inputEmail} />
                    <p id="errorText-email" style={{ color: 'red', display: 'none' }}>
                        Unesite validnu email adresu.
                    </p>
                </div>
                <div className="input-container-signUp">
                    <input className={`input-field-signUp ${selectedOption === 'password' && 'error-field'}`} type="password" id="password" placeholder="Lozinka" />
                </div>
                <button className="signup-button" onClick={handleSignUp}>
                    Registracija
                </button>
                <div className="links">
                    <button className="buttonLink" onClick={() => history.push('/login')}>Već si registrovan/a? <b>Prijavi se.</b></button>
                </div>
            </div>
        </div>
    );
}

export default SignUpPage;
