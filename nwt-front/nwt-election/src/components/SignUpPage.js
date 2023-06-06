import React, { useState, useRef } from 'react';
import '../css/SignUpPage.css';

function SignUpPage(props) {
    const [selectedOption, setSelectedOption] = useState('');
    const inputNumber = useRef(null);
    const inputEmail = useRef(null);

    function validateInput(input) {
        const id = input.id;
        const value = input.value;
        const errorText = document.getElementById(`errorText-${id}`);

        if (id === 'inputNumber') {
            if (value.length !== 13 || isNaN(value)) {
                errorText.style.display = 'block';
                inputNumber.current.classList.add('error-field');
                return false;
            } else {
                errorText.style.display = 'none';
                inputNumber.current.classList.remove('error-field');
                return true;
            }
        } else if (id === 'email') {
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

            // Ostatak koda za obradu registracije

            props.onFormSwitch('landing');
        }
    };

    return (
        <div className="signUp-page">
            <div className="frameSignUp">
        <span style={{ marginTop: '5px', marginBottom: '45px' }}>
          <h2 className="font-signUp">Registracija korisnika</h2>
        </span>
                <div className="input-container-signUp">
                    <input className={`input-field-signUp ${selectedOption === 'firstName' && 'error-field'}`} type="text" id="firstName" placeholder="Ime" />
                </div>
                <div className="input-container-signUp">
                    <input className={`input-field-signUp ${selectedOption === 'lastName' && 'error-field'}`} type="text" id="lastName" placeholder="Prezime" />
                </div>
                <div className="input-container-signUp">
                    <input className={`input-field-signUp ${selectedOption === 'residence' && 'error-field'}`} type="text" id="residence" placeholder="Mjesto prebivališta" />
                </div>
                <div className="input-container-signUp">
                    <form>
                        {/* Ostatak forme */}
                        <select className={`dropdown ${selectedOption === 'dropdown' && 'error-field'}`}>
                            <option value="Hrasno">Opcija 1</option>
                            <option value="Malta">Opcija 2</option>
                            <option value="Grbavica">Opcija 3</option>
                        </select>
                        {/* Ostatak forme */}
                    </form>
                </div>
                <div className="input-container-signUp">
                    <input className={`input-field-signUp ${selectedOption === 'inputNumber' && 'error-field'}`} type="text" id="inputNumber" placeholder="JMBG" ref={inputNumber} />
                    <p id="errorText-inputNumber" style={{ color: 'red', display: 'none' }}>
                        JMBG mora sadržati 13 brojeva.
                    </p>
                </div>
                <div className="input-container-signUp">
                    <input className={`input-field-email-signUp ${selectedOption === 'email' && 'error-field'}`} type="email" id="email" placeholder="E-mail adresa" ref={inputEmail} />
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
                    <button className="buttonLink" onClick={() => props.onFormSwitch('login')}>Već si registrovan/a? <b>Prijavi se.</b></button>
                </div>
            </div>
        </div>
    );
}

export default SignUpPage;
