import React from 'react';
import ReactDOM from 'react-dom';
import App from './components/App';
import "halfmoon/css/halfmoon-variables.min.css";
import {BrowserRouter} from "react-router-dom";
import {HelmetProvider} from "react-helmet-async";

const halfmoon = require("halfmoon");

ReactDOM.render(
    <React.StrictMode>
        <HelmetProvider>
            <BrowserRouter>
                <App/>
            </BrowserRouter>
        </HelmetProvider>
    </React.StrictMode>,
    document.getElementById('root')
);
halfmoon.onDOMContentLoaded();
