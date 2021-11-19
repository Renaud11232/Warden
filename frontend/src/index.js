import React from 'react';
import ReactDOM from 'react-dom';
import App from './components/App/App';
import "halfmoon/css/halfmoon-variables.min.css";
import {BrowserRouter} from "react-router-dom";

const halfmoon = require("halfmoon");

ReactDOM.render(
    <React.StrictMode>
        <BrowserRouter>
            <App/>
        </BrowserRouter>
    </React.StrictMode>,
    document.getElementById('root')
);
halfmoon.onDOMContentLoaded();
