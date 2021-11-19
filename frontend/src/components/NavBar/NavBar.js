import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import * as fas from '@fortawesome/free-solid-svg-icons';
import {Link} from "react-router-dom";
import {useState} from "react";

const halfmoon = require("halfmoon");

export default function NavBar() {
    const [user] = useState();

    return (
        <nav className="navbar">
            {user &&
            <div className="navbar-content">
                <button className="btn" type="button" onClick={() => {
                    halfmoon.toggleSidebar()
                }}>
                    <FontAwesomeIcon icon={fas.faBars}/>
                </button>
            </div>
            }
            <Link to="/" className="navbar-brand">
                <img src="/img/logo.png" alt="Logo"/>
                Warden
            </Link>
            <span className="navbar-text ml-5">
                <span className="badge text-monospace">v0.0.1-dev</span>
            </span>
            <ul className="navbar-nav ml-auto">
                {user &&
                <li className="nav-item dropdown with-arrow">
                    <a className="nav-link" data-toggle="dropdown" id="user-dropdown-toggle" href="#">
                        Le username
                        <FontAwesomeIcon icon={fas.faAngleDown} className="ml-5"/>
                    </a>
                    <div className="dropdown-menu dropdown-menu-right" aria-labelledby="user-dropdown-toggle">
                        <a href="#" className="dropdown-item">Change password</a>
                        {/* onClick="document.getElementById('logout-form').submit()"*/}
                        <a href="#" className="dropdown-item">Logout</a>
                        <form id="logout-form" method="POST" action="azeazeazejjakhbzzfioij">
                        </form>
                    </div>
                </li>
                }
            </ul>
        </nav>
    );
}
