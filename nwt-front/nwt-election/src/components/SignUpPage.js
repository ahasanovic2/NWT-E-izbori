import React, { useState } from 'react';
import './SignUpPage.css';

function validateInput(input) {
    const id = input.id;
    const value = input.value;
    const errorText = document.getElementById(`errorText-${id}`);

    if (id === 'inputNumber') {
        if (value.length !== 13 || isNaN(value)) {
            errorText.style.display = 'block';
            input.setCustomValidity('Invalid input');
        } else {
            errorText.style.display = 'none';
            input.setCustomValidity('');
        }
    } else if (id === 'email') {
        const emailRegex = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;

        if (!value.match(emailRegex)) {
            errorText.style.display = 'block';
            input.setCustomValidity('Invalid email');
        } else {
            errorText.style.display = 'none';
            input.setCustomValidity('');
        }
    }
}
function SignUpPage(props) {
    const handleSignUp = () => {
        const inputNumber = document.getElementById('inputNumber');
        const inputEmail = document.getElementById('email');

        validateInput(inputNumber);
        validateInput(inputEmail);

        if (inputNumber.checkValidity() && inputEmail.checkValidity()) {
            const number = inputNumber.value;
            const email = inputEmail.value;

            // Perform additional validation if needed

            // Send a request to the server to sign up the user
            fetch('/signup', {
                method: 'POST',
                body: JSON.stringify({ number, email }),
                headers: {
                    'Content-Type': 'application/json',
                },
            })
                .then((response) => {
                    if (response.ok) {
                        // Handle successful sign-up
                        console.log('User signed up successfully');
                    } else {
                        // Handle sign-up error
                        console.error('Error signing up');
                    }
                })
                .catch((error) => {
                    // Handle network or other errors
                    console.error('An error occurred', error);
                });
        }
    };

    const [selectedOption, setSelectedOption] = useState('');

    return (
        <div className="login-page">
            <div className="frame">
                <span style={{ marginTop: '5px', marginBottom: '45px'}}>
                    <h2 className="font">Registracija korisnika</h2>
                </span>
                <div className="input-container">
                    <input className="input-field" type="firstName" id="firstName" placeholder="Ime" />
                </div>
                <div className="input-container">
                    <input className="input-field" type="lastName" id="lastName" placeholder="Prezime" />
                </div>
                <div className="input-container">
                    <input className="input-field" type="residence" id="residence" placeholder="Mjesto prebivališta" />
                </div>
                <div className="input-container">
                    <form>
                        {/* Ostatak forme */}
                        <select className="dropdown">
                            <option value="Hrasno">Opcija 1</option>
                            <option value="Malta">Opcija 2</option>
                            <option value="Grbavica">Opcija 3</option>
                        </select>
                        {/* Ostatak forme */}
                    </form>
                </div>
                <div className="input-container">
                    <input className="input-field" type="text" id="inputNumber" placeholder="JMBG" />
                    <p id="errorText-inputNumber" style={{ color: 'red', display: 'none' }}>
                        JMBG mora sadržati 13 brojeva.
                    </p>
                </div>
                <div className="input-container">
                    <input className="input-field-email" type="email" id="email" placeholder="E-mail adresa" />
                    <p id="errorText-email" style={{ color: 'red', display: 'none' }}>
                        Unesite validnu email adresu.
                    </p>
                </div>
                <div className="input-container">
                    <input className="input-field" id="password" placeholder="Lozinka" />
                </div>
                <button className="signup-button" onClick={handleSignUp}>
                    Registracija
                </button>
                <div className="links">
                    <button className="buttonLink" onClick={()=>props.onFormSwitch('login')}>Već si registrovan/a? <b>Prijavi se.</b></button>
                </div>
            </div>
        </div>
    );
}

export default SignUpPage;
